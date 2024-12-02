/*
  2024
*/

package org.project.infrastructure.api.dto.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

/**
 * Entity for creating a product via API.
 */
@AllArgsConstructor
@Getter
public class ApiCreateProductDTO {

    private final @NotEmpty String name;
    private final String description;
    private final Double price;
    @Nullable
    private final ApiProductCategoryEnum category;
    private final @NotNull ApiProductInventoryStatusEnum inventoryStatus;

    // TODO add post-construct method to validate the object
    // TODO add missing attributes
}
