package com.carlease.car.exception;

/**
 * the custom car exception class
 */
public class CarException extends Exception {
    public CarException() {
        super();
    }

    public CarException(String message) {
        super(message);
    }

    public CarException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarException(Throwable cause) {
        super(cause);
    }

    protected CarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
