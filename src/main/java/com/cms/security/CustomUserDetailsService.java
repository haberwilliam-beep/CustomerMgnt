package com.cms.security;

import com.cms.mapper.UserMapper;
import com.cms.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private static final org.apache.logging.log4j.Logger securityLogger =
            org.apache.logging.log4j.LogManager.getLogger("security");

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username: {}", username);
        User user = userMapper.findByUsername(username);
        if (user == null) {
            securityLogger.warn("User not found: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }
        securityLogger.info("User loaded successfully: {}", username);
        String role = "ROLE_" + user.getRole().toUpperCase();
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(role)))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.isEnabled())
                .build();
    }
}
