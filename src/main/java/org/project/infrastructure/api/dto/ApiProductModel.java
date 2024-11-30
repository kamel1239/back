/*
  2024
*/
package org.project.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.Nullable;

@JsonInclude(Include.NON_NULL)
public record ApiProductModel(String id, @NotEmpty String name, String description, Double price,
                              @Nullable ApiProductCategoryEnum category) {
    
}
