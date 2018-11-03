package org.tadalabs.sample.core.boundary.enter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tadalabs.sample.core.domain.Version;

@Component
public class VersionService {

    @Value("${app.version}")
    private String appVersion;

    public VersionService() {}

    /**
     * Retrieve the Version from the Build's Metadata
     *
     * @return {Version} the Version Number
     */
    public Version getVersion() {
        return new Version(this.appVersion);
    }

}
