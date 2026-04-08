package com.cms.service;

import com.cms.mapper.CustomerMapper;
import com.cms.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    Customer findById(Long id);
    List<Customer> findAll();
    List<Customer> findByFilters(Map<String, Object> filters);
    int countByFilters(Map<String, Object> filters);
    Customer createCustomer(Customer customer, String username);
    Customer updateCustomer(Customer customer, String username);
    boolean deleteCustomer(Long id);
    List<Customer> searchByName(String name);
}

@Slf4j
@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    @Override
    public Customer findById(Long id) {
        log.debug("Finding customer by id: {}", id);
        return customerMapper.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Finding all customers");
        return customerMapper.findAll();
    }

    @Override
    public List<Customer> findByFilters(Map<String, Object> filters) {
        log.debug("Finding customers by filters: {}", filters);
        return customerMapper.findByFilters(filters);
    }

    @Override
    public int countByFilters(Map<String, Object> filters) {
        return customerMapper.countByFilters(filters);
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer, String username) {
        log.info("Creating customer: {} by user: {}", customer.getNameEn(), username);
        customer.setCreatedDate(LocalDateTime.now());
        customer.setUpdatedDate(LocalDateTime.now());
        customer.setCreatedBy(username);
        customer.setUpdatedBy(username);
        if (customer.getStatus() == null) {
            customer.setStatus("ACTIVE");
        }
        customerMapper.insert(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer, String username) {
        log.info("Updating customer: {} by user: {}", customer.getId(), username);
        customer.setUpdatedDate(LocalDateTime.now());
        customer.setUpdatedBy(username);
        customerMapper.update(customer);
        return customer;
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Long id) {
        log.info("Deleting customer: {}", id);
        return customerMapper.deleteById(id) > 0;
    }

    @Override
    public List<Customer> searchByName(String name) {
        log.debug("Searching customers by name: {}", name);
        return customerMapper.searchByName(name);
    }
}
