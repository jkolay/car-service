package com.carlease.car.model.request;

import com.carlease.car.config.CarValidationMessageConfig;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {
  @NotBlank(message = CarValidationMessageConfig.USER_NAME_NOT_NULL)
  private String name;

  @NotBlank(message = CarValidationMessageConfig.EMAIL_NOT_NULL)
  private String email;

  @NotBlank(message = CarValidationMessageConfig.MOBILE_NOT_NULL)
  private String mobileNumber;

  @NotBlank(message = CarValidationMessageConfig.PASSWORD_NOT_NULL)
  private String pwd;

  @NotBlank(message = CarValidationMessageConfig.ROLE_NOT_NULL)
  private String role;
}
