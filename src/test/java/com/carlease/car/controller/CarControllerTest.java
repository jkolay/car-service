package com.carlease.car.controller;

import com.carlease.car.config.CarStatus;
import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.request.CarUpdateStatusRequestModel;
import com.carlease.car.model.response.CarResponse;
import com.carlease.car.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

  @Mock private CarService carService;

  @InjectMocks private CarController controller;

  @Test
  public void createCarTest() {
    Mockito.when(carService.createCar(Mockito.any(CarRequest.class))).thenReturn(new CarResponse());
    Assertions.assertNotNull(controller.createCar(new CarRequest()));
  }

  @Test
  public void updateCarTest() throws CarNotFoundException, CarException {
    Mockito.when(carService.updateCar(Mockito.any(CarRequest.class), Mockito.anyInt()))
        .thenReturn(new CarResponse());
    Assertions.assertNotNull(controller.updateCar(new CarRequest(), 1));
  }

  @Test
  public void getCarsTest() throws CarException {
    Mockito.when(carService.getCars(Mockito.anyString())).thenReturn(new ArrayList<>());
    Assertions.assertNotNull(controller.getCars(CarStatus.ALL.getValue()));
  }

  @Test
  public void getCarByCarIdTest() throws CarNotFoundException {
    Mockito.when(carService.getCarByCarId(Mockito.anyInt())).thenReturn(new CarResponse());
    Assertions.assertNotNull(controller.getCarByCarId(1));
  }

  @Test
  public void updateCarStatusTest() throws CarNotFoundException, CarException {
    Mockito.when(
            carService.updateCarStatus(
                Mockito.anyInt(), Mockito.any(CarUpdateStatusRequestModel.class)))
        .thenReturn(new CarResponse());
    Assertions.assertNotNull(controller.updateCarStatus(1, new CarUpdateStatusRequestModel()));
  }
}
