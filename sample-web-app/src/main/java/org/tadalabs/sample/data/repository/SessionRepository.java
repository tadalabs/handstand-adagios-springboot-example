package org.tadalabs.sample.data.repository;

import org.tadalabs.sample.adapter.web.api.SessionList;
import org.tadalabs.sample.core.boundary.exit.ISessionRepository;
import org.tadalabs.sample.core.domain.Session;

public class SessionRepository implements ISessionRepository {

    @Override
    public SessionList sessions() {
        return null;
    }

    @Override
    public Session session(String sessionId) {
        return null;
    }

    @Override
    public void create(Session sessionDomainModel) {

    }

    @Override
    public void update(Session sessionDomainModel) {

    }

    @Override
    public void delete(String sessionId) {

    }

}
