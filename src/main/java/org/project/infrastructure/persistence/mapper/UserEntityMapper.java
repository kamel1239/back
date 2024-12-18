/*
  2024
*/

package org.project.infrastructure.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.project.domain.user.model.UserModel;
import org.project.infrastructure.persistence.model.UserEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserEntityMapper {

    public static UserModel toDomain(UserEntity userEntity) {
        return UserModel.builder().id(userEntity.getId()).version(userEntity.getVersion())
            .username(userEntity.getUsername()).password(userEntity.getPassword())
            .tokenIdList(userEntity.getTokens().stream().map(TokenEntityMapper::toDomain).toList())
            .role(userEntity.getRole()).build();
    }

    public static UserEntity toInfra(UserModel userModel) {
        // Create UserEntity with tokens use Getters from UserModel
        var userEntity = new UserEntity(userModel.getId(), userModel.getVersion(),
            userModel.getUsername(), userModel.getPassword(), null, userModel.getRole());
        // Set tokens to UserEntity
        userEntity.setTokens(userModel.getTokenIdList().stream()
            .map(token -> TokenEntityMapper.toInfra(userEntity, token)).toList());
        return userEntity;
    }

}
