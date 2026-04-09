package com.cms.dao;

import com.cms.model.User;

import java.util.List;

public interface UserDAO {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    int save(User user);
    int update(User user);
    int delete(Long id);
}
