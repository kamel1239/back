/*
  2024
*/
package org.project.infrastructure.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.project.domain.product.exception.ProductNotFoundException;
import org.project.domain.user.exception.UserNotFoundException;
import org.project.infrastructure.api.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
        HttpMessageNotReadableException exception) {
        // TODO hide internal code path when parsing error
        return ResponseEntity.badRequest().body(new ErrorResponse("400", exception.getMessage()));
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleProductNotFoundException(
        ProductNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUserNotFoundException(
        UserNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
        // TODO hide internal code path when parsing error
        DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse("409", exception.getMessage()));
    }

}
