package org.tadalabs.sample.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TodoListItem {

    private String todoId;
    private String value;
    private String sessionId;

    public TodoListItem(String todoId, String value, String sessionId) {
        this.todoId = todoId;
        this.value = value;
        this.sessionId = sessionId;
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

    
}
