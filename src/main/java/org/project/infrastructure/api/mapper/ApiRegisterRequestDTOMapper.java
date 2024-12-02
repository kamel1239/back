/*
  2024
*/
package org.project.infrastructure.api.mapper;

import lombok.NoArgsConstructor;
import org.project.application.dto.UserDTO;
import org.project.infrastructure.api.dto.user.ApiRegisterRequestDTO;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class ApiRegisterRequestDTOMapper {

    public static UserDTO toApp(ApiRegisterRequestDTO apiRegisterRequestDTO) {
        return new UserDTO(apiRegisterRequestDTO.username(), apiRegisterRequestDTO.password(),
            apiRegisterRequestDTO.role().name());
    }
    
}
