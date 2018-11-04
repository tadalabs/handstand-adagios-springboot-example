package org.tadalabs.sample.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tadalabs.sample.adapter.web.api.SessionList;
import org.tadalabs.sample.core.boundary.exit.ISessionRepository;
import org.tadalabs.sample.core.domain.Session;
import org.tadalabs.sample.data.dynamo.entity.SessionEntity;
import org.tadalabs.sample.data.dynamo.repository.SessionDynamoRepository;
import org.tadalabs.sample.web.SessionListMapper;
import org.tadalabs.sample.web.SessionMapper;

import java.util.List;
import java.util.Optional;

@Component
public class SessionRepository implements ISessionRepository {

    private final SessionDynamoRepository sessionDynamoRepository;

    private final SessionMapper sessionMapper;

    private final SessionListMapper sessionListMapper;

    @Autowired
    public SessionRepository(SessionDynamoRepository sessionDynamoRepository,
                             SessionMapper sessionMapper, SessionListMapper sessionListMapper) {

        this.sessionDynamoRepository = sessionDynamoRepository;
        this.sessionMapper = sessionMapper;
        this.sessionListMapper = sessionListMapper;
    }

    @Override
    public Optional<SessionList> sessions() {

        List<SessionEntity> entities = (List<SessionEntity>) sessionDynamoRepository.findAll();
        return sessionListMapper.toSessionList(entities);
    }

    @Override
    public Optional<Session> session(final String sessionId) {

        Optional<SessionEntity> optional = this.sessionDynamoRepository.findBySessionId(sessionId);
        if (!optional.isPresent()) {
            return Optional.empty();
        }

        return sessionMapper.toDomain(optional.get());
    }

    @Override
    public Optional<Session> create(final Session sessionDomainModel) {

        Optional<SessionEntity> optional = this.sessionMapper.fromDomain(sessionDomainModel);
        if(!optional.isPresent()) {
            return Optional.empty();
        }

        SessionEntity updatedEntity = this.sessionDynamoRepository.save(optional.get());
        return this.sessionMapper.toDomain(updatedEntity);
    }

    @Override
    public Optional<Session> update(final Session sessionDomainModel) {

        Optional<SessionEntity> optional = this.sessionMapper.fromDomain(sessionDomainModel);
        if(!optional.isPresent()) {
            return Optional.empty();
        }

        SessionEntity updatedEntity = this.sessionDynamoRepository.save(optional.get());
        return this.sessionMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(final String sessionId) {
        this.sessionDynamoRepository.deleteById(sessionId);
    }

}
