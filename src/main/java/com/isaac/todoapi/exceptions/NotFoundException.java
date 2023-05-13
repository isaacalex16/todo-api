package com.isaac.todoapi.exceptions;

import com.isaac.todoapi.enums.InternalErrorCode;

public class NotFoundException extends RuntimeException {
    private InternalErrorCode code;

    public NotFoundException(InternalErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public InternalErrorCode getCode() {
        return code;
    }
}