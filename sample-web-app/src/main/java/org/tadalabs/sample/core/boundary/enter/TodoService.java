package org.tadalabs.sample.core.boundary.enter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tadalabs.sample.core.domain.Todo;
import org.tadalabs.sample.data.dynamo.entity.TodoEntity;
import org.tadalabs.sample.web.TodoListMapper;
import org.tadalabs.sample.web.TodoMapper;
import org.tadalabs.sample.adapter.web.api.TodoList;
import org.tadalabs.sample.data.dynamo.repository.TodoDynamoRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoDynamoRepository todoDynamoRepository;

    private final TodoMapper todoMapper;

    private final TodoListMapper todoListMapper;

    /**
     * Default Constructor
     * @param todoDynamoRepository Todo Repository
     */
    @Autowired
    public TodoService(TodoDynamoRepository todoDynamoRepository,
                       TodoMapper todoMapper, TodoListMapper todoListMapper) {

        this.todoDynamoRepository = todoDynamoRepository;
        this.todoMapper = todoMapper;
        this.todoListMapper = todoListMapper;
    }

    /**
     * Fetches all Todos in the Repository
     * @return {TodoList} Encapsulated Collection of {@code TodoListItem} objects
     */
    public TodoList findAll() {
        List<TodoEntity> entities =
                (List<TodoEntity>) todoDynamoRepository.findAll();

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
            return Optional.empty();
        }

        TodoEntity entity = optional.get();

        entity = todoDynamoRepository.save(entity);

        return todoMapper.toDomain(entity);
    }

    /**
     * Retrieve the Todo record corresponding to the param Id
     *
     * @param todoId the Id of the Todo object to fetch
     * @return {Optional} the persisted Todo object
     */
    public Optional<Todo> getTodoById(@Valid @NotBlank String todoId) {

        Optional<TodoEntity> optional = this.todoDynamoRepository.findByTodoId(todoId);

        if (!optional.isPresent()) {
            return Optional.empty();
        }

        return todoMapper.toDomain(optional.get());
    }

}

