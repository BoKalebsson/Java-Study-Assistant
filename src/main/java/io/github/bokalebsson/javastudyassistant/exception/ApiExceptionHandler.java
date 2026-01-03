package io.github.bokalebsson.javastudyassistant.exception;

import io.github.bokalebsson.javastudyassistant.dto.error.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the API.
 * Converts exceptions into predictable JSON error responses.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    // Handles expected user input errors. (validation, illegal arguments, etc.)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        ErrorResponseDto errorResponse =
                new ErrorResponseDto("The request could not be processed. Please check your input.");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    // Handles unexpected errors.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex) {

        ErrorResponseDto errorResponse =
                new ErrorResponseDto("Something went wrong on our side. Please try again later.");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
