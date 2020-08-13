package com.mech_serv_mng.controllers;

import com.mech_serv_mng.DTOs.FindMatchingServicesDTO;
import com.mech_serv_mng.DTOs.ServiceOrderedDTO;
import com.mech_serv_mng.models.Car;
import com.mech_serv_mng.models.ServiceOrdered;
import com.mech_serv_mng.services.CarService;
import com.mech_serv_mng.services.ServiceOrderedService;
import com.mech_serv_mng.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                dto.getAcceptedDateLow(), dto.getAcceptedDateHigh(), dto.getDeadlineLow(), dto.getDeadlineHigh(), dto.getCompleted(             ), dto.getCustomerId());
    }

    @GetMapping("/services/{id}")
    public Optional<ServiceOrdered> findService(@PathVariable("id") Integer id) {
        return serviceOrderedService.findServiceOrdered(id);
    }

    @PostMapping("/services")
    public ResponseEntity<String> addService(@RequestBody ServiceOrderedDTO dto) {
        LocalDate accepted = DateUtils.validateDate(dto.getAccepted());
        LocalDate deadline = DateUtils.validateDate(dto.getDeadline());
        if(accepted!=null&&deadline!=null){
            Car car = carService.findCar(dto.getCarId()).orElse(null);
            ServiceOrdered service = new ServiceOrdered(null, dto.getPrice(), dto.getDescription(), accepted, deadline, dto.getCompleted(), car);
            serviceOrderedService.addService(service);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect parameter(s)");
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<String> deleteService(@PathVariable("id") Integer id) {
        if(serviceOrderedService.deleteServiceOrdered(id)){
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided id does not exist in the database");
    }

    @PutMapping("/services")
    public ResponseEntity<String> updateService(@RequestBody ServiceOrdered service) {
        if(service.getAccepted()!=null&&service.getDeadline()!=null){
            serviceOrderedService.addService(service);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Updated");
    }
}
