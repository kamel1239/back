/*
  2024
*/
package org.project.infrastructure.api.dto;

import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message, List<String> errors) {

    public ErrorResponse(HttpStatus status, String message) {
        this(status, message, Collections.emptyList());
    }

    public ErrorResponse(HttpStatus status, String message, String error) {
        this(status, message, Collections.singletonList(error));
    }

}
