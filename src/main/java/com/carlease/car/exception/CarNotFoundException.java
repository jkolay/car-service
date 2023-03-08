package com.carlease.car.exception;

/**
 * Custom Exception class when the car is not found
 */
public class CarNotFoundException extends Exception{
    public CarNotFoundException() {
        super();
    }

    public CarNotFoundException(String message) {
        super(message);
    }

    public CarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CarNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
