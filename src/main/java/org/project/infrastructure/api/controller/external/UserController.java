/*
  2024
*/
package org.project.infrastructure.api.controller.external;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.project.application.handler.UserHandler;
import org.project.infrastructure.api.dto.user.ApiAuthResponseDTO;
import org.project.infrastructure.api.dto.user.ApiRegisterRequestDTO;
import org.project.infrastructure.api.mapper.ApiRegisterRequestDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiAuthResponseDTO> login(
        @Valid @RequestBody ApiRegisterRequestDTO user) {
        // TODO  Create ApiLoginRequestDTO
        log.info("Logging in user with username {}", user.username());
        String token = userHandler.authenticate(ApiRegisterRequestDTOMapper.toApp(user));
        return ResponseEntity.ok(new ApiAuthResponseDTO(token));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ApiAuthResponseDTO> register(
        @Valid @RequestBody ApiRegisterRequestDTO user) {
        log.info("Registering user with username {}", user.username());
        String token = userHandler.register(ApiRegisterRequestDTOMapper.toApp(user));
        return ResponseEntity.ok(new ApiAuthResponseDTO(token));
    }

}
