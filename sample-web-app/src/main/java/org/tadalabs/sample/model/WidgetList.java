package org.tadalabs.sample.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class WidgetList {

    private List<WidgetListItem> widgets;

    public WidgetList(List<WidgetListItem> widgets) {
        this.widgets = widgets;
    }

    public List<WidgetListItem> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetListItem> widgets) {
        this.widgets = widgets;
    }

    public boolean isEmpty() {
        return this.widgets.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
