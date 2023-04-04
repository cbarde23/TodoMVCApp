package com.todotracker.restservice.controller;

import com.todotracker.restservice.Model.TodoItem;
import com.todotracker.restservice.exception.TaskNotFound;
import com.todotracker.restservice.repo.TodoRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mockClass;

    @MockBean
    TodoRepo repository;

    private TodoItem taskCreation(long id, String title, String status) {
        return new TodoItem(id, title, status);
    }

    @Test
    void testGetAllTodoItems() throws Exception {
        List<TodoItem> lstItem = new ArrayList<>();
        lstItem.add(taskCreation(1, "MyTask1", "OPEN"));
        lstItem.add(taskCreation(1, "MyTask2", "INPROGRESS"));
        lstItem.add(taskCreation(1, "MyTask3", "DONE"));
        when(repository.findAll()).thenReturn(lstItem);
        this.mockClass.perform(get("/todoTask")).andExpect(status().isOk());
    }

    @Test
    void testTaskFindbyId() throws Exception {
        TodoItem task = taskCreation(1, "MyTask1", "OPEN");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(task));
        this.mockClass.perform(get("/todoTask/{id}", "1")).andExpect(status().isOk());
    }

    @Test
    void testTaskFindbyIdException() throws Exception {
        this.mockClass.perform(get("/todoTask/{id}", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof TaskNotFound))
                .andExpect(result -> assertEquals("Could not find Task 1", result.getResolvedException().getMessage()));
    }

    @Test
    void testTaskFindbyStatus() throws Exception {
        TodoItem task = taskCreation(1, "MyTask1", "OPEN");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(task));
        this.mockClass.perform(get("/todoTask/search/{status}", "OPEN")).andExpect(status().isOk());
    }

    @Test
    void testPostTodoTask() throws Exception {
        this.mockClass.perform(post("/todoTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "        \"id\": 1,        \n" +
                        "        \"title\" : \"MyTask\",\n" +
                        "        \"status\": \"open\"\n" +
                        "    }"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateItem() throws Exception {
        TodoItem task = taskCreation(1, "MyTask1", "OPEN");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(task));
        this.mockClass.perform(patch("/todoTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "        \"id\": 1,        \n" +
                        "        \"title\" : \"MyTask\",\n" +
                        "        \"status\": \"open\"\n" +
                        "    }"))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void testRemoveItembyId() throws Exception {
        this.mockClass.perform(delete("/todoTask/{id}", "11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testRemoveItem() throws Exception {
        TodoItem task = taskCreation(1, "MyTask1", "OPEN");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(task));
        this.mockClass.perform(delete("/todoTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "        \"id\": 1,        \n" +
                        "        \"title\" : \"MyTask\",\n" +
                        "        \"status\": \"open\"\n" +
                        "    }"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testremoveAllItem() throws Exception {
        this.mockClass.perform(delete("/todoTask/All")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testExceptionIfNoDataFoundForUpdate() throws Exception {
        this.mockClass.perform(patch("/todoTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "        \"id\": 1,        \n" +
                        "        \"title\" : \"MyTask\",\n" +
                        "        \"status\": \"open\"\n" +
                        "    }"))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof TaskNotFound))
                .andExpect(result -> assertEquals("Could not find Task 1", result.getResolvedException().getMessage()));
    }
}