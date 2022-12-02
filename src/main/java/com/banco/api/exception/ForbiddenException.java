package com.banco.api.exception;

public class ForbiddenException extends RuntimeException {


    private static final long serialVersionUID = 3930003949055124467L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }
}
