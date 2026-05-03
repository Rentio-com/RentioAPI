package com.rentio.common.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.rentio.common.enums.ExceptionResponseCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldValidationErrorResponse> handleBodyValidation(MethodArgumentNotValidException ex) {
        List<FieldValidationError> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> new FieldValidationError(err.getField(), err.getDefaultMessage()))
            .toList();

        return ResponseEntity.status(400).body(new FieldValidationErrorResponse(400, ExceptionResponseCode.VALIDATION_FAILED, errors));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundEntity(NoSuchElementException ex){
        return ResponseEntity.status(404).body(new ErrorResponse(404, ExceptionResponseCode.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumError(HttpMessageNotReadableException ex) {
        
        String message = (ex.getMessage().split(":")[2] + ex.getMessage().split(":")[3]).strip();

        return ResponseEntity.status(400).body(new ErrorResponse(400, ExceptionResponseCode.VALIDATION_FAILED, message));
    }

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<FieldValidationError> handleBussinesError(BussinesException ex) {
        return ResponseEntity.badRequest().body(new FieldValidationError(ex.getField(), ex.getCode()));
    }
}