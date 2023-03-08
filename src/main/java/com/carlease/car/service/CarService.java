package com.carlease.car.service;

import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.request.CarUpdateStatusRequestModel;
import com.carlease.car.model.response.CarResponse;

import java.util.List;

/**
 * the car service interface
 */
public interface CarService {
    public CarResponse createCar(CarRequest carRequest);
    public CarResponse updateCar(CarRequest carRequest,Integer carId) throws CarNotFoundException, CarException;
    public List<CarResponse> getCars(String status) throws CarException;
    public CarResponse getCarByCarId(Integer carId) throws CarNotFoundException;
    public void deleteCar(Integer carId) throws CarNotFoundException, CarException;

    CarResponse updateCarStatus(Integer carId, CarUpdateStatusRequestModel updateStatusRequestModel) throws CarNotFoundException, CarException;
}
