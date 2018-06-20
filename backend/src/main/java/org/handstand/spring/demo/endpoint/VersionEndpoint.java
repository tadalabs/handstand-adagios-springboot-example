package org.handstand.spring.demo.endpoint;

import org.handstand.spring.demo.entity.VersionEntity;
import org.handstand.spring.demo.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionEndpoint {

    @Autowired
    private VersionService provider;

    @RequestMapping("/api/v1/version")
    public VersionEntity version() {
        return provider.currentVersion();
    }
}
