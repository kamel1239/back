/*
  2024
*/
package org.project.infrastructure.persistence.impl;

import org.project.domain.user.exception.UserNotFoundException;
import org.project.domain.user.model.UserModel;
import org.project.domain.user.repository.UserRepository;
import org.project.infrastructure.persistence.mapper.UserEntityModelMapper;
import org.project.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public UserModel save(UserModel userModel) {
        return UserEntityModelMapper.toDomain(
            jpaUserRepository.save(UserEntityModelMapper.toInfra(userModel)));
    }

    @Override
    public UserModel findUser(String username) throws UserNotFoundException {
        return jpaUserRepository.findByUsername(username).map(UserEntityModelMapper::toDomain)
            .orElseThrow(() -> new UserNotFoundException(
                String.format("User with username %s not found", username)));
    }

}
