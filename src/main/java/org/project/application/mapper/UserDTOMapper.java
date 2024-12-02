/*
  2024
*/
package org.project.application.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.project.application.dto.UserDTO;
import org.project.domain.user.model.UserModel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDTOMapper {

    public static UserDTO toApp(UserModel userModel) {
        return new UserDTO(userModel.getUsername(), userModel.getPassword(), userModel.getRole());
    }

    public static UserModel toDomain(UserDTO userDTO) {
        return new UserModel(userDTO.username(), userDTO.password(), userDTO.role());
    }

}
