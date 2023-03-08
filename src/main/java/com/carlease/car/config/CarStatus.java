package com.carlease.car.config;

/**
 * enum which maintains Car status
 */
public enum CarStatus {
    ALL("All"),
    NEW("Not-Leased"),
    LEASED("leased");

    private String value;

    CarStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static CarStatus fromString(String value) {
        for (CarStatus customerStatus : CarStatus.values()) {
            if (customerStatus.value.equalsIgnoreCase(value)) {
                return customerStatus;
            }
        }
        return null;
    }
}
