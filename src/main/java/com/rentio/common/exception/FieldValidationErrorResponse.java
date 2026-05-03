package com.rentio.common.exception;

import java.util.List;

public record FieldValidationErrorResponse( 
    int status,
    String code,
    List<FieldValidationError> errors) {}
