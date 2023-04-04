package com.todotracker.restservice;

import com.todotracker.restservice.Model.TodoItem;
import com.todotracker.restservice.controller.TodoController;
import com.todotracker.restservice.repo.TodoRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TodoTrackerAppTests {

    @MockBean
    private TodoController todoController;

    @MockBean
    private TodoItem todoItem;

    @MockBean
    private TodoRepo todoRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(todoController).isNotNull();
        Assertions.assertThat(todoItem).isNotNull();
        Assertions.assertThat(todoRepository).isNotNull();
    }


}
