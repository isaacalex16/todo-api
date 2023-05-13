package com.isaac.todoapi.repositories;

import com.isaac.todoapi.entities.ToDoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItemEntity, Integer> {
    @Query(value = "SELECT todo FROM ToDoItemEntity todo WHERE todo.deletedAt IS NULL order by todo.plannedAt desc")
    List<ToDoItemEntity> getAll();

    @Query(value = "SELECT todo FROM ToDoItemEntity todo WHERE todo.deletedAt IS NULL AND todo.id = :id")
    Optional<ToDoItemEntity> findById(@Param("id") Integer id);
}