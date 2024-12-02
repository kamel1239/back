/*
  2024
*/
package org.project.application.handler;


import org.project.application.dto.UserDTO;

public interface UserHandler {

    String authenticate(UserDTO userDTO);

    String register(UserDTO userDTO);

}
