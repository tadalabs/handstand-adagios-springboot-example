package org.tadalabs.sample.mapper;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.entity.WidgetEntity;
import org.tadalabs.sample.model.WidgetList;
import org.tadalabs.sample.model.WidgetListItem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WidgetListMapper {

    public WidgetList toWidgetList(List<WidgetEntity> widgetEntityList) {
        return toWidgetList(widgetEntityList.stream());
    }

    public WidgetList toWidgetList(Stream<WidgetEntity> widgetEntityStream) {
        return new WidgetList(widgetEntityStream
                .map(this::toWidgetListItem)
                .collect(Collectors.toList()));
    }

    protected WidgetListItem toWidgetListItem(WidgetEntity widgetEntity) {
        return new WidgetListItem(
                widgetEntity.getWidgetId(),
                widgetEntity.getWidgetName()
        );
    }
    
}
