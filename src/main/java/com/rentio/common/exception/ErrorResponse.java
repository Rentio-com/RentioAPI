package com.rentio.common.exception;

public record ErrorResponse(
    int status,
    String code,
    String message
) {}