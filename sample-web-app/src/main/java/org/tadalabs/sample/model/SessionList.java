package org.tadalabs.sample.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class SessionList {

    private List<SessionListItem> sessions;

    public SessionList(List<SessionListItem> sessions) {
        this.sessions = sessions;
    }

    public List<SessionListItem> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionListItem> sessions) {
        this.sessions = sessions;
    }

    public boolean isEmpty() {
        return this.sessions.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SessionList)) return false;

        SessionList that = (SessionList) o;

        return new EqualsBuilder()
                .append(getSessions(), that.getSessions())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getSessions())
                .toHashCode();
    }

    @Override
    public String toString() {
        return String.format("SessionList{sessions=%s}", sessions);
    }

}
