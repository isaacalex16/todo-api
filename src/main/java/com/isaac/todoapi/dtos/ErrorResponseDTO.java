package com.isaac.todoapi.dtos;

import com.isaac.todoapi.enums.InternalErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ErrorResponseDTO {
    private InternalErrorCode code;
    private String message;
    private List<String> details;
}
