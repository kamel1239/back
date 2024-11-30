/*
  2024
*/
package org.project.infrastructure.persistence.mongo.repository;

import org.project.infrastructure.persistence.mongo.model.MongoProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoProductRepository extends MongoRepository<MongoProductModel, String> {

}
