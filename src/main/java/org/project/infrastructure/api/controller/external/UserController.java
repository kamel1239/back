/*
  2024
*/
package org.project.infrastructure.api.controller.external;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.project.infrastructure.api.entity.ApiUserEntity;
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


    @PostMapping(path = "/login")
    public ResponseEntity<Void> login(@Valid @RequestBody ApiUserEntity user) {
        log.info("Logging in user with username {}", user.username());
        return null;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> register(@Valid @RequestBody ApiUserEntity user) {
        log.info("Registering user with username {}", user.username());
        return null;
    }

}
