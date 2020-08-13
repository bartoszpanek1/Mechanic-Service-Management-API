package com.mech_serv_mng.controllers;

import com.mech_serv_mng.DTOs.CustomerDTO;
import com.mech_serv_mng.models.Customer;
import com.mech_serv_mng.services.CustomerService;
import com.mech_serv_mng.util.DateUtils;
import com.mech_serv_mng.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public List<Customer> findCustomers(@RequestBody CustomerDTO dto) {
        return customerService.findMatchingCustomers(dto.getIdentNum(), dto.getName(), dto.getFamilyName(), dto.getPhoneNumber(), dto.getEmail(), dto.getBirthDate());
    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") Integer id) {
        return customerService.findCustomer(id);
    }

    @PostMapping("/customers")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO dto) {
        LocalDate birthDate=DateUtils.validateDate(dto.getBirthDate());
        if(StringUtils.isEmailCorrect(dto.getEmail())&& birthDate!=null){
            Customer customer = new Customer(null, dto.getIdentNum(), dto.getName(), dto.getFamilyName(), dto.getPhoneNumber(), dto.getEmail(), birthDate, null);
            customerService.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect parameter(s)");
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer id) {
        if(customerService.deleteCustomer(id)){
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided id does not exist in the database");
    }

    @PutMapping("/customers")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        if(StringUtils.isEmailCorrect(customer.getEmail())&&customer.getBirthDate()!=null){
            customerService.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect parameter(s)");
    }
}
