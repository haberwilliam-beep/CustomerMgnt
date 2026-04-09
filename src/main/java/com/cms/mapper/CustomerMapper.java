package com.cms.mapper;

import com.cms.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {
    Customer findById(@Param("id") Long id);
    List<Customer> findAll();
    List<Customer> findByFilters(Map<String, Object> filters);
    int countByFilters(Map<String, Object> filters);
    List<Customer> findPage(@Param("offset") int offset, @Param("limit") int limit);
    int countAll();
    int insert(Customer customer);
    int update(Customer customer);
    int deleteById(@Param("id") Long id);
    List<Customer> searchByName(@Param("name") String name);
}
