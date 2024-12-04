package com.devops.productservice.exception;

import com.devops.productservice.dto.APIResponse;
import com.devops.productservice.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        List<HashMap<String, String>> err = ex.getConstraintViolations()
                .stream().map(error -> {
                    HashMap<String, String> violation = new HashMap<>();
                    violation.put(error.getPropertyPath().toString(), error.getMessage());
                    return violation;
                }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new APIResponse<>(err));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        List<HashMap<String, String>> validationErrors = errors.stream().map(error -> {
            HashMap<String, String> violation = new HashMap<>();
            violation.put(
                    ((FieldError) error).getField(),
                    error.getDefaultMessage()
            );
            return violation;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(new APIResponse<>(validationErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex,
                                                               WebRequest webRequest) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(ex.getStatus())
                .errorMessage(ex.getMessage())
                .errorTime(Instant.now())
                .build(), HttpStatus.valueOf(ex.getStatus()));
    }

}
