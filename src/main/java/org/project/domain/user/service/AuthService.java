/*
  2024
*/
package org.project.domain.user.service;

import org.project.domain.user.model.TokenInfoModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    TokenInfoModel extractTokenInfo(String token);

    TokenInfoModel generateToken(UserDetails userDetails);

}
