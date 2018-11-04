package org.tadalabs.sample.core.boundary.exit;

import org.tadalabs.sample.adapter.web.api.SessionList;
import org.tadalabs.sample.core.domain.Session;

public interface ISessionRepository {

    SessionList sessions();

    Session session(final String sessionId);

    void create(final Session sessionDomainModel);

    void update(final Session sessionDomainModel);

    void delete(final String sessionId);
    
}
