package com.carlease.car.model.request;

import com.carlease.car.config.CarValidationMessageConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Car status update model class */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateStatusRequestModel {
  @NotBlank(message = CarValidationMessageConfig.STATUS_NOT_NULL)
  @Schema(description = "The status of the Car", example = "Leased")
  private String status;
}
