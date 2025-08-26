package com.johanwork.product_service.exception;

import com.johanwork.product_service.constants.SystemConstant;
import com.johanwork.product_service.dto.ApiResponse;
import com.johanwork.product_service.dto.enums.CommonStatus;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<ProblemDetail>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> violations = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String field = ((FieldError)violation).getField();
            String message = violation.getMessage();
            violations.put(field, message);
        });
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        CommonStatus.FAILED,
                        createProblemDetail(
                                HttpStatus.BAD_REQUEST,
                                SystemConstant.Validation.TITLE_400,
                                SystemConstant.Validation.MESSAGE_400,
                                violations
                        )
                )
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> violations = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(constraintViolation -> {
                    String field = ((FieldError) constraintViolation).getField();
                    String message = constraintViolation.getDefaultMessage();
                    violations.put(field, message);
                });
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        CommonStatus.FAILED,
                        createProblemDetail(
                                HttpStatus.BAD_REQUEST,
                                SystemConstant.Validation.TITLE_400,
                                SystemConstant.Validation.MESSAGE_400,
                                violations
                        )
                )
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<ProblemDetail>> handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(
                new ApiResponse<>(
                        CommonStatus.FAILED,
                        createProblemDetail(
                                ex.getStatus(),
                                ex.getTitle(),
                                ex.getMessage(),
                                null
                        )
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ProblemDetail>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>(
                        CommonStatus.FAILED,
                        createProblemDetail(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                SystemConstant.Server.TITLE_500,
                                SystemConstant.Server.MESSAGE_500,
                                null
                        )
                )
        );
    }

    private ProblemDetail createProblemDetail(HttpStatus status,
                                              String title,
                                              String detail,
                                              Map<String, String> violations) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        if (violations != null) {
            problemDetail.setProperty("errors", violations);
        }
        return problemDetail;
    }

}
