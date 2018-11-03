package org.tadalabs.sample.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tadalabs.sample.core.domain.Todo;
import org.tadalabs.sample.web.api.TodoList;
import org.tadalabs.sample.core.boundary.enter.TodoService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/rest/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo request) {

        Optional<Todo> optionalTodo = this.todoService.addTodo(request);

        if(!optionalTodo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(optionalTodo.get());
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<TodoList> getAllTodos() {

        TodoList todoList = this.todoService.findAll();

        if(todoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{todoId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Todo> getTodoById(@Valid @NotBlank @PathVariable("todoId") String todoId) {

        Optional<Todo> optionalTodo = this.todoService.getTodoById(todoId);

        if(!optionalTodo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(optionalTodo.get());
    }

}
