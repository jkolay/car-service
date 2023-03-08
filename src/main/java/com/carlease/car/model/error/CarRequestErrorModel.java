package com.carlease.car.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
/**
 * Car Error Model class for to display error to user
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestErrorModel {

    private Map<String,String> errorDescription;
    private String code;
    private ErrorSeverityLevelCodeType severityLevel;

}
