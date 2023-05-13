package com.isaac.todoapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItemResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime plannedDate;
}