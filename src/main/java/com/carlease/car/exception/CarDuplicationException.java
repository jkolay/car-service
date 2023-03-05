package com.carlease.car.exception;

public class CarDuplicationException extends Exception{
    public CarDuplicationException() {
        super();
    }

    public CarDuplicationException(String message) {
        super(message);
    }

    public CarDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarDuplicationException(Throwable cause) {
        super(cause);
    }

    protected CarDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

