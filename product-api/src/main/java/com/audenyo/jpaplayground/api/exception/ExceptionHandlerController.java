package com.audenyo.jpaplayground.api.exception;

import com.audenyo.jpaplayground.product.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(ObjectNotFoundException e){
        return ResponseEntity.badRequest().body(ErrorResponse.create(e, HttpStatusCode.valueOf(400), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception e){
        return ResponseEntity.badRequest().body(ErrorResponse.create(e, HttpStatusCode.valueOf(500), e.getMessage()));
    }
}
