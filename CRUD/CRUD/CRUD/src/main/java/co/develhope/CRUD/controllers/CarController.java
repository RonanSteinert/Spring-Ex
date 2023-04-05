package co.develhope.CRUD.controllers;

import co.develhope.CRUD.entities.Car;
import co.develhope.CRUD.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("")
    public Car createUser(@RequestBody Car car){
        car.setId(null);
        Car carSaved = carRepository.saveAndFlush(car);
        return carSaved;
    }

    @GetMapping("")
    public List<Car> getUsers(){
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getOptionalUser(@PathVariable String id){
        if(carRepository.existsById(id)){
            return carRepository.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @PatchMapping("/{id}")
    public Car editSingleCar(@PathVariable String id,@RequestBody Map<Object,Object> fields){
        Optional<Car> car1 = carRepository.findById(id);
        if(car1.isPresent()){
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Car.class, (String) key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field,car1.get(),value);
            });
            Car updatedCar = carRepository.saveAndFlush(car1.get());
            return updatedCar;
        } else {
           return new Car();
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteSingleCar(@PathVariable String id){
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    @DeleteMapping("")
    public void deleteAllCars(){
        carRepository.deleteAll();
    }
}
