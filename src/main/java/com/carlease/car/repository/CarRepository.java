package com.carlease.car.repository;

import com.carlease.car.persistence.CarDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarDao,Integer> {

    List<CarDao> findByStatus(String status);

    CarDao findByCarId(Integer carId);
}
