package com.cms.service;

import com.cms.mapper.UserMapper;
import com.cms.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Tests")
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .username("testuser")
                .password("encodedPassword")
                .role("VIEWER")
                .email("test@example.com")
                .enabled(true)
                .build();
    }

    @Test
    @DisplayName("Find user by ID - success")
    void findById_ShouldReturnUser() {
        when(userMapper.findById(1L)).thenReturn(testUser);
        User result = userService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUsername()).isEqualTo("testuser");
        verify(userMapper).findById(1L);
    }

    @Test
    @DisplayName("Find user by ID - not found")
    void findById_ShouldReturnNull_WhenNotFound() {
        when(userMapper.findById(99L)).thenReturn(null);
        User result = userService.findById(99L);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Find user by username - success")
    void findByUsername_ShouldReturnUser() {
        when(userMapper.findByUsername("testuser")).thenReturn(testUser);
        User result = userService.findByUsername("testuser");
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        verify(userMapper).findByUsername("testuser");
    }

    @Test
    @DisplayName("Find all users")
    void findAll_ShouldReturnAllUsers() {
        List<User> users = Arrays.asList(testUser,
                User.builder().id(2L).username("admin").role("ADMIN").enabled(true).build());
        when(userMapper.findAll()).thenReturn(users);
        List<User> result = userService.findAll();
        assertThat(result).hasSize(2);
        verify(userMapper).findAll();
    }

    @Test
    @DisplayName("Create user - encodes password")
    void createUser_ShouldEncodePasswordAndSave() {
        User newUser = User.builder()
                .username("newuser")
                .password("rawPassword")
                .role("VIEWER")
                .email("new@example.com")
                .build();
        when(passwordEncoder.encode("rawPassword")).thenReturn("encodedPassword");
        when(userMapper.insert(any(User.class))).thenReturn(1);

        User result = userService.createUser(newUser);

        assertThat(result.getPassword()).isEqualTo("encodedPassword");
        assertThat(result.isEnabled()).isTrue();
        assertThat(result.getCreatedDate()).isNotNull();
        verify(passwordEncoder).encode("rawPassword");
        verify(userMapper).insert(newUser);
    }

    @Test
    @DisplayName("Delete user - success")
    void deleteUser_ShouldReturnTrue_WhenDeleted() {
        when(userMapper.deleteById(1L)).thenReturn(1);
        boolean result = userService.deleteUser(1L);
        assertThat(result).isTrue();
        verify(userMapper).deleteById(1L);
    }

    @Test
    @DisplayName("Delete user - not found")
    void deleteUser_ShouldReturnFalse_WhenNotFound() {
        when(userMapper.deleteById(99L)).thenReturn(0);
        boolean result = userService.deleteUser(99L);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Exists by username - found")
    void existsByUsername_ShouldReturnTrue_WhenFound() {
        when(userMapper.findByUsername("testuser")).thenReturn(testUser);
        assertThat(userService.existsByUsername("testuser")).isTrue();
    }

    @Test
    @DisplayName("Exists by username - not found")
    void existsByUsername_ShouldReturnFalse_WhenNotFound() {
        when(userMapper.findByUsername("nouser")).thenReturn(null);
        assertThat(userService.existsByUsername("nouser")).isFalse();
    }

    @Test
    @DisplayName("Change password - updates encoded password")
    void changePassword_ShouldUpdatePassword() {
        when(userMapper.findById(1L)).thenReturn(testUser);
        when(passwordEncoder.encode("newPassword")).thenReturn("newEncodedPassword");
        when(userMapper.update(any(User.class))).thenReturn(1);

        userService.changePassword(1L, "newPassword");

        verify(passwordEncoder).encode("newPassword");
        verify(userMapper).update(argThat(u -> "newEncodedPassword".equals(u.getPassword())));
    }
}
