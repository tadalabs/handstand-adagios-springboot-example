package org.tadalabs.sample.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Debug {

    private boolean isDebugMode;

    public Debug() {}

    public Debug(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

    public boolean isDebugMode() {
        return isDebugMode;
    }

    public void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Debug)) return false;

        Debug debug = (Debug) o;

        return new EqualsBuilder()
                .append(isDebugMode(), debug.isDebugMode())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(isDebugMode())
                .toHashCode();
    }

    @Override
    public String toString() {
        return String.format("Debug{isDebugMode=%s}", isDebugMode);
    }

}
