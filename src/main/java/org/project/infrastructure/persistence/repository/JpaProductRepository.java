/*
  2024
*/
package org.project.infrastructure.persistence.repository;

import org.project.infrastructure.persistence.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, String> {

}
