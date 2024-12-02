/*
  2024
*/

package org.project.infrastructure.api.dto.product;

import org.springframework.lang.Nullable;

/**
 * Entity for patching a product via API.
 */
public class ApiPatchProductDTO extends ApiCreateProductDTO {

    public ApiPatchProductDTO(String name, String description, Double price,
        @Nullable ApiProductCategoryEnum category, ApiProductInventoryStatusEnum inventoryStatus) {
        super(name, description, price, category, inventoryStatus);
    }

}
