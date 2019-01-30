package com.westbank.service;

import com.westbank.entity.Customer;
import com.westbank.helper.CustomerMapper;
import com.westbank.repository.CustomerRepository;
import com.westbank.web.form.LoanForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer authenticate(String email, String pin) {
        return repository.findByEmailAndPassword(email, pin).orElse(null);
    }

    public Customer updateProfile(Long customerId, LoanForm form) {
        Customer customer = new CustomerMapper().from(form);
        if (customerId != null)
            customer.setId(customerId);
        return repository.save(customer);
    }
}
