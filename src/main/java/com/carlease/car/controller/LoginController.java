package com.carlease.car.controller;

import com.carlease.car.model.request.UserRequestModel;import com.carlease.car.persistence.Customer;
import com.carlease.car.repository.LoginRepository;
import java.sql.Date;
import java.util.List;
import com.carlease.car.service.CarService;
import com.carlease.car.service.UserManagementService;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    private final UserManagementService userManagementService;

    @Autowired
    public LoginController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestModel userRequestModel) {
        return userManagementService.registerUser(userRequestModel);

    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return userManagementService.getUserDetailsAfterLogin(authentication);

    }
}
