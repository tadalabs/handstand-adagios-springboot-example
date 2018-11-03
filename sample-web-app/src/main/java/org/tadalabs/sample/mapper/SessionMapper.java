package org.tadalabs.sample.mapper;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.domain.Session;
import org.tadalabs.sample.entity.SessionEntity;

import java.util.Optional;

@Component
public class SessionMapper {

    public Optional<SessionEntity> fromDomain(Session session) {
        if(session == null) {
            // skip execution if the param Session is null
            return Optional.empty();
        }

        SessionEntity entity = new SessionEntity();
        entity.setSessionId(session.getSessionId());
        entity.setAddress(session.getAddress());
        entity.setExpirationDate(session.getExpirationDate());
        entity.setInitiationDate(session.getInitiationDate());
        return Optional.of(entity);
    }

    public Optional<Session> toDomain(SessionEntity entity) {
        if(entity == null) {
            // skip execution if the param entity is null
            return Optional.empty();
        }

        return Optional.of(new Session(
                entity.getSessionId(),
                entity.getInitiationDate(),
                entity.getExpirationDate(),
                entity.getAddress()
        ));
    }
    
}
