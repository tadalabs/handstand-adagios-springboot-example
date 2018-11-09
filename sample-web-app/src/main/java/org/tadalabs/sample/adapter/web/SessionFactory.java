package org.tadalabs.sample.adapter.web;

import org.apache.commons.lang3.time.DateUtils;
import org.tadalabs.sample.core.domain.Session;

import java.util.Date;

/**
 * Factory Class to handle the Construction of {@code Session} Domain Models
 * @see Session
 */
public final class SessionFactory {

    /**
     * Private to prevent instantiation
     */
    private SessionFactory() {}

    /**
     * Constructs a new {@code Session}
     * @return {Session} constructed Session
     */
    public static Session session() {
        Session newSession = new Session();

        // set the expiration data and initiation date
        newSession.setInitiationDate(new Date());
        newSession.setExpirationDate(DateUtils.addDays(new Date(), 1));

        return newSession;
    }

}
