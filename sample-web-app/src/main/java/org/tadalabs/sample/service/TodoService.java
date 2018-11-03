package org.tadalabs.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tadalabs.sample.domain.Todo;
import org.tadalabs.sample.entity.TodoEntity;
import org.tadalabs.sample.mapper.TodoListMapper;
import org.tadalabs.sample.mapper.TodoMapper;
import org.tadalabs.sample.model.TodoList;
import org.tadalabs.sample.repository.TodoRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private TodoListMapper todoListMapper;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoList findAll() {
        List<TodoEntity> entities =
                (List<TodoEntity>) todoRepository.findAll();

        return todoListMapper.toTodoList(entities);
    }

    /**
     * Inserts a new `Todo`
     *
     * @param todo model to be persisted in the database
     * @return {Optional} the newly instantiated `Todo` object
     */
    public Optional<Todo> addTodo(Todo todo) {

        Optional<TodoEntity> optional = todoMapper.fromDomain(todo);

        if(!optional.isPresent()) {
            throw new InvalidParameterException("External todo model failed to be mapped to an internal entity.");
        }

        TodoEntity entity = optional.get();

        entity = todoRepository.save(entity);

        return todoMapper.toDomain(entity);
    }

    /**
     * Retrieve the Todo record corresponding to the param Id
     *
     * @param todoId the Id of the Todo object to fetch
     * @return {Optional} the persisted Todo object
     */
    public Optional<Todo> getTodoById(@Valid @NotBlank String todoId) {

        Optional<TodoEntity> optional = this.todoRepository.findByTodoId(todoId);

        if (!optional.isPresent()) {
            throw new InvalidParameterException("Internal todo entity failed to be mapped to an external domain model.");
        }

        return todoMapper.toDomain(optional.get());
    }

}

