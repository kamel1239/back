/*
  2024
*/
package org.project.application.handler.impl;

import org.project.application.dto.UserDTO;
import org.project.application.handler.UserHandler;
import org.project.domain.user.exception.UserNotFoundException;
import org.project.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserHandlerImpl implements UserHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String authenticate(UserDTO userDTO) throws UserNotFoundException {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password()));
        return userService.updateUserToken(userDTO.username()).token();
    }

    @Override
    public String register(UserDTO userDTO) {
        var userToken = userService.createUserToken(userDTO.username(), userDTO.password(),
            userDTO.role());
        return userToken.token();
    }

    @Override
    public void logoutFromAllSessions(String username) {
        userService.deleteUserTokens(username);
    }

    @Override
    public void logoutFromSession(String username, String tokenId) {
        userService.deleteUserToken(username, tokenId);
    }

}
