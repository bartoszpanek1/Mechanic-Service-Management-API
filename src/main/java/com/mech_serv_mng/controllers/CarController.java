package com.mech_serv_mng.controllers;

import com.mech_serv_mng.DTOs.CarDTO;
import com.mech_serv_mng.models.Car;
import com.mech_serv_mng.models.Customer;
import com.mech_serv_mng.services.CarService;
import com.mech_serv_mng.services.CustomerService;
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
    public List<Car> findMatchingCars(@RequestParam(required = false) String regNum
            , @RequestParam(required = false) String brand
            , @RequestParam(required = false) String model
            , @RequestParam(required = false) String color
            , @RequestParam(required = false) Integer customerId){
        return carService.findMatchingCars(regNum,brand,model,color,customerId);
    }

    @GetMapping("/cars/{id}")
    public Optional<Car> findCar(@PathVariable("id") Integer id){
        return carService.findCar(id);
    }

    @PostMapping("/cars")
    public String addCar(@RequestBody CarDTO dto){
        Optional<Customer> customer = customerService.findCustomer(dto.getCustomerId());
        Car car = new Car(null, dto.getRegNum(),dto.getBrand(),dto.getModel(),dto.getColor(), customer.orElse(null),null);
        carService.addCar(car);
        return "ADDED";
    }

    @DeleteMapping("/cars/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        carService.deleteCar(id);
        return "DELETED";
    }

    @PutMapping("/cars")
    public String updateCar(@RequestBody Car customer){
        carService.addCar(customer);
        return "UPDATED";
    }
}
