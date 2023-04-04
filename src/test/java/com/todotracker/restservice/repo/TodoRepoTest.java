package com.todotracker.restservice.repo;

import com.todotracker.restservice.Model.TodoItem;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
class TodoRepoTest {

    @Autowired
    TodoRepo repo;

    @Test
    @Disabled
    public void testAnyTodoItemPresent(){

    }

}