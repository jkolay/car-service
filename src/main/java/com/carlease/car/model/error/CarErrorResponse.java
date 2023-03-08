package com.carlease.car.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * car Error response model class
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarErrorResponse {
    private List<CarErrorModel> errors;
}
