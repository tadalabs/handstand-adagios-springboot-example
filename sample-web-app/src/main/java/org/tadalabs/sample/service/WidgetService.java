package org.tadalabs.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tadalabs.sample.domain.Widget;
import org.tadalabs.sample.entity.WidgetEntity;
import org.tadalabs.sample.mapper.WidgetListMapper;
import org.tadalabs.sample.mapper.WidgetMapper;
import org.tadalabs.sample.model.WidgetList;
import org.tadalabs.sample.repository.WidgetRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Service
public class WidgetService {

    private WidgetRepository widgetRepository;

    @Autowired
    private WidgetMapper widgetMapper;

    @Autowired
    private WidgetListMapper widgetListMapper;

    public WidgetService(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    public WidgetList findAll() {
        List<WidgetEntity> entities =
                (List<WidgetEntity>) widgetRepository.findAll();

        return widgetListMapper.toWidgetList(entities);

    }

    /**
     * Inserts a new `Widget`
     *
     * @param widget model to be persisted in the database
     * @return the newly instantiated `Widget` object
     */
    public Widget addWidget(Widget widget) {
        WidgetEntity entity = widgetMapper.fromDomain(widget);

        entity = widgetRepository.save(entity);

        return widgetMapper.toDomain(entity);
    }


    /**
     * Retrieve the Widget record corresponding to the param Id
     *
     * @param widgetId the Id of the Widget object to fetch
     * @return {Widget|null} the persisted Widget object
     */
    public Widget getWidgetById(@Valid @NotBlank String widgetId) {

        WidgetEntity result = null;

        Optional<WidgetEntity> optional = this.widgetRepository.findByWidgetId(widgetId);

        if (optional.isPresent()) {
            result = optional.get();
        }

        return widgetMapper.toDomain(result);
    }

}

