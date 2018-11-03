package org.tadalabs.sample.web;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.data.dynamo.entity.SessionEntity;
import org.tadalabs.sample.web.api.SessionListItem;
import org.tadalabs.sample.web.api.SessionList;

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
