/*
  2024
*/
package org.project.application.dto;

import java.time.Instant;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * TODO  add missing attributes
 * DTO for Product.
 *
 * @param id              the unique identifier of the product
 * @param name            the name of the product
 * @param description     the description of the product
 * @param price           the price of the product
 * @param category        the category of the product
 * @param inventoryStatus the inventory status of the product
 * @param updatedAt       the last update timestamp of the product
 */
public record ProductDTO(Long id, String name, String description, Double price,
                         @Nullable String category, @NonNull String inventoryStatus,
                         Instant updatedAt) {

}
