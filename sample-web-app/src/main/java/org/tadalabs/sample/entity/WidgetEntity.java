package org.tadalabs.sample.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@DynamoDBTable(tableName = "widget")
public class WidgetEntity {

    private String widgetId;
    private String widgetName;

    private String createdDate;
    private String lastModifiedDate;

    public WidgetEntity() {}

    public WidgetEntity(String widgetName) {
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

    @DynamoDBHashKey(attributeName = "widget_id")
    @DynamoDBAutoGeneratedKey
    public String getWidgetId() {
        return this.widgetId;
    }

    @DynamoDBAttribute(attributeName="widget_name")
    public String getWidgetName() {
        return widgetName;
    }


    @DynamoDBAttribute(attributeName = "created_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @DynamoDBAttribute(attributeName = "last_modified_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.ALWAYS)
    public String getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setWidgetId(String id) {
        this.widgetId = id;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
