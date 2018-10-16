package org.tadalabs.sample.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class Widget implements Serializable {

    private String widgetId;
    private String widgetName;


    public Widget() {}

    public Widget(String widgetId, String widgetName) {
        this.widgetId = widgetId;
        this.widgetName = widgetName;
    }

    @Override
    public boolean equals(Object other) {
        return getClass() == other.getClass() && EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getWidgetName() {
        return widgetName;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }


}
