package org.tadalabs.sample.core.boundary.enter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tadalabs.sample.core.domain.Session;
import org.tadalabs.sample.data.repository.SessionRepository;
import org.tadalabs.sample.adapter.web.api.SessionList;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {

        this.sessionRepository = sessionRepository;
    }

    /**
     * Fetches all Sessions
     * @return {SessionList} Encapsulated Collection of {@code SessionListItem}
     */
    public Optional<SessionList> findAll() {
        return this.sessionRepository.sessions();
    }

    /**
     * Inserts a new `Session`
     *
     * @param session model to be persisted
     * @return {Optional} the newly instantiated `Session` object
     */
    public Optional<Session> addSession(Session session) {
        return this.sessionRepository.create(session);
    }

    /**
     * Retrieve the Session record corresponding to the param Id
     *
     * @param sessionId the Id of the Session object to fetch
     * @return {Optional} the persisted Session object
     */
    public Optional<Session> getSessionById(@Valid @NotBlank String sessionId) {
        return this.sessionRepository.session(sessionId);
    }

    /**
     * Check whether the Session is still Valid or Not
     *
     * @param sessionId the unique Identifier associated with the Session
     * @param remoteAddress the sender's remote Address
     *
     * @return {boolean} True if the Session is valid, false if not
     */
    public boolean isSessionValid(@Valid @NotBlank String sessionId, @Valid @NotBlank String remoteAddress) {

        Optional<Session> optionalSession = this.getSessionById(sessionId);
        if(!optionalSession.isPresent()) {
            // session does not exist
            return false;
        }

        Session session = optionalSession.get();
        return Objects.equals(session.getAddress(), remoteAddress);
    }

    /**
     * Expires the Session
     * @param sessionId the unique Identifier corresponding to the session to Expire
     */
    public void expireSession(@Valid @NotBlank String sessionId) {
        this.sessionRepository.delete(sessionId);
    }

}

