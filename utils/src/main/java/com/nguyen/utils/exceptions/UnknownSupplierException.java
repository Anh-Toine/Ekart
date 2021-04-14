package com.nguyen.utils.exceptions;

public class UnknownSupplierException extends RuntimeException{
    public UnknownSupplierException() {
    }

    public UnknownSupplierException(String message) {
        super(message);
    }

    public UnknownSupplierException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownSupplierException(Throwable cause) {
        super(cause);
    }
}
