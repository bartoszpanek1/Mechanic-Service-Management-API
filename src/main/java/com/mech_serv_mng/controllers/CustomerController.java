package com.mech_serv_mng.controllers;

import com.mech_serv_mng.DTOs.CustomerDTO;
import com.mech_serv_mng.models.Customer;
import com.mech_serv_mng.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public List<Customer> findCustomers(@RequestParam(required = false) String identNum
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) String familyName
            , @RequestParam(required = false) String phoneNumber
            , @RequestParam(required = false) String email
            , @RequestParam(required = false) String birthDate) {
        return customerService.findMatchingCustomers(identNum, name, familyName, phoneNumber, email, birthDate);

    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") Integer id) {
        return customerService.findCustomer(id);
    }

    @PostMapping("/customers")
    public String addCustomer(@RequestBody CustomerDTO dto){
        LocalDate birthDate = LocalDate.parse(dto.getBirthDate());
        Customer customer = new Customer(null, dto.getIdentNum(),dto.getName(),dto.getFamilyName(),dto.getPhoneNumber(),dto.getEmail(),birthDate,null);
        customerService.addCustomer(customer);
        return "ADDED";
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id){
        customerService.deleteCustomer(id);
        return "DELETED";
    }

    @PutMapping("/customers")
    public String updateCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return "UPDATED";
    }

}
