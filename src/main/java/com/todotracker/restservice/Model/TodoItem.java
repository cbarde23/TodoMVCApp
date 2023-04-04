package com.todotracker.restservice.Model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof TodoItem))
            return false;
        TodoItem item = (TodoItem) obj;

        if (item.id == this.id && item.status == this.status && this.title == item.title) {
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (title != null) {
            result = 31 * result + title.hashCode();
        }
        if (status != null) {
            result = 31 * result + status.hashCode();
        }
        return result;
    }

}

