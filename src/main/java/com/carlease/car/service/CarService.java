package com.carlease.car.service;

import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.request.CarUpdateStatusRequestModel;
import com.carlease.car.model.response.CarResponse;
import java.util.List;

/** the car service interface */
public interface CarService {
  CarResponse createCar(CarRequest carRequest);

  CarResponse updateCar(CarRequest carRequest, Integer carId)
      throws CarNotFoundException, CarException;

  List<CarResponse> getCars() throws CarException;

  CarResponse getCarByCarId(Integer carId) throws CarNotFoundException;

  void deleteCar(Integer carId) throws CarNotFoundException, CarException;

}
