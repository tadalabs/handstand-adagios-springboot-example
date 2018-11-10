package org.tadalabs.sample.core.boundary.enter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tadalabs.sample.core.domain.Debug;

@Component
public class DebugService {

    @Value("${app.debug}")
    private Boolean appDebug;

    public DebugService() {}

    /**
     * Retrieve the Debug Flag from the Build's Metadata
     *
     * @return {Debug} the Debug Flag
     */
    public Debug getDebug() {
        return new Debug(this.appDebug);
    }

}
