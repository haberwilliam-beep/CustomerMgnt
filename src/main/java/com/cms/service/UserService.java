package com.cms.service;

import com.cms.mapper.UserMapper;
import com.cms.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(Long id);
    boolean existsByUsername(String username);
    void changePassword(Long userId, String newPassword);
}

@Slf4j
@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        log.debug("Finding user by id: {}", id);
        return userMapper.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        log.debug("Finding user by username: {}", username);
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        log.debug("Finding all users");
        return userMapper.findAll();
    }

    @Override
    @Transactional
    public User createUser(User user) {
        log.info("Creating new user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(LocalDateTime.now());
        user.setEnabled(true);
        userMapper.insert(user);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        log.info("Updating user: {}", user.getId());
        user.setUpdatedDate(LocalDateTime.now());
        userMapper.update(user);
        return user;
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        log.info("Deleting user: {}", id);
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.findByUsername(username) != null;
    }

    @Override
    @Transactional
    public void changePassword(Long userId, String newPassword) {
        log.info("Changing password for user: {}", userId);
        User user = userMapper.findById(userId);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setUpdatedDate(LocalDateTime.now());
            userMapper.update(user);
        }
    }
}
