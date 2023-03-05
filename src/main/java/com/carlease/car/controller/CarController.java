package com.carlease.car.controller;

import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.response.CarResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.carlease.car.service.CarService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/car")
public class CarController {
    private final CarService carService;
    private Logger logger= LoggerFactory.getLogger(CarController.class);

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(description = "create a car")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse createCar(@Valid @RequestBody CarRequest carRequest){
        return carService.createCar(carRequest);
    }

    @Operation(description = "update a car")
    @RequestMapping(method = RequestMethod.PUT,value = "/{carId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse updateCar(@Valid @RequestBody CarRequest carRequest,@PathVariable("carId") Integer carId) throws CarNotFoundException {
        return carService.updateCar(carRequest,carId);

    }

    @Operation(description = "retrieve list of cars")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<CarResponse> getCars(@PathParam("status") String status) throws CarException{
        return carService.getCars(status);
    }

    @Operation(description = "retrieve a car by car Id")
    @RequestMapping(method = RequestMethod.GET,value = "/{carId}")
    @ResponseStatus(HttpStatus.FOUND)
    public CarResponse getCarByCarId(@PathVariable("carId") Integer carId) throws CarNotFoundException{
        return carService.getCarByCarId(carId);
    }

    @Operation(description = "delete a car by car Id")
    @RequestMapping(method = RequestMethod.DELETE,value = "/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable("carId") Integer carId) throws CarNotFoundException{
        carService.deleteCar(carId);
    }
}