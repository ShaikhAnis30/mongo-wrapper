package com.mongo.mongoWrapper.repo;

import com.mongo.mongoWrapper.Model.PhysicalFile;
import org.apache.zookeeper.data.Id;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveMongoRepo extends ReactiveCrudRepository<PhysicalFile, String> {


}
