/*
    2024
*/
package org.project.domain.user.repository;

import org.project.domain.user.Exception.UserNotFoundException;
import org.project.domain.user.model.UserModel;

public interface UserRepository {

    UserModel save(UserModel userModel);

    UserModel findUser(String username) throws UserNotFoundException;


}
