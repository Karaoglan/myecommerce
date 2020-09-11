package com.burakkaraoglan.myecommerce.v2.exception;

public class IncorrectQuantityException extends RuntimeException {
    public IncorrectQuantityException() {
    }

    public IncorrectQuantityException(final String message) {
        super(message);
    }
}
