/*
  2024
*/
package org.project.application.handler;


import org.project.application.dto.UserDTO;
import org.project.domain.user.exception.UserNotFoundException;

public interface UserHandler {

    String authenticate(UserDTO userDTO) throws UserNotFoundException;

    String register(UserDTO userDTO);

    void logoutFromAllSessions(String username);

    void logoutFromSession(String username, String tokenId);

}
