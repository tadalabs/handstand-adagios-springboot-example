package org.tadalabs.sample.web

import org.apache.commons.lang3.time.DateUtils
import org.tadalabs.sample.core.domain.Session
import org.tadalabs.sample.data.dynamo.entity.SessionEntity
import spock.lang.Specification

class SessionMapperTest extends Specification {

    def "fromDomain() Should Properly map a Session Domain Model to an Entity "() {

        given: "a sample Session Domain Model instance"
            def testInitiationDate = DateUtils.addDays(new Date(), 50)
            def testExpirationDate = DateUtils.addDays(new Date(), 75)

            def testSession = new Session('abc-123-def-456', testInitiationDate, testExpirationDate, 'NCC-1701')

        when: "our sample Session is mapped to an Entity"
            Optional<SessionEntity> resultant = new SessionMapper().fromDomain(testSession)

        then: "the Optional should have a present Entity"
            resultant.isPresent()

        and: "the Resultant Session Entity's Properties should match our sample Session Domain Model's"
            with(resultant.get() as SessionEntity) {
                initiationDate == testInitiationDate
                expirationDate == testExpirationDate
                sessionId == testSession.sessionId
                address == testSession.address
            }
    }

    def "fromDomain() Should return an Empty Optional when a null Session Domain Model is passed in"() {

        given: "a null Session Domain Model instance"
            def testSession = null

        when: "our sample Session is mapped to an Entity"
            Optional<SessionEntity> resultant = new SessionMapper().fromDomain(testSession)

        then: "the Optional should not have a present Entity"
            !resultant.isPresent()
    }




}
