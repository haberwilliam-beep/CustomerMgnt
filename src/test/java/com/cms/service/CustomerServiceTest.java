package com.cms.service;

import com.cms.mapper.CustomerMapper;
import com.cms.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerService Tests")
class CustomerServiceTest {

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = Customer.builder()
                .id(1L)
                .nameEn("John Smith")
                .nameAr("جون سميث")
                .phone("+1-555-0101")
                .email("john.smith@example.com")
                .address("123 Main St")
                .status("ACTIVE")
                .createdBy("admin")
                .updatedBy("admin")
                .build();
    }

    @Test
    @DisplayName("Find customer by ID - success")
    void findById_ShouldReturnCustomer() {
        when(customerMapper.findById(1L)).thenReturn(testCustomer);
        Customer result = customerService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNameEn()).isEqualTo("John Smith");
        verify(customerMapper).findById(1L);
    }

    @Test
    @DisplayName("Find customer by ID - not found")
    void findById_ShouldReturnNull_WhenNotFound() {
        when(customerMapper.findById(99L)).thenReturn(null);
        assertThat(customerService.findById(99L)).isNull();
    }

    @Test
    @DisplayName("Find all customers")
    void findAll_ShouldReturnAllCustomers() {
        List<Customer> customers = Arrays.asList(testCustomer,
                Customer.builder().id(2L).nameEn("Jane Doe").status("ACTIVE").build());
        when(customerMapper.findAll()).thenReturn(customers);
        List<Customer> result = customerService.findAll();
        assertThat(result).hasSize(2);
        verify(customerMapper).findAll();
    }

    @Test
    @DisplayName("Create customer - sets audit fields")
    void createCustomer_ShouldSetAuditFieldsAndSave() {
        Customer newCustomer = Customer.builder()
                .nameEn("New Customer")
                .email("new@example.com")
                .build();
        when(customerMapper.insert(any(Customer.class))).thenReturn(1);

        Customer result = customerService.createCustomer(newCustomer, "testUser");

        assertThat(result.getCreatedBy()).isEqualTo("testUser");
        assertThat(result.getUpdatedBy()).isEqualTo("testUser");
        assertThat(result.getStatus()).isEqualTo("ACTIVE");
        assertThat(result.getCreatedDate()).isNotNull();
        assertThat(result.getUpdatedDate()).isNotNull();
        verify(customerMapper).insert(newCustomer);
    }

    @Test
    @DisplayName("Create customer - uses existing status if set")
    void createCustomer_ShouldPreserveExistingStatus() {
        Customer newCustomer = Customer.builder()
                .nameEn("Inactive Customer")
                .status("INACTIVE")
                .build();
        when(customerMapper.insert(any(Customer.class))).thenReturn(1);

        Customer result = customerService.createCustomer(newCustomer, "admin");
        assertThat(result.getStatus()).isEqualTo("INACTIVE");
    }

    @Test
    @DisplayName("Update customer - sets updatedBy and updatedDate")
    void updateCustomer_ShouldSetUpdatedFields() {
        when(customerMapper.update(any(Customer.class))).thenReturn(1);

        Customer result = customerService.updateCustomer(testCustomer, "operator");

        assertThat(result.getUpdatedBy()).isEqualTo("operator");
        assertThat(result.getUpdatedDate()).isNotNull();
        verify(customerMapper).update(testCustomer);
    }

    @Test
    @DisplayName("Delete customer - success")
    void deleteCustomer_ShouldReturnTrue() {
        when(customerMapper.deleteById(1L)).thenReturn(1);
        assertThat(customerService.deleteCustomer(1L)).isTrue();
        verify(customerMapper).deleteById(1L);
    }

    @Test
    @DisplayName("Delete customer - not found")
    void deleteCustomer_ShouldReturnFalse_WhenNotFound() {
        when(customerMapper.deleteById(99L)).thenReturn(0);
        assertThat(customerService.deleteCustomer(99L)).isFalse();
    }

    @Test
    @DisplayName("Count by filters")
    void countByFilters_ShouldReturnCount() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("status", "ACTIVE");
        when(customerMapper.countByFilters(filters)).thenReturn(5);
        assertThat(customerService.countByFilters(filters)).isEqualTo(5);
        verify(customerMapper).countByFilters(filters);
    }

    @Test
    @DisplayName("Search by name")
    void searchByName_ShouldReturnMatchingCustomers() {
        when(customerMapper.searchByName("John")).thenReturn(Arrays.asList(testCustomer));
        List<Customer> result = customerService.searchByName("John");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNameEn()).isEqualTo("John Smith");
        verify(customerMapper).searchByName("John");
    }
}
