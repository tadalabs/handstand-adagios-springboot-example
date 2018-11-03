package org.tadalabs.sample.mapper;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.entity.SessionEntity;
import org.tadalabs.sample.model.SessionList;
import org.tadalabs.sample.model.SessionListItem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SessionListMapper {

    public SessionList toSessionList(List<SessionEntity> sessionEntityList) {
        return toSessionList(sessionEntityList.stream());
    }

    public SessionList toSessionList(Stream<SessionEntity> sessionEntityStream) {
        return new SessionList(sessionEntityStream
                .map(this::toSessionListItem)
                .collect(Collectors.toList()));
    }

    protected SessionListItem toSessionListItem(SessionEntity sessionEntity) {
        return new SessionListItem(
                sessionEntity.getSessionId(),
                sessionEntity.getInitiationDate(),
                sessionEntity.getExpirationDate(),
                sessionEntity.getAddress()
        );
    }
    
}
