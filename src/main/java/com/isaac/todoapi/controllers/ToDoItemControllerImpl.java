package com.isaac.todoapi.controllers;

import com.isaac.todoapi.dtos.ToDoItemRequestDTO;
import com.isaac.todoapi.services.ToDoItemServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/todo-items")
public class ToDoItemControllerImpl implements ToDoItemController {
    private ToDoItemServiceImpl toDoItemService;

    public ToDoItemControllerImpl(ToDoItemServiceImpl toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getAll() {
        var todoItems = toDoItemService.getAll();
        return ResponseEntity.ok(todoItems);
    }
    @Override
    @GetMapping("/{id}")
    public  ResponseEntity<?> getById(@PathVariable Integer id) {
        var todoItem = toDoItemService.getById(id);
        return ResponseEntity.ok((todoItem));
    }
    @Override
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ToDoItemRequestDTO toDoItem) {
        var todoItemId = toDoItemService.add(toDoItem);
        var todoItemUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoItemId).toUri();
        return ResponseEntity.created(todoItemUri).build();
    }
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody ToDoItemRequestDTO toDoItem) {
        toDoItemService.update(id, toDoItem);
        return ResponseEntity.noContent().build();
    }
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        toDoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}