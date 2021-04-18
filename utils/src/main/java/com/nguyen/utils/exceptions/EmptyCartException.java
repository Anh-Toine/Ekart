package com.nguyen.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Supplier not found")
public class EmptyCartException extends RuntimeException{
    public EmptyCartException() {
    }

    public EmptyCartException(String message) {
        super(message);
    }

    public EmptyCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyCartException(Throwable cause) {
        super(cause);
    }
}
