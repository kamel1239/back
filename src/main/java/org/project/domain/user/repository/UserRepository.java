/*
    2024
*/
package org.project.domain.user.repository;

import org.project.domain.user.exception.UserNotFoundException;
import org.project.domain.user.model.TokenInfoModel;
import org.project.domain.user.model.UserModel;

public interface UserRepository {

    UserModel save(UserModel userModel);

    UserModel findUser(String username) throws UserNotFoundException;

    UserModel findUserByToken(TokenInfoModel tokenInfoModel);
    
}
