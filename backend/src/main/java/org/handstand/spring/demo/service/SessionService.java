package org.handstand.spring.demo.service;

import org.handstand.spring.demo.DemoApplication;
import org.handstand.spring.demo.entity.SessionEntity;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.handstand.spring.demo.DemoApplication.validSessions;

@Service("session")
public class SessionService {

    public SessionEntity initiate(SessionEntity session, HttpServletRequest request) {
        int sessionIdLength = 30;
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (sessionIdLength-- != 0) {
            int character = (int) (Math.random() * alphanumeric.length());
            builder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(character));
        }
        session.id = builder.toString();
        session.initiationDate = new Date();
        session.expirationDate = new Date();
        session.address = request.getRemoteAddr();
        Calendar c = Calendar.getInstance();
        c.setTime(session.expirationDate);
        c.add(Calendar.DATE, 1);
        session.expirationDate = c.getTime();
        if (!validSessions.containsKey(session.id)) {
            validSessions.put(session.id, session);
        } else {
            validSessions.replace(session.id, session);
        }

        return session;
    }
    public SessionEntity get(String sessionId) {
        return validSessions.get(sessionId);
    }
    public SessionEntity expire(SessionEntity session) {
        if (validSessions.containsKey(session.id))
            validSessions.remove(session);
        return session;
    }
    public SessionEntity update(SessionEntity session) {
        if (validSessions.containsValue(session)) {
            validSessions.replace(session.id, session);
        }
        return session;
    }
    public boolean validate(String sessionId, String remoteAddr) {
        boolean result = false;
        if (null != sessionId &&
                sessionId.length() > 0 &&
                null != validSessions &&
                validSessions.keySet().size() > 0 &&
                validSessions.containsKey(sessionId) &&
                validSessions.get(sessionId).address.equals(remoteAddr)) {
            result = true;
        }
        return result;
    }
    public List<SessionEntity> list() {
        return new ArrayList<SessionEntity>(validSessions.values());
    }
}