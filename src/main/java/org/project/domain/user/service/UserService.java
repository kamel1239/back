/*
  2024
*/
package org.project.domain.user.service;

import org.project.domain.user.model.TokenInfoModel;

public interface UserService {

    TokenInfoModel createUserToken(String username, String password, String role);

    TokenInfoModel updateUserToken(String username);

    void deleteUserTokens(String username);

    void deleteUserToken(String username, String tokenId);

}
