package io.github.bokalebsson.javastudyassistant.dto.error;

/**
 * Minimal error response DTO used for API error handling.
 */
public class ErrorResponseDto {

    private final String message;

    public ErrorResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
