/*
  2024
*/
package org.project.infrastructure.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

/**
 * Represents a product entity with various attributes.
 *
 * @param id              the unique identifier of the product
 * @param name            the name of the product
 * @param description     the description of the product
 * @param price           the price of the product
 * @param category        the category of the product
 * @param inventoryStatus the inventory status of the product
 */
@JsonInclude(Include.NON_NULL)
public record ApiProductEntity(long id, @NotEmpty String name, String description, Double price,
                               @Nullable ApiProductCategoryEnum category,
                               @NotNull ApiProductInventoryStatusEnum inventoryStatus,
                               long updatedAt) {

    // TODO add missing attributes
}
