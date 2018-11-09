package org.tadalabs.sample.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.tadalabs.sample.core.boundary.enter.DebugService
import org.tadalabs.sample.core.domain.Debug
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*

@WebMvcTest
class DebugControllerIntegrationTest extends Specification {

    @Shared
    MockMvc mockMvc

    @Autowired
    DebugService debugService = Stub(DebugService)

    void setup() {
        mockMvc = standaloneSetup(new DebugController(debugService)).build()
    }

    def "Spring Context should load Debug Endpoint"() {

        given: 'a sample Debug object to test against'
            boolean testIsBooleanMode = true
            Debug testDebug = new Debug(isDebugMode: testIsBooleanMode)

        and: 'a Stubbed DebugService that is set to always return the sample Debug object'
            debugService.getDebug() >> testDebug

        expect: "controller is available and returns the expected resultant"
            mockMvc.perform(get("/rest/debug/"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"debugMode\":${testIsBooleanMode}}"))
    }

}
