package org.tadalabs.sample.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.tadalabs.sample.core.boundary.enter.VersionService
import org.tadalabs.sample.core.domain.Version
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@WebMvcTest
class VersionControllerIntegrationTest extends Specification {

    @Shared
    MockMvc mockMvc

    @Autowired
    VersionService versionService = Stub(VersionService)

    void setup() {
        mockMvc = standaloneSetup(new VersionController(versionService)).build()
    }

    def "Spring Context should load Version Endpoint"() {

        given: 'a sample Version object to test against'
            boolean testVersionNumber = '4.0'
            Version testVersion = new Version(version: testVersionNumber)

        and: 'a Stubbed VersionService that is set to always return the sample Version object'
            versionService.getVersion() >> testVersion

        expect: "controller is available and returns the expected resultant"
            mockMvc.perform(get("/rest/version/"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"version\":\"${testVersionNumber}\"}"))
    }

}
