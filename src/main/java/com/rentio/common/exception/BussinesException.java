package com.rentio.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BussinesException extends RuntimeException {
    private final String code;
    private final String field;
}
