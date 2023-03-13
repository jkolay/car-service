package com.carlease.car.service;

import com.carlease.car.model.request.UserRequestModel;
import com.carlease.car.persistence.Customer;import org.springframework.http.ResponseEntity;import org.springframework.security.core.Authentication;

public interface UserManagementService {
  ResponseEntity<String> registerUser(UserRequestModel userManagementService);

  Customer getUserDetailsAfterLogin(Authentication authentication);
}
