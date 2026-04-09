package com.cms.dao;

import com.cms.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerDAO {
    Customer findById(Long id);
    List<Customer> findAll();
    List<Customer> findByFilters(Map<String, Object> filters);
    int countByFilters(Map<String, Object> filters);
    int save(Customer customer);
    int update(Customer customer);
    int delete(Long id);
}
