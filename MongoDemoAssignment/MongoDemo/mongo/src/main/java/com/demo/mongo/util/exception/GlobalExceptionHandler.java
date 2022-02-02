package com.demo.mongo.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Custom message For @Valid DTO
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", new Date());

        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        response.put("errors", errors);

        return new ResponseEntity<>(response, status);

    }

    // Global Handler for All Application Logic Error
    @ExceptionHandler(value = ApplicationLogicError.class)
    public ResponseEntity<Object> ApplicationLogicErrorHandler(ApplicationLogicError exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Global Handler for All Bad Request Exception
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> BadRequestExceptionHandler(BadRequestException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

}