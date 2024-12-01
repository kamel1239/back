/*
  2024
*/
package org.project.infrastructure.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ApiProductCategoryEnum {

    @JsonProperty("Accessories") ACCESSORIES,
    @JsonProperty("Clothing") CLOTHING,
    @JsonProperty("Electronics") ELECTRONICS,
    @JsonProperty("Fitness") FITNESS,

}
