package com.todotracker.restservice.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;

@Entity
public class TodoItem {

    private long id;
    @NotBlank
    private String title;
    private String status;

    public TodoItem() {
    }

    public TodoItem(long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    // this is the id primary key auto increament
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

