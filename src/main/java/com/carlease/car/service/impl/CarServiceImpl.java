package com.carlease.car.service.impl;

import com.carlease.car.mapper.CarMapper;
import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.response.CarResponse;
import com.carlease.car.persistence.CarDao;
import com.carlease.car.repository.CarRepository;
import com.carlease.car.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private Logger logger= LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarResponse createCar(CarRequest carRequest) {
        CarDao carDao=carMapper.mapCarRequestToCarDao(carRequest);
        carDao.setStatus("Not-Leased");
        return carMapper.mapCarDaoTOCarResponse(carRepository.save(carDao));
    }

    @Override
    public CarResponse updateCar(CarRequest carRequest, Integer carId) throws CarNotFoundException {
        CarDao existingCarDao= carRepository.findByCarId(carId);
        if(existingCarDao!=null){
            CarDao carDao=carMapper.mapCarRequestToCarDao(carRequest);
            carDao.setCarId(existingCarDao.getCarId());
            carDao.setStatus(existingCarDao.getStatus());
            carDao.setCreatedAt(existingCarDao.getCreatedAt());
            return carMapper.mapCarDaoTOCarResponse(carRepository.save(carDao));
        }
        throw new CarNotFoundException("Car details is not present for the car");
    }

    @Override
    public List<CarResponse> getCars(String status) throws CarException {
        if(status.equalsIgnoreCase("All")){
            return carRepository.findAll().stream().map(carDao -> carMapper.mapCarDaoTOCarResponse(carDao)).collect(Collectors.toList());
        }
        else if(status.equalsIgnoreCase("Leased") || status.equalsIgnoreCase("Not-Leased")){
            return carRepository.findByStatus(status).stream().map(carDao -> carMapper.mapCarDaoTOCarResponse(carDao)).collect(Collectors.toList());

        }
       else{
           throw new CarException("status value can be All,Leased, not-leased");
        }
    }

    @Override
    public CarResponse getCarByCarId(Integer carId) throws CarNotFoundException {
        CarDao existingCarDao= carRepository.findByCarId(carId);
        if(existingCarDao!=null){
            return carMapper.mapCarDaoTOCarResponse(existingCarDao);
        }
        throw new CarNotFoundException("Car details is not present for the car");
    }

    @Override
    public void deleteCar(Integer carId) throws CarNotFoundException {
        CarDao existingCarDao= carRepository.findByCarId(carId);
        if(existingCarDao!=null){
          carRepository.delete(existingCarDao);
        }
        else {
            throw new CarNotFoundException("Car details is not present for the car");
        }

    }
}
