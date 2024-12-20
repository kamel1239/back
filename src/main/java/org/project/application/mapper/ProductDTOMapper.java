/*
  2024
*/
package org.project.application.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.project.application.dto.ProductDTO;
import org.project.domain.product.model.ProductModel;
import org.springframework.lang.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDTOMapper {

    /**
     * Converts a ProductModel to a ProductDTO.
     *
     * @param productModel the product model to be converted
     * @return the converted product
     */
    public static @NonNull ProductDTO toApp(@NonNull ProductModel productModel) {
        return new org.project.application.dto.ProductDTO(productModel.id(), productModel.name(),
            productModel.description(), productModel.price(), productModel.category(),
            productModel.inventoryStatus(), productModel.updatedAt());
    }

    /**
     * Use for creating a new product. Convert a ProductDTO to a ProductModel.
     *
     * @param productDTO the product to be converted
     * @return the converted product model
     */
    public static @NonNull ProductModel toDomain(@NonNull ProductDTO productDTO) {
        return new ProductModel(productDTO.id(), productDTO.name(), productDTO.description(),
            productDTO.price(), productDTO.category(), productDTO.inventoryStatus());
    }


}
