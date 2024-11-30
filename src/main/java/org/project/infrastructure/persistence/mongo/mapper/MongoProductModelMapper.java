/*
  2024
*/

package org.project.infrastructure.persistence.mongo.mapper;

import lombok.NoArgsConstructor;
import org.project.domain.product.model.ProductModel;
import org.project.infrastructure.api.dto.ApiProductInventoryStatusEnum;
import org.project.infrastructure.persistence.mongo.model.MongoProductModel;
import org.springframework.lang.NonNull;

@NoArgsConstructor
public final class MongoProductModelMapper {

    /**
     * Converts a MongoProductModel to a ProductModel.
     *
     * @param mongoProductModel the product to be converted
     * @return the converted product model
     */
    public static @NonNull ProductModel toDomain(@NonNull MongoProductModel mongoProductModel) {
        return new ProductModel(mongoProductModel.id(), mongoProductModel.code(),
            mongoProductModel.name(), mongoProductModel.description(), mongoProductModel.image(),
            mongoProductModel.category(), mongoProductModel.price(), mongoProductModel.quantity(),
            mongoProductModel.internalReference(), mongoProductModel.shellId(),
            ApiProductInventoryStatusEnum.valueOf(
                mongoProductModel.inventoryStatus()), mongoProductModel.rating(),
            mongoProductModel.createdAt(), mongoProductModel.updatedAt());
    }

    /**
     * Converts a ProductModel to a MongoProductModel.
     *
     * @param productModel the product model to be converted
     * @return the converted MongoDB product model
     */
    public static @NonNull MongoProductModel toInfra(@NonNull ProductModel productModel) {
        return new MongoProductModel(productModel.id(), productModel.code(), productModel.name(),
            productModel.description(), productModel.image(), productModel.category(),
            productModel.price(), productModel.quantity(), productModel.internalReference(),
            productModel.shellId(), productModel.inventoryStatus().name(), productModel.rating(),
            productModel.createdAt(), productModel.updatedAt());
    }

}
