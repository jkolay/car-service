package com.carlease.car.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** car Error Model class for to display error to user */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarErrorModel {
  private String description;
  private String code;
  private ErrorSeverityLevelCodeType severityLevel;
}
