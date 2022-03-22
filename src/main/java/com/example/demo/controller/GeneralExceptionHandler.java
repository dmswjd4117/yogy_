package com.example.demo.controller;


import com.example.demo.excpetion.UnauthenticatedException;
import com.example.demo.excpetion.DuplicatedException;
import com.example.demo.excpetion.NotFoundException;
import com.example.demo.excpetion.ServiceRuntimeException;
import com.example.demo.utils.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.demo.utils.ApiUtils.error;

@ControllerAdvice
public class GeneralExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(error(throwable, status), headers, status);
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public ResponseEntity<?> handleServiceRuntimeException(ServiceRuntimeException e) {
        if (e instanceof DuplicatedException)
            return newResponse(e, HttpStatus.BAD_REQUEST);
        if (e instanceof NotFoundException)
            return newResponse(e, HttpStatus.NOT_FOUND);
        if (e instanceof UnauthenticatedException)
            return newResponse(e, HttpStatus.UNAUTHORIZED);

        log.warn("Unexpected serviceRuntimeException: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected Exception: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
