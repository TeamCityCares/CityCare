//package com.cts.CityCare.CityCare.exception;
//
//
//
//import com.cts.CityCare.CityCare.dto.response.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    // 1. Resource Not Found (404)
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(ApiResponse.error(ex.getMessage()));
//    }
//
//    // 2. Bad Request (400)
//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(ApiResponse.error(ex.getMessage()));
//    }
//
//    // 3. Unauthorized Access (403) - e.g., Admin from Hospital A touching Hospital B data
//    @ExceptionHandler(UnauthorizedAccessException.class)
//    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(UnauthorizedAccessException ex) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(ApiResponse.error(ex.getMessage()));
//    }
//
//    // 4. Capacity Exceeded (409 Conflict or 400) - e.g., Ward is full
//    @ExceptionHandler(CapacityExceededException.class)
//    public ResponseEntity<ApiResponse<Object>> handleCapacity(CapacityExceededException ex) {
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body(ApiResponse.error(ex.getMessage()));
//    }
//
//    // 5. Invalid Data (422 Unprocessable Entity) - e.g., Validation logic fails
//    @ExceptionHandler(InvalidDataException.class)
//    public ResponseEntity<ApiResponse<Object>> handleInvalidData(InvalidDataException ex) {
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
//                .body(ApiResponse.error(ex.getMessage()));
//    }
//
//    // 6. Generic Catch-All (500) - For any unexpected crashes
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> handleGlobal(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ApiResponse.error("An unexpected error occurred: " + ex.getMessage()));
//    }
//}



package com.cts.CityCare.CityCare.exception;

import com.cts.CityCare.CityCare.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1. HANDLES: Bean Validation Errors (@Valid)
     * This catches @NotBlank, @Size, @Min, etc., from your DTOs.
     * Postman Result: 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Wraps the error map inside your consistent ApiResponse structure
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .success(false)
                .message("Validation Failed")
                .data(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(CapacityExceededException.class)
    public ResponseEntity<ApiResponse<Object>> handleCapacity(CapacityExceededException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidData(InvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobal(Exception ex) {
        // In production, you would log the full 'ex' here for debugging.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("An unexpected system error occurred: " + ex.getMessage()));
    }
}