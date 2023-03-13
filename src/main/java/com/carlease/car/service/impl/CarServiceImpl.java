package com.carlease.car.service.impl;

import com.carlease.car.exception.CarException;
import com.carlease.car.exception.CarNotFoundException;
import com.carlease.car.mapper.CarMapper;
import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.response.CarResponse;
import com.carlease.car.persistence.CarDao;
import com.carlease.car.repository.CarRepository;
import com.carlease.car.service.CarService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** The implementation of the car service class */
@Service
public class CarServiceImpl implements CarService {
  private final CarRepository carRepository;
  private final CarMapper carMapper;
  private final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

  @Autowired
  public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
    this.carRepository = carRepository;
    this.carMapper = carMapper;
  }
  /**
   * this is the implementation to create a car in the system
   *
   * @param carRequest the car details which needs to be created
   * @return the car response object
   */
  @Override
  public CarResponse createCar(CarRequest carRequest) {
    CarDao carDao = carMapper.mapCarRequestToCarDao(carRequest);
    logger.info("New car details added.");
    return carMapper.mapCarDaoTOCarResponse(carRepository.save(carDao));
  }
  /**
   * this is the implementation to update a car details
   *
   * @param carRequest the car details
   * @param carId the car id
   * @return the customer response
   * @throws CarNotFoundException this gets thrown when the car not present
   * @throws CarException it gets thrown when the update request is for a leased car
   */
  @Override
  public CarResponse updateCar(CarRequest carRequest, Integer carId)
      throws CarNotFoundException, CarException {
    CarDao existingCarDao = carRepository.findByCarId(carId);
    if (existingCarDao != null) {
        CarDao carDao = carMapper.mapCarRequestToCarDao(carRequest);
        carDao.setCarId(existingCarDao.getCarId());
        carDao.setCreatedAt(existingCarDao.getCreatedAt());
        carDao.setUpdatedAt(LocalDateTime.now());
        logger.info("Car details has been updated");
        return carMapper.mapCarDaoTOCarResponse(carRepository.save(carDao));
      }
    throw new CarNotFoundException("Car details is not present for the car");
  }
  /**
   * this is the implementation to retrieve list of cars by providing status as All,Leased or
   * Not-Leased
   *
   * @return the list of car response
   * @throws CarException this gets thrown when the status of the car is not valid
   */
  @Override
  public List<CarResponse> getCars() throws CarException {

      logger.info("Retrieving all car details");
      return carRepository.findAll().stream()
          .map(carDao -> carMapper.mapCarDaoTOCarResponse(carDao))
          .collect(Collectors.toList());
  }
  /**
   * this is the implementation to retrieve a car details
   *
   * @param carId the car id
   * @return the car response object
   * @throws CarNotFoundException gets thrown when car is not found
   */
  @Override
  public CarResponse getCarByCarId(Integer carId) throws CarNotFoundException {
    CarDao existingCarDao = carRepository.findByCarId(carId);
    if (existingCarDao != null) {
      return carMapper.mapCarDaoTOCarResponse(existingCarDao);
    }
    throw new CarNotFoundException("Car details is not present for the car");
  }
  /**
   * this is the implementation to delete a car
   *
   * @param carId the car id
   * @throws CarNotFoundException gets thrown when car is not found
   * @throws CarException gets thrown when car is leased
   */
  @Override
  public void deleteCar(Integer carId) throws CarNotFoundException, CarException {
    CarDao existingCarDao =
        Optional.of(carRepository.findByCarId(carId))
            .orElseThrow(() -> new CarNotFoundException("Car details is not present for the car"));
    carRepository.delete(existingCarDao);
  }
}
