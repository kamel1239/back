/*
  2024
*/
package org.project.infrastructure.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.project.domain.user.model.TokenInfoModel;
import org.project.infrastructure.persistence.model.TokenEntity;
import org.project.infrastructure.persistence.model.UserEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenEntityMapper {

    public static TokenInfoModel toDomain(TokenEntity tokenEntity) {
        return new TokenInfoModel(tokenEntity.getId(), tokenEntity.getTokenValue(),
            tokenEntity.getExpiryDate());
    }

    public static TokenEntity toInfra(UserEntity userEntity, TokenInfoModel tokenModel) {
        return new TokenEntity(tokenModel.id(), tokenModel.token(), tokenModel.expirationDate(),
            userEntity);
    }

}
