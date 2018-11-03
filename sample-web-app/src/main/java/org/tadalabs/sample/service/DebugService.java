package org.tadalabs.sample.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tadalabs.sample.domain.Debug;

@Component
public class DebugService {

    @Value("${app.debug}")
    private Boolean appDebug;

    public DebugService() {}

    /**
     * Retrieve the Debug Flag from the Build's Metadata
     *
     * @return {Debug|null} the Debug Flag
     */
    public Debug getDebug() {
        return new Debug(this.appDebug);
    }

}
