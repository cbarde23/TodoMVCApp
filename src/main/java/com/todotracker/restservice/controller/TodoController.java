package com.todotracker.restservice.controller;

import com.todotracker.restservice.Model.TodoItem;
import com.todotracker.restservice.exception.TaskNotFound;
import com.todotracker.restservice.repo.TodoRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * This is contoller class to update todo task in memory DB.
 * it allows Insert/Update/Delete operation with different input criteria
 * dev: Chetan Barde
 * date:2nd April 2023
 * Request:
 *  {
 *   "id": 1,
 *   "title" : "MyTask",
 *   "status": "open"
 *  }
 */

@RestController
@RequestMapping(value = "/todoTask")
public class TodoController {

    @Autowired
    private TodoRepo repository;

    /**
     * This function will return all too items without any filter.
     * if no data stored in the DB then no result will be returned.
     *
     * @return list of todo items (TodoItem)
     * sample url http://localhost:8080/todo
     */
    @GetMapping
    public List<TodoItem> findAll() {
        return repository.findAll();
    }

    /**
     * This function will be called to get any specific taks based on ID.
     *
     * @param id of task
     * @return return the sprcific task if found other wise notfound exception will be returned.
     * sample url http://localhost:8080/todo/1
     */
    @GetMapping(value = "/{id}")
    public TodoItem findbyId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFound(id));
    }

    /**
     * it will search the task based on status (inprogress/done/open)
     *
     * @param status string
     * @return it will return list of tasks based on search filter
     * sample url http://localhost:8080/todo/search/inprogress
     */
    @GetMapping(value = "/search/{status}")
    public List<TodoItem> findbyStatus(@PathVariable String status) {
        return repository.findAll().stream().filter(i -> i.getStatus().equals(status)).
                collect(Collectors.toList());
    }

    /**
     * Insertion of data
     *
     * @param TodoItem object
     * @return TodoItem Object will be returned
     * sample url http://localhost:8080/todo
     * request:
     * {
     * "id": 1,
     * "title" : "MyTask",
     * "status": "open"
     * }
     */
    @PostMapping
    public TodoItem addITem(@Valid @NotNull @RequestBody TodoItem item) {
        return repository.save(item);
    }


    /**
     * It will delete the task object
     *
     * @param TodoItem sample url http://localhost:8080/todo with request
     *                 request:
     *                 {
     *                 "id": 1,
     *                 "title" : "MyTask",
     *                 "status": "open"
     *                 }
     */
    @DeleteMapping
    public void removeItem(@RequestBody TodoItem item) {
        repository.delete(item);
    }

    /**
     * this will remove data for respected Id
     *
     * @param id sample url http://localhost:8080/todo/1
     */
    @DeleteMapping(value = "/{id}")
    public void removeItem(@PathVariable long id) {
        repository.deleteById(id);
    }

    /**
     * This will remove all data stored in memory
     * sample url http://localhost:8080/todo/All
     */
    @DeleteMapping("/All")
    public void removeAllItem() {
        repository.deleteAll();
    }

    /**
     * this will update not null fields as per request body based on ID.
     *
     * @param TodoItem
     * @return sample url http://localhost:8080/todo
     *
     */
    @PatchMapping
    public TodoItem updateTodoField(@RequestBody TodoItem item) {
        TodoItem todoitem = repository.findById(item.getId()).orElseThrow(() -> new TaskNotFound(item.getId()));
        if (item.getStatus() != null)
            todoitem.setStatus(item.getStatus());
        if (item.getTitle() != null)
            todoitem.setTitle(item.getTitle());
        repository.save(todoitem);
        return todoitem;
    }


}
