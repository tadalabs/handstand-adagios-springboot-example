package org.tadalabs.sample.core.boundary.enter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tadalabs.sample.core.boundary.exit.ITodoRepository;
import org.tadalabs.sample.core.domain.Todo;
import org.tadalabs.sample.adapter.web.api.TodoList;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
public class TodoService {

    private final ITodoRepository todoRepository;

    /**
     * Default Constructor
     * @param todoRepository
     */
    @Autowired
    public TodoService(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Fetches all Todos in the Repository
     * @return {TodoList} Encapsulated Collection of {@code TodoListItem} objects
     */
    public Optional<TodoList> findAll() {
        return todoRepository.todos();
    }

    /**
     * Removes the {@code TodoEntity}
     * @param todoId the unique Identifier corresponding to the {@code TodoEntity} to delete
     */
    public void removeTodo(@Valid @NotBlank String todoId) {
        this.todoRepository.delete(todoId);
    }

    /**
     * Updates a {@code TodoEntity}
     * @param todoDomainModel the domain model to be persisted with
     */
    public Optional<Todo> updateTodo(Todo todoDomainModel) {
        return this.todoRepository.update(todoDomainModel);
    }

    /**
     * Updates a {@code TodoEntity}
     * @param todoDomainModel the domain model to be persisted with
     */
    public Optional<Todo> createNewTodo(Todo todoDomainModel) {
        return this.todoRepository.create(todoDomainModel);
    }

    /**
     * Retrieve the To-Do record corresponding to the param Id
     *
     * @param todoId the Id of the {@code TodoEntity} to fetch
     * @return {Optional} wrapped {@code TodoEntity}
     */
    public Optional<Todo> getByTodoId(@Valid @NotBlank String todoId) {
        return this.todoRepository.todo(todoId);
    }

    /**
     * Retrieve the To-Do record corresponding to the param Id
     *
     * @param todoId the Id of the {@code TodoEntity} to fetch
     * @return {Optional} wrapped {@code TodoEntity}
     */
    public Optional<Todo> getByTodoIdAndSessionId(@Valid @NotBlank String todoId, @Valid @NotBlank String sessionId) {
        return this.todoRepository.todo(todoId, sessionId);
    }

    /**
     * Retrieve the To-Do record corresponding to the param Id
     *
     * @param sessionId the Id of the {@code SessionEntity} to fetch {@code TodoEntity} based off of
     * @return {Optional} wrapped {@code TodoEntity}
     */
    public Optional<TodoList> findAllWithSessionId(@Valid @NotBlank String sessionId) {
        return this.todoRepository.todos(sessionId);
    }

}

