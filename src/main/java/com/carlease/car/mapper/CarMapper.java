package com.carlease.car.mapper;

import com.carlease.car.model.request.CarRequest;
import com.carlease.car.model.response.CarResponse;
import com.carlease.car.persistence.CarDao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CarMapper {

    CarResponse mapCarDaoTOCarResponse(CarDao carDao);
    CarDao mapCarRequestToCarDao(CarRequest carRequest);
}
