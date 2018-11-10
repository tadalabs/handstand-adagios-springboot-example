package org.tadalabs.sample.data.dynamo.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import org.apache.commons.lang3.StringUtils;
import org.tadalabs.sample.core.domain.TodoState;

public class TodoStateConverter implements DynamoDBTypeConverter<String, TodoState> {

    @Override
    public String convert(TodoState todoState) {

        if(todoState == null) {
            return "";
        }

        return todoState.toString();
    }

    @Override
    public TodoState unconvert(String s) {

        if(StringUtils.isEmpty(s)) {
            return TodoState.UNKNOWN;
        }

        return TodoState.valueOf(s);
    }

}
