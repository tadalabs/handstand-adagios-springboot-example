package org.tadalabs.sample.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tadalabs.sample.domain.Version;

@Component
public class VersionService {

    @Value("${app.version}")
    private String appVersion;

    public VersionService() {}

    /**
     * Retrieve the Version from the Build's Metadata
     *
     * @return {Version|null} the Version Number
     */
    public Version getVersion() {
        return new Version(this.appVersion);
    }

}
