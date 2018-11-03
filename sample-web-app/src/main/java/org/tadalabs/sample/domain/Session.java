package org.tadalabs.sample.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class Session {

    private String sessionId;

    private Date initiationDate;
    private Date expirationDate;

    private String address;

    public Session() {}

    public Session(String sessionId, Date initiationDate, Date expirationDate, String address) {
        this.sessionId = sessionId;
        this.initiationDate = initiationDate;
        this.expirationDate = expirationDate;
        this.address = address;
    }
    
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Date initiationDate) {
        this.initiationDate = initiationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Session)) return false;

        Session session = (Session) o;

        return new EqualsBuilder()
                .append(sessionId, session.sessionId)
                .append(initiationDate, session.initiationDate)
                .append(expirationDate, session.expirationDate)
                .append(address, session.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sessionId)
                .append(initiationDate)
                .append(expirationDate)
                .append(address)
                .toHashCode();
    }

    @Override
    public String toString() {
        return String.format("Session{sessionId='%s', initiationDate=%s, expirationDate=%s, address='%s'}",
                sessionId, initiationDate, expirationDate, address);
    }
}
