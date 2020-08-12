package com.mech_serv_mng.controllers;

import com.mech_serv_mng.DTOs.FindMatchingServicesDTO;
import com.mech_serv_mng.DTOs.ServiceOrderedDTO;
import com.mech_serv_mng.models.Car;
import com.mech_serv_mng.models.ServiceOrdered;
import com.mech_serv_mng.services.CarService;
import com.mech_serv_mng.services.ServiceOrderedService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ServiceOrderedController {
    private final ServiceOrderedService serviceOrderedService;
    private final CarService carService;
    public ServiceOrderedController(ServiceOrderedService serviceOrderedService, CarService carService) {
        this.serviceOrderedService = serviceOrderedService;
        this.carService = carService;
    }

    @GetMapping("/services")
    public List<ServiceOrdered> findMatchingServices(@RequestBody FindMatchingServicesDTO dto) {
        System.out.println(dto);
        return serviceOrderedService.findMatchingServicesOrdered(dto.getPriceLow(), dto.getPriceHigh(), dto.getDescription(),
                dto.getAcceptedDateLow(), dto.getAcceptedDateHigh(), dto.getDeadlineLow(), dto.getDeadlineHigh(), dto.getCompleted(), dto.getCustomerId());
    }

    @GetMapping("/services/{id}")
    public Optional<ServiceOrdered> findService(@PathVariable("id") Integer id) {
        return serviceOrderedService.findServiceOrdered(id);
    }

    @PostMapping("/services")
    public String addService(@RequestBody ServiceOrderedDTO dto) {
        LocalDate accepted = LocalDate.parse(dto.getAccepted());
        LocalDate deadline = LocalDate.parse(dto.getAccepted());
        Car car = carService.findCar(dto.getCarId()).orElse(null);
        ServiceOrdered service = new ServiceOrdered(null, dto.getPrice(), dto.getDescription(), accepted, deadline, dto.getCompleted(),car);
        serviceOrderedService.addService(service);
        return "ADDED";
    }

    @DeleteMapping("/services/{id}")
    public String deleteService(@PathVariable("id") Integer id) {
        serviceOrderedService.deleteServiceOrdered(id);
        return "DELETED";
    }

    @PutMapping("/services")
    public String updateService(@RequestBody ServiceOrdered service){
        System.out.println(service);
        serviceOrderedService.addService(service);
        return "UPDATED";
    }
}
