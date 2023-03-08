package com.carlease.car.mapper;

import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.response.CarResponse;
import com.carlease.car.persistence.CarDao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/** The car service mapper class */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CarMapper {
  /**
   * maps the Car dao object to car response
   *
   * @param carDao
   * @return
   */
  CarResponse mapCarDaoTOCarResponse(CarDao carDao);

  /**
   * maps the car request object to car dao object
   *
   * @param carRequest
   * @return
   */
  CarDao mapCarRequestToCarDao(CarRequest carRequest);
}
