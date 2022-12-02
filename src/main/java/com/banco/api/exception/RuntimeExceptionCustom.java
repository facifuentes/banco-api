package com.banco.api.exception;


import com.banco.api.dto.ExceptionDtoOutput;

public class RuntimeExceptionCustom extends RuntimeException {

    private static final long serialVersionUID = 8696638981285538482L;

    private ExceptionDtoOutput value;

    public RuntimeExceptionCustom() {
    }

    public RuntimeExceptionCustom(String message) {
        super(message);
    }

    public RuntimeExceptionCustom(String message, ExceptionDtoOutput value) {
        super(message);
        this.value = value;
    }

    public RuntimeExceptionCustom(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeExceptionCustom(Throwable cause) {
        super(cause);
    }

    public RuntimeExceptionCustom(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ExceptionDtoOutput getValue() {
        return this.value;
    }

}
