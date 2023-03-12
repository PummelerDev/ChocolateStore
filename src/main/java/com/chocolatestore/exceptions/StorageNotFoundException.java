package com.chocolatestore.exceptions;

public class StorageNotFoundException extends RuntimeException {
    public StorageNotFoundException() {
    }

    public StorageNotFoundException(String message) {
        super(message);
    }

    public StorageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageNotFoundException(Throwable cause) {
        super(cause);
    }

    public StorageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
