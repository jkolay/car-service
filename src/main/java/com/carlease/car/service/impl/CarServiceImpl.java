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
    carDao.setStatus(CarStatus.NEW.getValue());
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
      if (existingCarDao.getStatus().equalsIgnoreCase(CarStatus.NEW.getValue())) {
        CarDao carDao = carMapper.mapCarRequestToCarDao(carRequest);
        carDao.setCarId(existingCarDao.getCarId());
        carDao.setStatus(existingCarDao.getStatus());
        carDao.setCreatedAt(existingCarDao.getCreatedAt());
        carDao.setUpdatedAt(LocalDateTime.now());
        logger.info("Car details has been updated");
        return carMapper.mapCarDaoTOCarResponse(carRepository.save(carDao));
      } else {
        logger.error("car is already leased");
        throw new CarException("Car details can not be updated for leased cars");
      }
    }
    throw new CarNotFoundException("Car details is not present for the car");
  }
  /**
   * this is the implementation to retrieve list of cars by providing status as All,Leased or
   * Not-Leased
   *
   * @param status the status of the car
   * @return the list of car response
   * @throws CarException this gets thrown when the status of the car is not valid
   */
  @Override
  public List<CarResponse> getCars(String status) throws CarException {
    if (status.equalsIgnoreCase(CarStatus.ALL.getValue())) {
      logger.info("Retrieving all car details");
      return carRepository.findAll().stream()
          .map(carDao -> carMapper.mapCarDaoTOCarResponse(carDao))
          .collect(Collectors.toList());
    } else if (status.equalsIgnoreCase(CarStatus.LEASED.getValue())
        || status.equalsIgnoreCase(CarStatus.NEW.getValue())) {
      logger.info("Retrieving car details for status {}", status);
      return carRepository.findByStatus(status).stream()
          .map(carDao -> carMapper.mapCarDaoTOCarResponse(carDao))
          .collect(Collectors.toList());

    } else {
      logger.error("Incorrect status value provided for retrieval {}", status);
      throw new CarException("Status value can be All, Leased, not-leased");
    }
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
    if (existingCarDao.getStatus().equalsIgnoreCase(CarStatus.NEW.getValue())) {
      carRepository.delete(existingCarDao);
    } else {
      logger.error("Leased car details can not be deleted");
      throw new CarException("Leased car details can not be deleted");
    }
  }
  /**
   * this is the implementation to update a car status
   *
   * @param carId the car id
   * @param updateStatusRequestModel the new status of the car
   * @return the updated car object
   * @throws CarNotFoundException gets thrown when car is not found
   * @throws CarException gets thrown when the car status is not valid
   */
  @Override
  public CarResponse updateCarStatus(
      Integer carId, CarUpdateStatusRequestModel updateStatusRequestModel)
      throws CarNotFoundException, CarException {
    CarDao existingCarDao =
        Optional.of(carRepository.findByCarId(carId))
            .orElseThrow(() -> new CarNotFoundException("Car details is not present for the car"));
    if (updateStatusRequestModel.getStatus().equalsIgnoreCase(CarStatus.LEASED.getValue())
        || updateStatusRequestModel.getStatus().equalsIgnoreCase(CarStatus.NEW.getValue())) {
      logger.info("car status getting updated");
      existingCarDao.setStatus(updateStatusRequestModel.getStatus());
      return carMapper.mapCarDaoTOCarResponse(carRepository.save(existingCarDao));
    }
    throw new CarException("Car status can not be updated");
  }
}
