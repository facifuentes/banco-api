package com.banco.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CREATED)
public class ObjectRequestException extends RuntimeException {

    private static final long serialVersionUID = 1984472553314381694L;

    public ObjectRequestException() {
        super();
    }

    public ObjectRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectRequestException(String message) {
        super(message);
    }

    public ObjectRequestException(Throwable cause) {
        super(cause);
    }

}
