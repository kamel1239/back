/*
  2024
*/

package org.project.infrastructure.api.entity;

import org.springframework.lang.Nullable;

/**
 * Entity for patching a product via API.
 */
public class ApiPatchProductEntity extends ApiCreateProductEntity {

    public ApiPatchProductEntity(String name, String description, Double price,
        @Nullable ApiProductCategoryEnum category, ApiProductInventoryStatusEnum inventoryStatus) {
        super(name, description, price, category, inventoryStatus);
    }

}
