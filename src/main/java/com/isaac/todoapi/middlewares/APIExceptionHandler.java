package com.isaac.todoapi.middlewares;

import com.isaac.todoapi.dtos.ErrorResponseDTO;
import com.isaac.todoapi.enums.InternalErrorCode;
import com.isaac.todoapi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        var response = new ErrorResponseDTO(ex.getCode(), ex.getMessage(), List.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream().map(error -> "%s - %s".formatted(error.getField(), error.getDefaultMessage())).toList();
        var response = new ErrorResponseDTO(InternalErrorCode.INVALID_REQUEST_DATA, "Some fields in the request are invalid", details);
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        var response = new ErrorResponseDTO(InternalErrorCode.UNKNOWN_ERROR, "An unknown error occurred", List.of());
        return ResponseEntity.internalServerError().body(response);
    }
}