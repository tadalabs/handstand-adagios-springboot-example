package org.tadalabs.sample.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tadalabs.sample.entity.WidgetEntity;

import java.util.Optional;


@EnableScan
@Repository
public interface WidgetRepository extends CrudRepository<WidgetEntity, String> {

    Optional<WidgetEntity> findByWidgetId(String widgetId);

}


