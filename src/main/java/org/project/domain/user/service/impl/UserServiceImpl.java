/*
  2024
*/
package org.project.domain.user.service.impl;

import jakarta.transaction.Transactional;
import org.project.domain.user.model.TokenInfoModel;
import org.project.domain.user.model.UserModel;
import org.project.domain.user.repository.UserRepository;
import org.project.domain.user.service.AuthService;
import org.project.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenInfoModel createUserToken(String username, String password, String role) {
        var user = UserModel.builder().username(username).password(passwordEncoder.encode(password))
            .role(role).build();
        var tokenInfoModel = authService.generateToken(user);
        user.addToken(tokenInfoModel);
        userRepository.save(user);
        return tokenInfoModel;
    }

    @Transactional
    @Override
    public TokenInfoModel updateUserToken(String username) {
        var userModel = userRepository.findUser(username);
        var tokenInfoModel = authService.generateToken(userModel);
        userModel.removeExpiredTokens();
        userModel.addToken(tokenInfoModel);
        userRepository.save(userModel);
        return tokenInfoModel;
    }

    @Transactional
    @Override
    public void deleteUserTokens(String username) {
        var userModel = userRepository.findUser(username);
        userModel.removeTokens();
        userRepository.save(userModel);
    }

    @Transactional
    @Override
    public void deleteUserToken(String username, String tokenId) {
        var userModel = userRepository.findUser(username);
        userModel.removeTokenById(tokenId);
        userRepository.save(userModel);
    }

}
