package com.carlease.car.service;

import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.response.CarResponse;

import java.util.List;

public interface CarService {
    public CarResponse createCar(CarRequest carRequest);
    public CarResponse updateCar(CarRequest carRequest,Integer carId) throws CarNotFoundException;
    public List<CarResponse> getCars(String status) throws CarException;
    public CarResponse getCarByCarId(Integer carId) throws CarNotFoundException;
    public void deleteCar(Integer carId) throws CarNotFoundException;
}
