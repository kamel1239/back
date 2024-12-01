/*
  2024
*/

package org.project.infrastructure.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.project.domain.user.model.UserModel;
import org.project.infrastructure.persistence.model.UserEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserEntityModelMapper {

    public static UserModel toDomain(
        org.project.infrastructure.persistence.model.UserEntity userEntity) {
        return new UserModel(userEntity.getUsername(), userEntity.getPassword(),
            userEntity.getRole());
    }

    public static UserEntity toInfra(UserModel userModel) {
        return new UserEntity(null, userModel.username(), userModel.password(), userModel.role());
    }

}
