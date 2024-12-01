/*
  2024
*/

package org.project.infrastructure.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.project.domain.product.model.ProductModel;
import org.project.infrastructure.persistence.model.ProductEntity;
import org.springframework.lang.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductEntityModelMapper {

    /**
     * Converts a ProductEntity to a ProductModel.
     *
     * @param productEntity the product to be converted
     * @return the converted product model
     */
    public static @NonNull ProductModel toDomain(@NonNull ProductEntity productEntity) {
        return new ProductModel(productEntity.getId(), productEntity.getCode(),
            productEntity.getName(), productEntity.getDescription(), productEntity.getImage(),
            productEntity.getCategory(), productEntity.getPrice(), productEntity.getQuantity(),
            productEntity.getInternalReference(), productEntity.getShellId(),
            productEntity.getInventoryStatus(), productEntity.getRating(),
            productEntity.getCreatedAt(), productEntity.getUpdatedAt());
    }

    /**
     * Converts a ProductModel to a ProductEntity.
     *
     * @param productModel the product model to be converted
     * @return the converted product entity
     */
    public static @NonNull ProductEntity toInfra(@NonNull ProductModel productModel) {
        return new ProductEntity(productModel.id(), productModel.code(), productModel.name(),
            productModel.description(), productModel.image(), productModel.category(),
            productModel.price(), productModel.quantity(), productModel.internalReference(),
            productModel.shellId(), productModel.inventoryStatus(), productModel.rating(), null,
            null);
    }

}
