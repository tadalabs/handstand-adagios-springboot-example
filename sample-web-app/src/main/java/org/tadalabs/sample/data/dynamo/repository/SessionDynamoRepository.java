package org.tadalabs.sample.data.dynamo.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tadalabs.sample.data.dynamo.entity.SessionEntity;

import java.util.Optional;

@EnableScan
@Repository
public interface SessionDynamoRepository extends CrudRepository<SessionEntity, String> {

    Optional<SessionEntity> findBySessionId(String sessionId);

    Optional<SessionEntity> findBySessionIdAndAddress(String sessionId, String address);

}


