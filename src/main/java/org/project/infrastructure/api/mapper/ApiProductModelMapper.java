/*
  2024
*/
package org.project.infrastructure.api.mapper;

import java.util.Optional;
import lombok.NoArgsConstructor;
import org.project.application.dto.ProductDTO;
import org.project.infrastructure.api.dto.ApiProductCategoryEnum;
import org.project.infrastructure.api.dto.ApiProductModel;
import org.springframework.lang.NonNull;

@NoArgsConstructor
public final class ApiProductModelMapper {

    /**
     * Converts a ProductDTO to an ApiProductModel.
     *
     * @param productDTO the product to be converted
     * @return the converted API product model
     */
    public static @NonNull ApiProductModel toInfra(@NonNull ProductDTO productDTO) {
        return new ApiProductModel(productDTO.id(), productDTO.name(), productDTO.description(),
            productDTO.price(),
            Optional.ofNullable(productDTO.category()).map(ApiProductCategoryEnum::valueOf)
                .orElse(null));
    }

    /**
     * Converts an ApiProductModel to a ProductDTO.
     *
     * @param apiProductModel the API product model to be converted
     * @return the converted product data transfer object
     */
    public static @NonNull ProductDTO toApp(@NonNull ApiProductModel apiProductModel) {
        return new ProductDTO(apiProductModel.id(), apiProductModel.name(),
            apiProductModel.description(), apiProductModel.price(),
            Optional.ofNullable(apiProductModel.category()).map(Enum::name).orElse(null));
    }

}
