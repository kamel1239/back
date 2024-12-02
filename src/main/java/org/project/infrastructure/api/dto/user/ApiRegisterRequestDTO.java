/*
  2024
*/
package org.project.infrastructure.api.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ApiRegisterRequestDTO(@NotEmpty String username, @NotEmpty String password,
                                    @NotNull ApiUserRoleEnum role) {
    // Default role is user
}
