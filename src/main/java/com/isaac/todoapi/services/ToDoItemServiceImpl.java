package com.isaac.todoapi.services;

import com.isaac.todoapi.dtos.ToDoItemRequestDTO;
import com.isaac.todoapi.dtos.ToDoItemResponseDTO;
import com.isaac.todoapi.entities.ToDoItemEntity;
import com.isaac.todoapi.enums.InternalErrorCode;
import com.isaac.todoapi.exceptions.NotFoundException;
import com.isaac.todoapi.mappers.ToDoItemMapper;
import com.isaac.todoapi.repositories.ToDoItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoItemServiceImpl /*implements ToDoItemService*/ {

    private ToDoItemRepository toDoItemRepository;

    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepository){
        this.toDoItemRepository = toDoItemRepository;
    }


    public ToDoItemResponseDTO getById(Integer id) {
        var result = toDoItemRepository.findById(id);
        if (result.isEmpty()) throw new NotFoundException(InternalErrorCode.TODO_NOT_FOUND, "The ToDo with ID %s not found".formatted(id.toString()));

        return ToDoItemMapper.toDto(result.get());
    }


    public List<ToDoItemResponseDTO> getAll() {
        return this.toDoItemRepository.getAll()
                .stream()
                .map(ToDoItemMapper::toDto)
                .toList();
    }

    public Integer add(ToDoItemRequestDTO toDoItem) {
        ToDoItemEntity entity = ToDoItemMapper.toEntity(toDoItem);
        var savedEntity = toDoItemRepository.save(entity);
        return savedEntity.getId();
    }

    public void update(Integer id, ToDoItemRequestDTO toDoItem) {
        var result = toDoItemRepository.findById(id);
        if (result.isEmpty()) throw new NotFoundException(InternalErrorCode.TODO_NOT_FOUND, "The ToDo with ID %s not found".formatted(id.toString()));

        ToDoItemEntity entity = result.get();
        entity.setTitle(toDoItem.getTitle());
        entity.setDescription(toDoItem.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());
        toDoItemRepository.save(entity);
    }


    public void delete(Integer id) {
        var result = toDoItemRepository.findById(id);
        if (result.isEmpty()) throw new NotFoundException(InternalErrorCode.TODO_NOT_FOUND, "The ToDo with ID %s not found".formatted(id.toString()));

        ToDoItemEntity entity = result.get();
        entity.setDeletedAt(LocalDateTime.now());

        toDoItemRepository.save(entity);
    }
}