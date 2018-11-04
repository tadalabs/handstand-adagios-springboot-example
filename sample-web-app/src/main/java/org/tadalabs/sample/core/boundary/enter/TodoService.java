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
     * @param todoDynamoRepository
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
     * Removes the {@code TodoEntity}
     * @param todoId the unique Identifier corresponding to the {@code TodoEntity} to delete
     */
    public void removeTodo(@Valid @NotBlank String todoId) {
        todoDynamoRepository.deleteById(todoId);
    }

    /**
     * Updates a {@code TodoEntity}
     *
     * @param todoDomainModel the domain model to be persisted with
     * @return {Optional} the newly updated or persisted {@code TodoEntity}
     */
    public Optional<Todo> saveTodoDomainModel(Todo todoDomainModel) {

        Optional<TodoEntity> optional = todoMapper.fromDomain(todoDomainModel);

        if(!optional.isPresent()) {
            return Optional.empty();
        }

        TodoEntity entity = optional.get();

        entity = todoDynamoRepository.save(entity);

        return todoMapper.toDomain(entity);
    }

    /**
     * Retrieve the To-Do record corresponding to the param Id
     *
     * @param todoId the Id of the {@code TodoEntity} to fetch
     * @return {Optional} wrapped {@code TodoEntity}
     */
    public Optional<Todo> getTodoById(@Valid @NotBlank String todoId) {

        Optional<TodoEntity> optional = this.todoDynamoRepository.findByTodoId(todoId);

        if (!optional.isPresent()) {
            return Optional.empty();
        }

        return todoMapper.toDomain(optional.get());
    }

}

