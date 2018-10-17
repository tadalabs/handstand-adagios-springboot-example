package org.handstand.spring.demo.service;


import org.handstand.spring.demo.entity.TodoEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("todo")
public class TodoService {
    private Map<String, List<TodoEntity>> storedTodos = new HashMap<String, List<TodoEntity>>();
    public List<TodoEntity> list(String sessionId) {
        return storedTodos.get(sessionId);
    }
    public void initialize(String sessionId) {
        storedTodos.put(sessionId, new ArrayList<TodoEntity>());
    }
    public void save(String sessionId, TodoEntity todo) {
        List<TodoEntity> todos = list(sessionId);
        todo.sessionId = sessionId;
        if (null == todo.id || todo.id.length() == 0)
            todo.id = String.valueOf(new Date().getTime());
        TodoEntity existingById = todos.stream()
                .filter(e -> e.id.equals(todo.id))
                .findFirst().orElse(null);
        if (null == existingById && !todos.contains(todo)) {
            todos.add(todo);
        } else {
            existingById.value = todo.value;
            existingById.state = todo.state;
        }
    }
    public void remove(String sessionId, TodoEntity todo) {
        list(sessionId).remove(todo);
    }
}