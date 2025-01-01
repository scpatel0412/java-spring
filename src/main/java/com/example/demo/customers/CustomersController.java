package com.example.demo.customers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @PostMapping
    public Customers createCustomer(@RequestBody Customers customers){
        return customersService.saveCustomer(customers);
    }

    @GetMapping
    public List<Customers> getAllCustomers() {
        return customersService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> getEmployeeById(@PathVariable("id") Long id) {
        Optional<Customers> customers = customersService.getCustomerById(id);
        return customers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customers> updateEmployee(@PathVariable("id") Long id, @RequestBody Customers customers) {
        Optional<Customers> existingCustomer = customersService.getCustomerById(id);
        if (existingCustomer.isPresent()) {
            customers.setId(id);
            Customers updatedCustomer = customersService.saveCustomer(customers);
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        Optional<Customers> customers = customersService.getCustomerById(id);
        if (customers.isPresent()) {
            customersService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // // CREATE: Add new employee
    // @PostMapping
    // public Employee createEmployee(@RequestBody Employee employee) {
    //     return customersService.saveEmployee(employee);
    // }

    // // READ: Get all employees
    // @GetMapping
    // public List<Employee> getAllEmployees() {
    //     return customersService.getAllEmployees();
    // }

    // // READ: Get employee by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
    //     Optional<Employee> employee = customersService.getEmployeeById(id);
    //     return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    // // UPDATE: Update existing employee
    // @PutMapping("/{id}")
    // public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
    //     Optional<Employee> existingEmployee = customersService.getEmployeeById(id);
    //     if (existingEmployee.isPresent()) {
    //         employee.setId(id);
    //         Employee updatedCutomer = customersService.saveEmployee(employee);
    //         return ResponseEntity.ok(updatedCutomer);
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    // // DELETE: Delete employee by ID
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
    //     Optional<Employee> employee = customersService.getEmployeeById(id);
    //     if (employee.isPresent()) {
    //         customersService.deleteEmployee(id);
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.notFound().build();
    // }
}
