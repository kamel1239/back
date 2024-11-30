/*
  2024
*/
package org.project.infrastructure.persistence.mongo.module;

public record MongoProduct(String id, String code, String name, String description, String image,
                           String category, double price, int quantity, String internalReference,
                           int shellId, String inventoryStatus, double rating, long createdAt,
                           long updatedAt) {

}