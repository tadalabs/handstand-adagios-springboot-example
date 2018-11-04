package org.tadalabs.sample.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tadalabs.sample.core.boundary.enter.SessionService;
import org.tadalabs.sample.core.domain.Todo;
import org.tadalabs.sample.adapter.web.api.TodoList;
import org.tadalabs.sample.core.boundary.enter.TodoService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/rest/todos")
public class TodoController {

    private final TodoService todoService;

    private final SessionService sessionService;

    @Autowired
    public TodoController(TodoService todoService, SessionService sessionService) {
        this.todoService = todoService;
        this.sessionService = sessionService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo request) {

        Optional<Todo> optionalTodo = this.todoService.createNewTodo(request);
        if(!optionalTodo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(optionalTodo.get());
    }

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<TodoList> getAllTodos(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,
            HttpServletRequest httpServletRequest) {

        if(!this.sessionService.isSessionValid(authorizationToken, httpServletRequest.getRemoteAddr())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Optional<TodoList> optional = this.todoService.findAllWithSessionId(authorizationToken);

        return optional.map(todoList -> new ResponseEntity<>(todoList, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(value = "/{todoId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Todo> getTodoById(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,
            HttpServletRequest httpServletRequest,
            @Valid @NotBlank @PathVariable("todoId") String todoId) {

        if(!this.sessionService.isSessionValid(authorizationToken, httpServletRequest.getRemoteAddr())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Optional<Todo> optionalTodo = this.todoService.getByTodoIdAndSessionId(todoId, authorizationToken);
        if(!optionalTodo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(optionalTodo.get());
    }

    @PutMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity updateTodo(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody Todo request) {

        if(!this.sessionService.isSessionValid(authorizationToken, httpServletRequest.getRemoteAddr())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Optional<Todo> optionalTodo = this.todoService.updateTodo(request);
        if(!optionalTodo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalTodo.get());
    }

    @DeleteMapping(value = "/{todoId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity deleteTodo(
            @RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,
            HttpServletRequest httpServletRequest,
            @PathVariable String todoId) {

        if(!this.sessionService.isSessionValid(authorizationToken, httpServletRequest.getRemoteAddr())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        this.todoService.removeTodo(todoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
