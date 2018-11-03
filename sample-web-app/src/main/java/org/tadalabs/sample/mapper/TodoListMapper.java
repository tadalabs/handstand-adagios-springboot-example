package org.tadalabs.sample.mapper;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.entity.TodoEntity;
import org.tadalabs.sample.model.TodoList;
import org.tadalabs.sample.model.TodoListItem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TodoListMapper {

    public TodoList toTodoList(List<TodoEntity> todoEntityList) {
        return toTodoList(todoEntityList.stream());
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
                todoEntity.getSessionId()
        );
    }
    
}
