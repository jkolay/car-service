package com.carlease.car.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private Integer carId;

    private String make;

    private String model;

    private String version;

    private Integer numberOfDoors;

    private Double co2Emission;

    private Double netPrice;

    private Double grossPrice;

    private String status;

    private Long mileage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
