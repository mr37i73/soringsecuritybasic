package com.eazybytes.soringsecuritybasic.controller;

import com.eazybytes.soringsecuritybasic.Model.Customer;
import com.eazybytes.soringsecuritybasic.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer) {
        String hashedPassword = passwordEncoder.encode(customer.getPwd());
        ResponseEntity response = null;
        customer.setPwd(hashedPassword);
        Customer registerCustomer = customerRepository.save(customer);
        if(customer.getId()>0) {
            response = ResponseEntity.status(HttpStatus.CREATED).body("User has been registered");
        }
        else {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error has been occurred");
        }
        return response;
    }
}
