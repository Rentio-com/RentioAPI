package com.rentio.common.exception;

public record FieldValidationError(
    String field,
    String code
) {}