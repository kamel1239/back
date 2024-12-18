/*
  2024
*/
package org.project.infrastructure.persistence.impl;

import org.project.domain.user.exception.UserAlreadyExistsException;
import org.project.domain.user.exception.UserNotFoundException;
import org.project.domain.user.model.TokenInfoModel;
import org.project.domain.user.model.UserModel;
import org.project.domain.user.repository.UserRepository;
import org.project.infrastructure.persistence.mapper.UserEntityMapper;
import org.project.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public UserModel save(UserModel userModel) {
        try {
            return UserEntityMapper.toDomain(
                jpaUserRepository.save(UserEntityMapper.toInfra(userModel)));
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(
                String.format("User with name %s already exists", userModel.getUsername()));
        }
    }

    @Override
    public UserModel findUser(String username) throws UserNotFoundException {
        return jpaUserRepository.findByUsername(username).map(UserEntityMapper::toDomain)
            .orElseThrow(() -> new UserNotFoundException(
                String.format("User with username %s not found", username)));
    }

    @Override
    public UserModel findUserByToken(TokenInfoModel tokenInfoModel) {
        return jpaUserRepository.findByTokens_Id(tokenInfoModel.id())
            .map(UserEntityMapper::toDomain).orElseThrow(() -> new UserNotFoundException(
                String.format("User with tokenId %s not found", tokenInfoModel.id())));
    }


}
