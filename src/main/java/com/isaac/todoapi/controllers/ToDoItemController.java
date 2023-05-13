package com.isaac.todoapi.controllers;

import com.isaac.todoapi.dtos.ErrorResponseDTO;
import com.isaac.todoapi.dtos.ToDoItemRequestDTO;
import com.isaac.todoapi.dtos.ToDoItemResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ToDoItemController {

    @Operation(summary = "Get all the ToDos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ToDoItemResponseDTO.class)))}),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<?> getAll();
    @Operation(summary = "Get a specific ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ToDoItemResponseDTO.class))}),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public  ResponseEntity<?> getById(@PathVariable Integer id);
    @Operation(summary = "Add a ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<?> add(ToDoItemRequestDTO toDoItem);
    @Operation(summary = "Update a ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ToDoItemRequestDTO toDoItem);
    @Operation(summary = "Delete a ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<?> delete(@PathVariable Integer id);

}