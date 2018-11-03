package org.tadalabs.sample.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tadalabs.sample.entity.SessionEntity;

import java.util.Optional;

@EnableScan
@Repository
public interface SessionRepository extends CrudRepository<SessionEntity, String> {

    Optional<SessionEntity> findBySessionId(String sessionId);

}


