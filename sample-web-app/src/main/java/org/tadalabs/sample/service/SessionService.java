package org.tadalabs.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tadalabs.sample.domain.Session;
import org.tadalabs.sample.entity.SessionEntity;
import org.tadalabs.sample.mapper.SessionListMapper;
import org.tadalabs.sample.mapper.SessionMapper;
import org.tadalabs.sample.model.SessionList;
import org.tadalabs.sample.repository.SessionRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private SessionListMapper sessionListMapper;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public SessionList findAll() {
        List<SessionEntity> entities =
                (List<SessionEntity>) sessionRepository.findAll();

        return sessionListMapper.toSessionList(entities);
    }

    /**
     * Inserts a new `Session`
     *
     * @param session model to be persisted in the database
     * @return {Optional} the newly instantiated `Session` object
     */
    public Optional<Session> addSession(Session session) {

        Optional<SessionEntity> optional = sessionMapper.fromDomain(session);

        if(!optional.isPresent()) {
            throw new InvalidParameterException("External session model failed to be mapped to an internal entity.");
        }

        SessionEntity entity = optional.get();

        entity = sessionRepository.save(entity);

        return sessionMapper.toDomain(entity);
    }

    /**
     * Retrieve the Session record corresponding to the param Id
     *
     * @param sessionId the Id of the Session object to fetch
     * @return {Optional} the persisted Session object
     */
    public Optional<Session> getSessionById(@Valid @NotBlank String sessionId) {

        Optional<SessionEntity> optional = this.sessionRepository.findBySessionId(sessionId);

        if (!optional.isPresent()) {
            throw new InvalidParameterException("Internal session entity failed to be mapped to an external domain model.");
        }

        return sessionMapper.toDomain(optional.get());
    }

}

