package com.mech_serv_mng.services;

import com.mech_serv_mng.models.Customer;
import com.mech_serv_mng.repositories.CustomerRepository;
import com.mech_serv_mng.services.specifications.CustomerSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomer(Integer id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findMatchingCustomers(String identNum, String name, String familyName, String phoneNumber, String email, String birthDate) {
        Specification<Customer> spec = Specification
                .where(identNum == null ? null : CustomerSpecifications.identNumContains(identNum))
                .and(name == null ? null : CustomerSpecifications.nameContains(name))
                .and(familyName == null ? null : CustomerSpecifications.familyNameContains(familyName))
                .and(phoneNumber == null ? null : CustomerSpecifications.phoneNumberContains(phoneNumber))
                .and(email == null ? null : CustomerSpecifications.emailContains(email))
                .and(birthDate == null ? null : CustomerSpecifications.birthDateEquals(birthDate));
        return customerRepository.findAll(spec);
    }

    public void addCustomer(Customer customer) {
        toUpperCustomer(customer);
        customerRepository.save(customer);
    }

    public boolean deleteCustomer(Integer id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void toUpperCustomer(Customer customer) {
        customer.setIdentNum(customer.getIdentNum().toUpperCase());
        customer.setName(customer.getName().toUpperCase());
        customer.setFamilyName(customer.getFamilyName().toUpperCase());
        customer.setPhoneNumber(customer.getPhoneNumber().toUpperCase());
    }
}
