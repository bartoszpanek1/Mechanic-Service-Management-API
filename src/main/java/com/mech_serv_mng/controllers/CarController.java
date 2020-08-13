package com.mech_serv_mng.controllers;

import com.mech_serv_mng.DTOs.CarDTO;
import com.mech_serv_mng.models.Car;
import com.mech_serv_mng.models.Customer;
import com.mech_serv_mng.services.CarService;
import com.mech_serv_mng.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {
    private final CarService carService;
    private final CustomerService customerService;

    public CarController(CarService carService, CustomerService customerService) {
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping("/cars")
    public List<Car> findMatchingCars(@RequestBody CarDTO dto) {
        return carService.findMatchingCars(dto.getRegNum(), dto.getBrand(),dto.getModel(), dto.getColor(), dto.getCustomerId());
    }

    @GetMapping("/cars/{id}")
    public Optional<Car> findCar(@PathVariable("id") Integer id) {
        return carService.findCar(id);
    }

    @PostMapping("/cars")
    public ResponseEntity<String> addCar(@RequestBody CarDTO dto) {
        Optional<Customer> customer = customerService.findCustomer(dto.getCustomerId());
        Car car = new Car(null, dto.getRegNum(), dto.getBrand(), dto.getModel(), dto.getColor(), customer.orElse(null), null);
        carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable("id") Integer id) {
        if(carService.deleteCar(id)){
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided id does not exist in the database");
    }

    @PutMapping("/cars")
    public ResponseEntity<String> updateCar(@RequestBody Car customer) {
        carService.addCar(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Updated");
    }
}
