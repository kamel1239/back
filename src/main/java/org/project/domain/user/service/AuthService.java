/*
  2024
*/
package org.project.domain.user.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}
