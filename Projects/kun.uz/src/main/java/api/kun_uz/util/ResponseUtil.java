package api.kun_uz.util;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    // Method to create a success response with data
    public static <T> ResponseEntity<T> success(T data) {
        return ResponseEntity.ok(data);
    }

    // Method to create a success response with custom status
    public static <T> ResponseEntity<T> successWithStatus(T data, int statusCode) {
        return ResponseEntity.status(statusCode).body(data);
    }

    // Method to create an empty success response (204 No Content)
    public static ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }

    // Method to create an error response with custom error message
    public static ResponseEntity<Object> error(String message, int statusCode) {
        return ResponseEntity.status(statusCode).body(new ErrorResponse(message));
    }

    // Error response inner class
    public static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
