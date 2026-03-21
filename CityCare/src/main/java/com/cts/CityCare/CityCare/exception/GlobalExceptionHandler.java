package com.cts.CityCare.CityCare.exception;



import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Resource Not Found (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // 2. Bad Request (400)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // 3. Unauthorized Access (403) - e.g., Admin from Hospital A touching Hospital B data
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // 4. Capacity Exceeded (409 Conflict or 400) - e.g., Ward is full
    @ExceptionHandler(CapacityExceededException.class)
    public ResponseEntity<ApiResponse<Object>> handleCapacity(CapacityExceededException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // 5. Invalid Data (422 Unprocessable Entity) - e.g., Validation logic fails
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidData(InvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // 6. Validation Errors (400) - e.g. Invalid Email format, Name too short
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        java.util.Map<String, String> errors = new java.util.HashMap<>();
        for (org.springframework.validation.FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.validationError("Validation failed", errors));
    }

    // 7. Database/JPA Validation Errors (400) - e.g. Phone number format fails during database save
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationExceptions(jakarta.validation.ConstraintViolationException ex) {
        java.util.Map<String, String> errors = new java.util.HashMap<>();
        for (jakarta.validation.ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String path = violation.getPropertyPath().toString();
            String fieldName = path.substring(path.lastIndexOf('.') + 1); // Extract exact field name
            errors.put(fieldName, violation.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.validationError("Database validation failed", errors));
    }

    // 8. Generic Catch-All (500) - For any unexpected crashes
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobal(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("An unexpected error occurred: " + ex.getMessage()));
    }
}