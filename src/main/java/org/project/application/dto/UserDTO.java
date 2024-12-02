/*
  2024
*/
package org.project.application.dto;

public record UserDTO(String username, String password, String role) {

    public UserDTO(String username, String password) {
        this(username, password, null);
    }

}
