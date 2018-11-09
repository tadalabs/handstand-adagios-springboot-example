package org.tadalabs.sample.web;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.data.dynamo.entity.TodoEntity;
import org.tadalabs.sample.adapter.web.api.TodoList;
import org.tadalabs.sample.adapter.web.api.TodoListItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TodoListMapper {

    public Optional<TodoList> toTodoList(List<TodoEntity> todoEntityList) {
        return Optional.of(toTodoList(todoEntityList.stream()));
    }

    public TodoList toTodoList(Stream<TodoEntity> todoEntityStream) {
        return new TodoList(todoEntityStream
                .map(this::toTodoListItem)
                .collect(Collectors.toList()));
    }

    protected TodoListItem toTodoListItem(TodoEntity todoEntity) {
        return new TodoListItem(
                todoEntity.getTodoId(),
                todoEntity.getValue(),
                todoEntity.getSessionId(),
                todoEntity.getTodoState()
        );
    }
    
}
