/*
  2024
*/
package org.project.infrastructure.api.controller.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping(path = "/api/products")
public class Permissions {

    @GetMapping
    public void getPermissions() {
        log.info("Getting permissions");
    }

    @PostMapping
    public void postPermissions() {
        log.info("Posting permissions");
    }

    @DeleteMapping(path = "/{id}")
    public void deletePermissions(String id) {
        log.info("Deleting permissions {}", id);
    }

    @PatchMapping(path = "/{id}")
    public void patchPermissions(String id) {
        log.info("Patching permissions {}", id);
    }

}
