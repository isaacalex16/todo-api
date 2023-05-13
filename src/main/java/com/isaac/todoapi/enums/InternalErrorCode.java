package com.isaac.todoapi.enums;

public enum InternalErrorCode {
    TODO_NOT_FOUND("todo_not_found"),
    INVALID_REQUEST_DATA("invalid_request_data"),
    UNKNOWN_ERROR("unknown_error");

    private String code;
    private InternalErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}