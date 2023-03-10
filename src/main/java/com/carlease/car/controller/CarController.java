package com.carlease.car.controller;

import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.response.CarResponse;
import com.carlease.car.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Car controller which consists of all the endpoints for car service */
@RestController
@RequestMapping(value = "api/v1/car")
public class CarController {
  private final CarService carService;

  @Autowired
  public CarController(CarService carService) {
    this.carService = carService;
  }

  /**
   * this is the endpoint implementation to create a car in the system
   *
   * @param carRequest the car details which needs to be created
   * @return the car response object
   */
  @Operation(description = "create a car")
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest carRequest) {
    return ResponseEntity.ok(carService.createCar(carRequest));
  }

  /**
   * this is the endpoint implementation to update a car details
   *
   * @param carRequest the car details
   * @param carId the car id
   * @return the customer response
   * @throws CarNotFoundException this gets thrown when the car not present
   * @throws CarException it gets thrown when the update request is for a leased car
   */
  @Operation(description = "update a car")
  @RequestMapping(method = RequestMethod.PUT, value = "/update/{carId}")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CarResponse> updateCar(
      @Valid @RequestBody CarRequest carRequest, @PathVariable("carId") Integer carId)
      throws CarNotFoundException, CarException {
    return ResponseEntity.ok(carService.updateCar(carRequest, carId));
  }

  /**
   * this is the endpoint implementation to retrieve list of cars by providing status as All,Leased
   * or Not-Leased
   *
   * @return the list of car response
   * @throws CarException this gets thrown when the status of the car is not valid
   */
  @Operation(description = "retrieve list of cars")
  @RequestMapping(method = RequestMethod.GET, value = "/retrieveAll")
  @ResponseStatus(HttpStatus.FOUND)
  public ResponseEntity<List<CarResponse>> getCars()
      throws CarException {
    return ResponseEntity.ok(carService.getCars());
  }

  /**
   * this is the endpoint implementation to retrieve a car details
   *
   * @param carId the car id
   * @return the car response object
   * @throws CarNotFoundException gets thrown when car is not found
   */
  @Operation(description = "retrieve a car by car Id")
  @RequestMapping(method = RequestMethod.GET, value = "/view/{carId}")
  @ResponseStatus(HttpStatus.FOUND)
  public ResponseEntity<CarResponse> getCarByCarId(@PathVariable("carId") Integer carId)
      throws CarNotFoundException {
    return ResponseEntity.ok(carService.getCarByCarId(carId));
  }


  /**
   * this is the endpoint implementation to delete a car
   *
   * @param carId the car id
   * @throws CarNotFoundException gets thrown when car is not found
   * @throws CarException gets thrown when car is leased
   */
  @Operation(description = "delete a car by car Id")
  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{carId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCar(@PathVariable("carId") Integer carId)
      throws CarNotFoundException, CarException {
    carService.deleteCar(carId);
  }


}
