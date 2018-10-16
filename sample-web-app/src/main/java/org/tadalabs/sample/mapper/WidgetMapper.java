package org.tadalabs.sample.mapper;

import org.springframework.stereotype.Component;
import org.tadalabs.sample.domain.Widget;
import org.tadalabs.sample.entity.WidgetEntity;

@Component
public class WidgetMapper {
    
    public WidgetEntity fromDomain(Widget widget) {
        if(widget == null) {
            // skip execution if the param Widget is null
            return null;
        }

        WidgetEntity entity = new WidgetEntity();
        entity.setWidgetId(widget.getWidgetId());
        entity.setWidgetName(widget.getWidgetName());
        return entity;
    }

    public Widget toDomain(WidgetEntity entity) {
        if(entity == null) {
            // skip execution if the param entity is null
            return null;
        }

        return new Widget(
                entity.getWidgetId(),
                entity.getWidgetName()
        );
    }
}
