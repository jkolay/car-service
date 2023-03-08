package com.carlease.car.model.request;

import com.carlease.car.config.CarValidationMessageConfig;
import com.carlease.car.config.ValidationConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Car Request model class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    @NotBlank(message = CarValidationMessageConfig.MAKE_NOT_NULL)
    @Pattern(regexp = ValidationConstant.PATTERN_MAKE, message = CarValidationMessageConfig.MAKE_PATTERN_NOT_VALID)
    @Schema(description = "The make of the Car", example = "Audi")
    private String make;

    @NotBlank(message = CarValidationMessageConfig.MODEL_NOT_NULL)
    @Size(max = ValidationConstant.MAX_LENGTH_MODEL, message = CarValidationMessageConfig.MODEL_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.PATTERN_MODEL, message = CarValidationMessageConfig.MODEL_PATTERN_NOT_VALID)
    @Schema(description = "Model of the Car;", example = "X012")
    private String model;

    @NotBlank(message = CarValidationMessageConfig.VERSION_NOT_NULL)
    @Size(max = ValidationConstant.VERSION_LENGTH, message = CarValidationMessageConfig.VERSION__NOT_VALID)
    @Pattern(regexp = ValidationConstant.VERSION_PATTERN, message = CarValidationMessageConfig.VERSION_PATTERN_NOT_VALID)
    @Schema(description = "The version of the car", example = "2012")
    private String version;

  @NotNull(message = CarValidationMessageConfig.NUMBER_OF_DOORS_NOT_NULL)
  @Max(
      value = ValidationConstant.NUMBER_OF_DOORS_LENGTH,
      message = CarValidationMessageConfig.NUMBER_OF_DOORS_NOT_VALID)
  @Schema(description = "number of doors ", example = "5")
  private Integer numberOfDoors;

    @NotNull(message = CarValidationMessageConfig.C02_EMISSION_NOT_NULL)
    @Schema(description = "co2 emission", example = "1.6")
    private Double co2Emission;

    @NotNull(message = CarValidationMessageConfig.GROSS_PRICE_NOT_NULL)
    @Schema(description = "The gross price of the car", example = "123000")
    private Double grossPrice;

    @NotNull(message = CarValidationMessageConfig.NET_PRICE_NOT_NULL)
    @Schema(description = "The net price of the car", example = "135000")
    private Double netPrice;

    @NotNull(message = CarValidationMessageConfig.MILEAGE_NOT_NULL)
    @Schema(description = "The current mileage the car", example = "15000")
    private Long mileage;



}
