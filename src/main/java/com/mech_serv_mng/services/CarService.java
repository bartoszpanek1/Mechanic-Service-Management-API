package com.mech_serv_mng.services;

import com.mech_serv_mng.models.Car;
import com.mech_serv_mng.models.Customer;
import com.mech_serv_mng.repositories.CarRepository;
import com.mech_serv_mng.services.specifications.CarSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<Car> findCar(Integer id) {
        return carRepository.findById(id);
    }
    public List<Car> findMatchingCars(String regNum, String brand, String model, String color,Integer customerId){
        Specification<Car> spec = Specification
                .where(regNum==null?null: CarSpecifications.regNumEquals(regNum))
                .and(brand==null?null:CarSpecifications.brandContains(brand))
                .and(model==null?null:CarSpecifications.modelContains(model))
                .and(color==null?null:CarSpecifications.colorContains(color));
        List<Car> specCars = carRepository.findAll(spec);
        if(customerId!=null) {
            List<Car> customerCars = carRepository.findAll()
                    .stream().filter(c -> c.getCustomer().getId().equals(customerId)).collect(Collectors.toList());
            specCars.retainAll(customerCars);
        }
        return specCars;
    }

    public void deleteCar(Integer id){
        carRepository.deleteById(id);
    }
    public void addCar(Car car){
        toUpperCar(car);
        carRepository.save(car);
    }
    private void toUpperCar(Car car){
        car.setBrand(car.getBrand().toUpperCase());
        car.setColor(car.getColor().toUpperCase());
        car.setModel(car.getModel().toUpperCase());
        car.setRegNum(car.getRegNum().toUpperCase());
    }
}
