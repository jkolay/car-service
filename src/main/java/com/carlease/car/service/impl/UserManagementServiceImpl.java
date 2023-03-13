package com.carlease.car.service.impl;

import com.carlease.car.exception.CarException;
import com.carlease.car.mapper.UserMapper;
import com.carlease.car.model.request.UserRequestModel;
import com.carlease.car.persistence.Authority;
import com.carlease.car.persistence.Customer;
import com.carlease.car.repository.AuthorityRepository;
import com.carlease.car.repository.LoginRepository;
import com.carlease.car.service.UserManagementService;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {
  @Autowired AuthorityRepository authorityRepository;
  @Autowired private LoginRepository loginRepository;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private UserMapper userMapper;

  @Override
  public ResponseEntity<String> registerUser(UserRequestModel userRequestModel) {
    Customer savedCustomer = null;
    ResponseEntity response = null;
    try {
      Customer customer = userMapper.mapUserRequestModelToCustomer(userRequestModel);
      if (!userRequestModel.getRole().equalsIgnoreCase("BROKER")
          && userRequestModel.getRole().equalsIgnoreCase("COMPANY")) {
        throw new CarException("User role needs to be either Broker or Company");
      }
      String hashPwd = passwordEncoder.encode(customer.getPwd());
      customer.setPwd(hashPwd);
      customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
      savedCustomer = loginRepository.save(customer);
      String authorityName = "ROLE_" + customer.getRole().toUpperCase();

      Authority authority = new Authority();
      authority.setName(authorityName);
      authority.setCustomer(savedCustomer);
      authorityRepository.save(authority);
      if (savedCustomer.getId() > 0) {
        response =
            ResponseEntity.status(HttpStatus.CREATED)
                .body("Given user details are successfully registered");
      }
    } catch (Exception ex) {
      response =
          ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred due to " + ex.getMessage());
    }
    return response;
  }

  @Override
  public Customer getUserDetailsAfterLogin(Authentication authentication) {
    List<Customer> customers = loginRepository.findByEmail(authentication.getName());
    if (customers.size() > 0) {
      return customers.get(0);
    } else {
      return null;
    }
  }
}
