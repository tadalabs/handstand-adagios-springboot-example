package org.tadalabs.sample.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Version {

    private String version;

    public Version() {}

    public Version(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Version)) return false;

        Version version1 = (Version) o;

        return new EqualsBuilder()
                .append(getVersion(), version1.getVersion())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getVersion())
                .toHashCode();
    }

    @Override
    public String toString() {
        return String.format("Version{version='%s'}", version);
    }

}
