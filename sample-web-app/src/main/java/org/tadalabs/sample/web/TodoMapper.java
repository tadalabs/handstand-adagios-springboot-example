package org.tadalabs.sample.web;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.core.domain.Todo;
import org.tadalabs.sample.data.dynamo.entity.TodoEntity;

import java.util.Optional;

@Component
public class TodoMapper {

    public Optional<TodoEntity> fromDomain(Todo todo) {
        if(todo == null) {
            return Optional.empty();
        }

        TodoEntity entity = new TodoEntity();
        entity.setTodoId(todo.getTodoId());
        entity.setSessionId(todo.getSessionId());
        entity.setValue(todo.getValue());
        return Optional.of(entity);
    }

    public Optional<Todo> toDomain(TodoEntity entity) {
        if(entity == null) {
            return Optional.empty();
        }

        return Optional.of(new Todo(
                entity.getTodoId(),
                entity.getValue(),
                entity.getSessionId()
        ));
    }
    
}
