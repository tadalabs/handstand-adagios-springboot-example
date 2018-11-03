package org.tadalabs.sample.web.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class TodoList {

    private final List<TodoListItem> todos;

    public TodoList(List<TodoListItem> todos) {
        this.todos = todos;
    }

    public List<TodoListItem> getTodos() {
        return todos;
    }

    public boolean isEmpty() {
        return this.todos.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TodoList)) return false;

        TodoList todoList = (TodoList) o;

        return new EqualsBuilder()
                .append(getTodos(), todoList.getTodos())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTodos())
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
