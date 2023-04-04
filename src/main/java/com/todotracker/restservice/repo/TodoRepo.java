package com.todotracker.restservice.repo;

import com.todotracker.restservice.Model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoItem, Long> {

}
