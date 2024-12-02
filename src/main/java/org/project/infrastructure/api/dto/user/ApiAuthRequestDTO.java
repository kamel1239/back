/*
  2024
*/
package org.project.infrastructure.api.dto.user;

import jakarta.validation.constraints.NotEmpty;

public record ApiAuthRequestDTO(@NotEmpty String username, @NotEmpty String password) {

}
