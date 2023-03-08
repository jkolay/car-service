package com.carlease.car.service.impl;

import com.carlease.car.config.CarStatus;
import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.mapper.CarMapper;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.request.CarUpdateStatusRequestModel;
import com.carlease.car.model.response.CarResponse;
import com.carlease.car.persistence.CarDao;
import com.carlease.car.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    @InjectMocks
    CarServiceImpl carService;
    @Mock
    private  CarRepository carRepository;
    @Mock
    private  CarMapper carMapper;

    @Test
    public void createCarTest(){
        CarDao carDao = Mockito.mock(CarDao.class);
        CarResponse carResponse=Mockito.mock(CarResponse.class);
        Mockito.when(carMapper.mapCarRequestToCarDao(Mockito.any(CarRequest.class))).thenReturn(carDao);
        Mockito.when(carRepository.save(Mockito.any(CarDao.class))).thenReturn(carDao);
        Mockito.when( carMapper.mapCarDaoTOCarResponse(Mockito.any(CarDao.class))).thenReturn(carResponse);

        CarRequest carRequest= new CarRequest("abc","test","x123",4,9.0,11111.0,13456.0,45000L);
        Assertions.assertNotNull(carService.createCar(carRequest));

    }

    @Test
    public void updateCarTest() throws CarNotFoundException, CarException {
        CarDao existingCarDao = Mockito.mock(CarDao.class);
        CarDao carDao = Mockito.mock(CarDao.class);
        CarResponse carResponse=Mockito.mock(CarResponse.class);
        Mockito.when(carRepository.findByCarId(Mockito.anyInt())).thenReturn(existingCarDao);
        Mockito.when(carMapper.mapCarRequestToCarDao(Mockito.any(CarRequest.class))).thenReturn(carDao);
        Mockito.when(existingCarDao.getCarId()).thenReturn(1);
        Mockito.when(existingCarDao.getStatus()).thenReturn(CarStatus.NEW.getValue());
        Mockito.when(existingCarDao.getCreatedAt()).thenReturn(LocalDateTime.now());
        Mockito.when(carRepository.save(Mockito.any(CarDao.class))).thenReturn(carDao);
        Mockito.when( carMapper.mapCarDaoTOCarResponse(Mockito.any(CarDao.class))).thenReturn(carResponse);

        CarRequest carRequest= new CarRequest("abc","test","x123",4,9.0,11111.0,13456.0,45000L);
        Assertions.assertNotNull(carService.updateCar(carRequest,1));


    }

    @Test
    public void getCarsTest() throws CarException {
        CarDao carDao = Mockito.mock(CarDao.class);
        List<CarDao> carDaoList=new ArrayList<>();
        carDaoList.add(carDao);
        CarResponse carResponse=Mockito.mock(CarResponse.class);

        Mockito.when(carRepository.findAll()).thenReturn(carDaoList);
        Mockito.when( carMapper.mapCarDaoTOCarResponse(Mockito.any(CarDao.class))).thenReturn(carResponse);

        Assertions.assertNotNull(carService.getCars(CarStatus.ALL.getValue()));
    }

    @Test
    public void getCarByCarIdTest() throws CarNotFoundException {
        CarDao carDao = Mockito.mock(CarDao.class);
        CarResponse carResponse=Mockito.mock(CarResponse.class);
        Mockito.when(carRepository.findByCarId(Mockito.anyInt())).thenReturn(carDao);
        Mockito.when( carMapper.mapCarDaoTOCarResponse(Mockito.any(CarDao.class))).thenReturn(carResponse);
        Assertions.assertNotNull(carService.getCarByCarId(1));

    }

    @Test
    public void deleteCarTestWithException(){
        CarDao carDao = Mockito.mock(CarDao.class);

        Mockito.when(carRepository.findByCarId(Mockito.anyInt())).thenReturn(carDao);
        Mockito.when(carDao.getStatus()).thenReturn(CarStatus.LEASED.getValue());
        Assertions.assertThrows(CarException.class,()->carService.deleteCar(1));

    }

    @Test
    public void updateCarStatusTest() throws CarNotFoundException, CarException {
        CarDao existingCarDao = Mockito.mock(CarDao.class);
        CarResponse carResponse=Mockito.mock(CarResponse.class);
        Mockito.when(carRepository.findByCarId(Mockito.anyInt())).thenReturn(existingCarDao);
        Mockito.when(carRepository.save(Mockito.any(CarDao.class))).thenReturn(existingCarDao);
        Mockito.when( carMapper.mapCarDaoTOCarResponse(Mockito.any(CarDao.class))).thenReturn(carResponse);

        Assertions.assertNotNull(carService.updateCarStatus(1,new CarUpdateStatusRequestModel(CarStatus.NEW.getValue())));

    }


}
