package org.tadalabs.sample.adapter.web

import org.apache.commons.lang3.time.DateUtils
import spock.lang.Specification

class SessionFactoryTest extends Specification {

    def "session() Should Set the Expiration Date to be one Day from the Initiation Date"() {

        when: "a Session is created"
            def resultant = SessionFactory.session()

        then: "the Expiration Date is one day ahead of the Initiation Date"
            DateUtils.isSameDay(DateUtils.addDays(resultant.initiationDate, 1), resultant.expirationDate)
    }

}
