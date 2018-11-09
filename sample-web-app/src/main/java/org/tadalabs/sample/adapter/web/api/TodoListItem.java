package org.tadalabs.sample.adapter.web.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.tadalabs.sample.core.domain.TodoState;

public class TodoListItem {

    private final String todoId;
    private final String value;
    private final String sessionId;
    private final TodoState todoState;

    public TodoListItem(String todoId, String value, String sessionId, TodoState todoState) {
        this.todoId = todoId;
        this.value = value;
        this.sessionId = sessionId;
        this.todoState = todoState;
    }

    @Override
    public boolean equals(Object other) {
        return getClass() == other.getClass() && EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getTodoId() {
        return todoId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getValue() {
        return value;
    }

    public TodoState getTodoState() {
        return todoState;
    }

}
