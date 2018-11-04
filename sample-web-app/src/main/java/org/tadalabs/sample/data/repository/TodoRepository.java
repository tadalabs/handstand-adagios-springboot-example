package org.tadalabs.sample.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tadalabs.sample.adapter.web.api.TodoList;
import org.tadalabs.sample.core.boundary.exit.ITodoRepository;
import org.tadalabs.sample.core.domain.Todo;
import org.tadalabs.sample.data.dynamo.entity.TodoEntity;
import org.tadalabs.sample.data.dynamo.repository.TodoDynamoRepository;
import org.tadalabs.sample.web.TodoListMapper;
import org.tadalabs.sample.web.TodoMapper;

import java.util.List;
import java.util.Optional;

@Component
public class TodoRepository implements ITodoRepository {

    private final TodoDynamoRepository todoDynamoRepository;

    private final TodoListMapper todoListMapper;

    private final TodoMapper todoMapper;

    @Autowired
    public TodoRepository(TodoDynamoRepository todoDynamoRepository,
                          TodoListMapper todoListMapper, TodoMapper todoMapper) {
        this.todoDynamoRepository = todoDynamoRepository;
        this.todoListMapper = todoListMapper;
        this.todoMapper = todoMapper;
    }

    @Override
    public Optional<TodoList> todos() {
        List<TodoEntity> entities =  (List<TodoEntity>) this.todoDynamoRepository.findAll();
        return this.todoListMapper.toTodoList(entities);
    }

    @Override
    public Optional<TodoList> todos(final String sessionId) {
        Optional<List<TodoEntity>> optional = this.todoDynamoRepository.findAllBySessionId(sessionId);

        if (!optional.isPresent()) {
            return Optional.empty();
        }

        return todoListMapper.toTodoList(optional.get());
    }

    @Override
    public Optional<Todo> todo(final String todoId) {
        Optional<TodoEntity> optional = this.todoDynamoRepository.findByTodoId(todoId);

        if (!optional.isPresent()) {
            return Optional.empty();
        }

        return todoMapper.toDomain(optional.get());
    }

    @Override
    public Optional<Todo> create(Todo todoDomainModel) {
        Optional<TodoEntity> optional = this.todoMapper.fromDomain(todoDomainModel);
        if(!optional.isPresent()) {
            return Optional.empty();
        }
        TodoEntity updatedEntity = this.todoDynamoRepository.save(optional.get());
        return this.todoMapper.toDomain(updatedEntity);
    }

    @Override
    public Optional<Todo> update(Todo todoDomainModel) {
        Optional<TodoEntity> optional = this.todoMapper.fromDomain(todoDomainModel);
        if(!optional.isPresent()) {
            return Optional.empty();
        }
        TodoEntity updatedEntity = this.todoDynamoRepository.save(optional.get());
        return this.todoMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(final String todoId) {
        this.todoDynamoRepository.deleteById(todoId);
    }

}
