package org.tadalabs.sample.adapter.web.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class SessionListItem {

    private final String sessionId;

    private final Date initiationDate;
    private final Date expirationDate;

    private final String address;

    public SessionListItem(final String sessionId, final Date initiationDate, final Date expirationDate, final String address) {
        this.sessionId = sessionId;
        this.initiationDate = initiationDate;
        this.expirationDate = expirationDate;
        this.address = address;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Date getInitiationDate() {
        return initiationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SessionListItem)) return false;

        SessionListItem that = (SessionListItem) o;

        return new EqualsBuilder()
                .append(getSessionId(), that.getSessionId())
                .append(getInitiationDate(), that.getInitiationDate())
                .append(getExpirationDate(), that.getExpirationDate())
                .append(getAddress(), that.getAddress())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getSessionId())
                .append(getInitiationDate())
                .append(getExpirationDate())
                .append(getAddress())
                .toHashCode();
    }

    @Override
    public String toString() {
        return String.format("SessionListItem{sessionId='%s', initiationDate=%s, expirationDate=%s, address='%s'}",
                sessionId, initiationDate, expirationDate, address);
    }

}
