/*
  2024
*/
package org.project.application.handler.impl;

import org.project.application.dto.UserDTO;
import org.project.application.handler.UserHandler;
import org.project.application.mapper.UserDTOMapper;
import org.project.domain.user.exception.UserNotFoundException;
import org.project.domain.user.repository.UserRepository;
import org.project.domain.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserHandlerImpl implements UserHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String authenticate(UserDTO userDTO) throws UserNotFoundException {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password()));

        var user = userRepository.findUser(userDTO.username());
        return authService.generateToken(user);
    }

    @Override
    public String register(UserDTO userDTO) {
        var user = UserDTOMapper.toDomain(userDTO);
        // Encode the password before saving it
        // TODO  handle the password encoding
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return authService.generateToken(user);
    }

}
