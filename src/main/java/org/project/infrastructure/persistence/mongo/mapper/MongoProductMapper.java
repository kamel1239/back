/*
  2024
*/

package org.project.infrastructure.persistence.mongo.mapper;

import lombok.NoArgsConstructor;
import org.project.domain.product.model.Product;
import org.project.infrastructure.persistence.mongo.module.MongoProduct;

@NoArgsConstructor
public final class MongoProductMapper {

    public static Product toDomain(MongoProduct mongoProduct) {
        return new Product(mongoProduct.id(), mongoProduct.code(), mongoProduct.name(),
            mongoProduct.description(), mongoProduct.image(), mongoProduct.category(),
            mongoProduct.price(), mongoProduct.quantity(), mongoProduct.internalReference(),
            mongoProduct.shellId(), org.project.domain.product.model.InventoryStatusEnum.valueOf(
            mongoProduct.inventoryStatus()), mongoProduct.rating(), mongoProduct.createdAt(),
            mongoProduct.updatedAt());
    }

    public static MongoProduct toInfra(org.project.domain.product.model.Product product) {
        return new MongoProduct(product.id(), product.code(), product.name(), product.description(),
            product.image(), product.category(), product.price(), product.quantity(),
            product.internalReference(), product.shellId(), product.inventoryStatus().name(),
            product.rating(), product.createdAt(), product.updatedAt());
    }

}
