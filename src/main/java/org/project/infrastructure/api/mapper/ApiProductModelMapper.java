/*
  2024
*/
package org.project.infrastructure.api.mapper;

import java.util.Optional;
import lombok.NoArgsConstructor;
import org.project.application.dto.ProductDTO;
import org.project.infrastructure.api.entity.ApiCreateProductEntity;
import org.project.infrastructure.api.entity.ApiPatchProductEntity;
import org.project.infrastructure.api.entity.ApiProductCategoryEnum;
import org.project.infrastructure.api.entity.ApiProductEntity;
import org.project.infrastructure.api.entity.ApiProductInventoryStatusEnum;
import org.springframework.lang.NonNull;

@NoArgsConstructor
public final class ApiProductModelMapper {

    /**
     * Converts a ProductDTO to an ApiProductModel.
     *
     * @param productDTO the product to be converted
     * @return the converted API product model
     */
    public static @NonNull ApiProductEntity toInfra(@NonNull ProductDTO productDTO) {
        return new ApiProductEntity(productDTO.id(), productDTO.name(), productDTO.description(),
            productDTO.price(),
            Optional.ofNullable(productDTO.category()).map(ApiProductCategoryEnum::valueOf)
                .orElse(null), ApiProductInventoryStatusEnum.valueOf(productDTO.inventoryStatus()),
            productDTO.updatedAt().toEpochMilli());
    }

    /**
     * Converts an ApiProductModel to a ProductDTO.
     *
     * @param apiProductModel the API product model to be converted
     * @return the converted product
     */
    public static @NonNull ProductDTO toApp(@NonNull ApiCreateProductEntity apiProductModel) {
        return new ProductDTO(null, apiProductModel.getName(), apiProductModel.getDescription(),
            apiProductModel.getPrice(),
            Optional.ofNullable(apiProductModel.getCategory()).map(Enum::name).orElse(null),
            apiProductModel.getInventoryStatus().name(), null);
    }

    /**
     * Converts an ApiProductModel to a ProductDTO.
     *
     * @param apiProductModel the API product model to be converted
     * @return the converted product
     */
    public static @NonNull ProductDTO toApp(long id,
        @NonNull ApiPatchProductEntity apiProductModel) {
        return new ProductDTO(id, apiProductModel.getName(), apiProductModel.getDescription(),
            apiProductModel.getPrice(),
            Optional.ofNullable(apiProductModel.getCategory()).map(Enum::name).orElse(null),
            apiProductModel.getInventoryStatus().name(), null);
    }


}
