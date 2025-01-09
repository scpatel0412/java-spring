package com.example.demo.customers;

import com.example.demo.extras.ResponseObject;
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
    public ResponseObject<Customers, String> createCustomer(
        @RequestBody Customers customers
    ) {
        try {
            Customers savedCustomer = customersService.saveCustomer(customers);
            return new ResponseObject<>(true, savedCustomer, 200, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseObject<>(false, null, 500, e.getMessage());
        }
    }

    @GetMapping
    public ResponseObject<List<Customers>, String> getAllCustomers() {
        try {
            List<Customers> getCustomer = customersService.getAllCustomers();
            return new ResponseObject<>(true, getCustomer, 200, null);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseObject<>(false, null, 500, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseObject<ResponseEntity<Customers>, String> getEmployeeById(
        @PathVariable("id") Long id
    ) {
        try {
            Optional<Customers> customers = customersService.getCustomerById(
                id
            );
            return new ResponseObject<>(
                true,
                customers
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build()),
                200,
                null
            );
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseObject<>(false, null, 500, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseObject<ResponseEntity<Customers>, String> updateEmployee(
        @PathVariable("id") Long id,
        @RequestBody Customers customers
    ) {
        try {
            Optional<Customers> existingCustomer =
                customersService.getCustomerById(id);
            if (existingCustomer.isPresent()) {
                customers.setId(id);
                Customers updatedCustomer = customersService.saveCustomer(
                    customers
                );
                return new ResponseObject<>(
                    true,
                    ResponseEntity.ok(updatedCustomer),
                    200,
                    null
                );
            }
            return new ResponseObject<>(
                true,
                ResponseEntity.notFound().build(),
                200,
                null
            );
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseObject<>(false, null, 500, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseObject<ResponseEntity<String>, String> deleteCustomer(
        @PathVariable("id") Long id
    ) {
        try {
            Optional<Customers> customers = customersService.getCustomerById(
                id
            );
            if (customers.isPresent()) {
                customersService.deleteCustomer(id);
                return new ResponseObject<>(
                    true,
                    ResponseEntity.noContent().build(),
                    200,
                    null
                );
            }
            return new ResponseObject<>(
                true,
                ResponseEntity.notFound().build(),
                200,
                null
            );
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseObject<>(false, null, 500, e.getMessage());
        }
    }
}
