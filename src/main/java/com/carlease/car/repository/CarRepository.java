package com.carlease.car.repository;

import com.carlease.car.persistence.CarDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Car repository class */
@Repository
public interface CarRepository extends JpaRepository<CarDao, Integer> {


  /**
   * find the car by car id
   *
   * @param carId
   * @return
   */
  CarDao findByCarId(Integer carId);
}
