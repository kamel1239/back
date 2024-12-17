/*
  2024
*/
package org.project.infrastructure.api.controller.external;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.project.application.handler.UserHandler;
import org.project.domain.user.model.UserModel;
import org.project.infrastructure.api.dto.user.ApiAuthRequestDTO;
import org.project.infrastructure.api.dto.user.ApiAuthResponseDTO;
import org.project.infrastructure.api.dto.user.ApiRegisterRequestDTO;
import org.project.infrastructure.api.mapper.ApiAuthRequestDTOMapper;
import org.project.infrastructure.api.mapper.ApiRegisterRequestDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping(path = "/api/auth")
public class UserController {

    @Autowired
    private UserHandler userHandler;

    @PostMapping(path = "/login")
    public ResponseEntity<ApiAuthResponseDTO> login(@Valid @RequestBody ApiAuthRequestDTO user) {
        log.info("Logging in user with username {}", user.username());
        var token = userHandler.authenticate(ApiAuthRequestDTOMapper.toApp(user));
        return ResponseEntity.ok(new ApiAuthResponseDTO(token));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ApiAuthResponseDTO> register(
        @Valid @RequestBody ApiRegisterRequestDTO user) {
        log.info("Registering user with username {}", user.username());
        var token = userHandler.register(ApiRegisterRequestDTOMapper.toApp(user));
        return ResponseEntity.ok(new ApiAuthResponseDTO(token));
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<Void> logoutFromAllSessions(@AuthenticationPrincipal UserModel user) {
        log.info("Logging out user with username {}", user.getUsername());
        userHandler.logoutFromAllSessions(user.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/logout/currentSession")
    public ResponseEntity<Void> logoutFromSession(@AuthenticationPrincipal UserModel user) {
        log.info("Logging out user with username {} and tokenId {}", user.getUsername(),
            user.getCurrentSessionId());
        userHandler.logoutFromSession(user.getUsername(), user.getCurrentSessionId());
        return ResponseEntity.noContent().build();
    }

}
