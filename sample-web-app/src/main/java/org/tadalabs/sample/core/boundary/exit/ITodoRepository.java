package org.tadalabs.sample.core.boundary.exit;

import org.tadalabs.sample.adapter.web.api.TodoList;
import org.tadalabs.sample.core.domain.Todo;

import java.util.Optional;

public interface ITodoRepository {

    Optional<TodoList>  todos();

    Optional<TodoList>  todos(final String sessionId);

    Optional<Todo>      todo(final String todoId);

    Optional<Todo>      create(final Todo todoDomainModel);

    Optional<Todo>      update(final Todo todoDomainModel);

    void                delete(final String todoId);

}
