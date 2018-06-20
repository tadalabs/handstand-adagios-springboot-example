package org.handstand.spring.demo.service;

import org.handstand.spring.demo.entity.VersionEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("version")
public class VersionService {


    @Value("${app.version}")
    private String CURRENT_VERSION;

    public VersionEntity currentVersion() {
        return new VersionEntity(CURRENT_VERSION);
    }
}