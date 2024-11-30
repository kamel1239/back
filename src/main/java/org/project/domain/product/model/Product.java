/*
  2024
*/
package org.project.domain.product.model;

public record Product(String id, String code, String name, String description, String image,
                      String category, double price, int quantity, String internalReference,
                      int shellId, InventoryStatusEnum inventoryStatus, double rating,
                      long createdAt, long updatedAt) {

}
