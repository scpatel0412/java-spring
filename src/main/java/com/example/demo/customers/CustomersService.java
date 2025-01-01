package com.example.demo.customers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    public Customers saveCustomer(Customers customers) {
        return customersRepository.save(customers);
    }


    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Optional<Customers> getCustomerById(Long id) {
        return customersRepository.findById(id);
    }

    public void deleteCustomer(Long id) {
        customersRepository.deleteById(id);
    }
}
