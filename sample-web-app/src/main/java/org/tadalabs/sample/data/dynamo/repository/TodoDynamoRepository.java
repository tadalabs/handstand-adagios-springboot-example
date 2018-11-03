package org.tadalabs.sample.data.dynamo.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tadalabs.sample.data.dynamo.entity.TodoEntity;

import java.util.Optional;

@EnableScan
@Repository
public interface TodoDynamoRepository extends CrudRepository<TodoEntity, String> {

    Optional<TodoEntity> findByTodoId(String sessionId);

}


