/*
  2024
*/
package org.project.infrastructure.persistence.mongo.model;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record MongoProductModel(@Id String id, String code, String name, String description,
                                String image, String category, Double price, int quantity,
                                String internalReference, int shellId, String inventoryStatus,
                                double rating, @CreatedDate Instant createdAt,
                                @LastModifiedDate Instant updatedAt) {

}