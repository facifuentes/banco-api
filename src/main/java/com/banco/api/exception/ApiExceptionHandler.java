package com.banco.api.exception;


import com.banco.api.dto.ObjectResponse;
import com.google.common.base.Strings;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, FileNotFoundException.class})
    public ObjectResponse notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ObjectResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
    @ExceptionHandler({FileStorageException.class, FileUploadException.class})
    public ObjectResponse fileStorageRequest(HttpServletRequest request, Exception exception) {
        return new ObjectResponse(HttpStatus.INSUFFICIENT_STORAGE.value(), exception.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({org.springframework.dao.DuplicateKeyException.class,
            org.springframework.dao.DataAccessException.class, SQLException.class,
            org.springframework.dao.EmptyResultDataAccessException.class,
            MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
            javax.validation.ConstraintViolationException.class,  InvalidDataAccessApiUsageException.class,
            NullPointerException.class
    })
    public ObjectResponse badRequest(HttpServletRequest request, Exception exception) {
        String message = getMessage(exception);
        return new ObjectResponse(HttpStatus.BAD_REQUEST.value(), message, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public ObjectResponse badRequestCustom(HttpServletRequest request, RuntimeExceptionCustom exception) {
        String message = getMessage(exception);
        return new ObjectResponse(HttpStatus.BAD_REQUEST.value(), message, exception.getValue());
    }

    private String getMessage(Exception exception) {

        String message = exception.getMessage();
        StringBuilder errors = new StringBuilder();

        //Controla excepciones de un objeto desde el controller
        if (exception instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors().forEach(error -> {
                String errorMessage = error.getDefaultMessage();
                if (errorMessage != null) {
                    errorMessage = errorMessage.trim();
                    char lastLetterErrorMessage = errorMessage.charAt(errorMessage.length() - 1);
                    if (lastLetterErrorMessage != '.') errorMessage = String.format("%s. ", errorMessage);
                }
                errors.append(errorMessage);
            });
            message = errors.toString();
        }

        //Controla excepciones de un objeto desde el service
        if (exception instanceof javax.validation.ConstraintViolationException) {
            for (ConstraintViolation<?> violation : ((javax.validation.ConstraintViolationException) exception).getConstraintViolations()) {
                String queryParamPath = violation.getPropertyPath().toString();
                String field = splitStringAndGetLastElement(queryParamPath);
                errors.append(String.format("%s : %s. ", field, violation.getMessage()));
            }
            message = errors.toString();
        }

        return message;

    }

    private String splitStringAndGetLastElement(String value) {
        if (!Strings.isNullOrEmpty(value) && Boolean.TRUE.equals(value.contains("."))) {
            String[] parts = value.split(Pattern.quote("."));
            value = parts[parts.length - 1];
        }
        return value;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class,  DataIntegrityViolationException.class})
    public ObjectResponse conflictRequest(HttpServletRequest request, Exception exception) {
        String message = exception.getMessage();



        if (exception instanceof DataIntegrityViolationException) {
            if (request.getMethod().equals("DELETE")) {
                message = "No es posible realizar la acción, otros registros dependen de este";
            } else {
                SQLException sqlException = (SQLException) exception.getCause().getCause();
                if (sqlException.getSQLState().contains("23505")) {
                    message = "El registro ya existe";
                } else {
                    message = "No es posible realizar el registro";
                }

            }

        }
        return new ObjectResponse(HttpStatus.CONFLICT.value(), message, request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, ServletException.class})
    public ObjectResponse fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        return new ObjectResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler({ObjectRequestException.class})
    public ObjectResponse objectRequest(HttpServletRequest request, Exception exception) {
        return new ObjectResponse(HttpStatus.CREATED.value(), exception.getMessage(), request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({org.springframework.web.HttpRequestMethodNotSupportedException.class})
    public ObjectResponse methodNotAllowed(HttpServletRequest request, Exception exception) {
        return new ObjectResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage(), request.getRequestURI());
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    public ObjectResponse forbiddenRequest(HttpServletRequest request, Exception exception) {
        return new ObjectResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage(), request.getRequestURI());
    }


}
