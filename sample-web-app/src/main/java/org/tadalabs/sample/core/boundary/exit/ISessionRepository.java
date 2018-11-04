package org.tadalabs.sample.core.boundary.exit;

import org.tadalabs.sample.adapter.web.api.SessionList;
import org.tadalabs.sample.core.domain.Session;

import java.util.Optional;

public interface ISessionRepository {

    Optional<SessionList> sessions();

    Optional<Session> session(final String sessionId);

    Optional<Session> sessionByAddress(final String address);

    Optional<Session> create(final Session sessionDomainModel);

    Optional<Session> update(final Session sessionDomainModel);

    void delete(final String sessionId);
    
}
