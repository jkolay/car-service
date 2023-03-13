package com.carlease.car.mapper;

import com.carlease.car.model.request.UserRequestModel;
import com.carlease.car.persistence.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    Customer mapUserRequestModelToCustomer(UserRequestModel userRequestModel);
}
