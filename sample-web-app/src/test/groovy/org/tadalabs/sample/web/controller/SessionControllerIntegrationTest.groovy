package org.tadalabs.sample.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.tadalabs.sample.adapter.web.api.SessionList
import org.tadalabs.sample.adapter.web.api.SessionListItem
import org.tadalabs.sample.core.boundary.enter.SessionService
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@WebMvcTest
class SessionControllerIntegrationTest extends Specification {

    @Shared
    MockMvc mockMvc

    @Autowired
    SessionService sessionService = Stub(SessionService)

    void setup() {
        mockMvc = standaloneSetup(new SessionController(sessionService)).build()
    }

    def "Spring Context should load Session Service"() {
        given: 'a sample SessionList object to test against'
            String testSessionId = 'abc-123-def-456'
            String testAddress = '555.555.555.555'
            SessionListItem testSessionListItem = new SessionListItem(testSessionId, new Date(), new Date(), testAddress)
            Optional<SessionList> testSessionList = Optional.of(new SessionList([testSessionListItem]))
        and: 'a Stubbed SessionService that is set to always return a collection containing our sample SessionList'
            sessionService.findAll() >> testSessionList

        expect: "controller is available and returns the expected resultant"
            mockMvc.perform(get("/rest/sessions/"))
                .andExpect(status().isOk())
    }

}
