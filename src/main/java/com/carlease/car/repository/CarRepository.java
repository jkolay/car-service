package com.carlease.car.repository;

import com.carlease.car.persistence.CarDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Car repository class
 */
@Repository
public interface CarRepository extends JpaRepository<CarDao,Integer> {
    /**
     * returns the list of cars based on the status of the car
     * @param status
     * @return
     */
    List<CarDao> findByStatus(String status);

    /**
     * find the car by car id
     * @param carId
     * @return
     */
    CarDao findByCarId(Integer carId);
}
