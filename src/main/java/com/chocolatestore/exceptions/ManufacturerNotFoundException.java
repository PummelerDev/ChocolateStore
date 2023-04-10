package com.chocolatestore.exceptions;

public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException() {
    }

    public ManufacturerNotFoundException(String message) {
        super(message);
    }

    public ManufacturerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManufacturerNotFoundException(Throwable cause) {
        super(cause);
    }

    public ManufacturerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
