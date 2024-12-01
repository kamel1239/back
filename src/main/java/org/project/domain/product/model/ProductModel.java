/*
  2024
*/
package org.project.domain.product.model;

import java.time.Instant;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * TODO  add properly enums
 * Represents a product model with various attributes.
 *
 * @param id                the unique identifier of the product
 * @param code              the code of the product
 * @param name              the name of the product
 * @param description       the description of the product
 * @param image             the image URL of the product
 * @param category          the category of the product
 * @param price             the price of the product
 * @param quantity          the quantity of the product in stock
 * @param internalReference the internal reference of the product
 * @param shellId           the shell ID of the product
 * @param inventoryStatus   the inventory status of the product
 * @param rating            the rating of the product
 * @param createdAt         the creation timestamp of the product
 * @param updatedAt         the last update timestamp of the product
 */
public record ProductModel(Long id, String code, String name, String description, String image,
                           String category, @Nullable Double price, int quantity,
                           String internalReference, int shellId, @NonNull String inventoryStatus,
                           double rating, Instant createdAt, Instant updatedAt) {

    /**
     * Constructs a new ProductModel with the specified attributes.
     *
     * @param id          the unique identifier of the product
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param category    the category of the product as a string
     */
    public ProductModel(Long id, String name, String description, Double price, String category,
        String inventoryStatus) {
        this(id, null, name, description, null, category, price, 0, null, 0, inventoryStatus, 0.0,
            null, null);
    }


}
