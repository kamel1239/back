/*
  2024
*/
package org.project.infrastructure.api.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.product.exception.ProductNotFoundException;
import org.project.domain.user.exception.UserAlreadyExistsException;
import org.project.domain.user.exception.UserNotFoundException;
import org.project.infrastructure.api.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    private static final String INVALID_ENUM_ERROR_MESSAGE = "the values accepted are:";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
        @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        // Enhance verbosity for internal exceptions
        log.info("An exception of type {} occurred due to: {}", ex.getClass().getName(),
            ex.getMessage());

        if (body instanceof ErrorResponse) {
            return new ResponseEntity<>(body, headers, status);
        } else {
            log.warn("This exception is not handled - a generic error message was displayed");
            var errorResponse = new ErrorResponse(HttpStatus.resolve(status.value()),
                ex.getMessage());
            return new ResponseEntity<>(errorResponse, headers, status);
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        var errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid input", errors);
        return handleExceptionInternal(ex, errorResponse, headers, errorResponse.status(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ErrorResponse errorResponse;
        var cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormatException) {
            var path = invalidFormatException.getPath();
            var fieldName = path.getFirst().getFieldName();
            var validValues = Arrays.toString(
                invalidFormatException.getTargetType().getEnumConstants());
            var message = String.format("%s: %s %s", fieldName, INVALID_ENUM_ERROR_MESSAGE,
                validValues);
            errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid input", message);
        } else {
            // Handle internal error messages
            errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid input",
                cause.getMessage());
        }

        return handleExceptionInternal(ex, errorResponse, headers, errorResponse.status(), request);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        @NonNull MethodArgumentTypeMismatchException ex) {
        String error;
        if (ex.getRequiredType() != null) {
            error = String.format("%s: should be of type %s", ex.getName(),
                ex.getRequiredType().getName());
        } else {
            error = String.format("%s: have wrong type", ex.getName());
        }
        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid type", error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleProductNotFoundException(
        ProductNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUserNotFoundException(
        UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ErrorResponse(HttpStatus.FORBIDDEN, "Bad credentials"));
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
        UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
    }

}
