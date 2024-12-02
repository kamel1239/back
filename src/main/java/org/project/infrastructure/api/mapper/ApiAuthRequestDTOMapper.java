/*
  2024
*/
package org.project.infrastructure.api.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.project.application.dto.UserDTO;
import org.project.infrastructure.api.dto.user.ApiAuthRequestDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiAuthRequestDTOMapper {

    public static UserDTO toApp(ApiAuthRequestDTO apiAuthRequestDTO) {
        return new UserDTO(apiAuthRequestDTO.username(), apiAuthRequestDTO.password());
    }

}
