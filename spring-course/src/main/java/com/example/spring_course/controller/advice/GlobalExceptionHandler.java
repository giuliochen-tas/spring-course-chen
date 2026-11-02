package com.example.spring_course.controller.advice;

import com.example.spring_course.controller.advice.model.ApiErrorResponse;
import com.example.spring_course.exception.AppException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppException(AppException appException, HttpServletRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(appException.getStatus());
        apiErrorResponse.setError(appException.getStatus().getReasonPhrase());
        apiErrorResponse.setMessage(appException.getMessage());
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(appException.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiErrorResponse.setMessage(message);
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(apiErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e, HttpServletRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiErrorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        apiErrorResponse.setMessage("Internal server error");
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }
}
