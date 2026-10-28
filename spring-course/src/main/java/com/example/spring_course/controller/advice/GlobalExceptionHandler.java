package com.example.spring_course.controller.advice;

import com.example.spring_course.controller.advice.model.ApiErrorResponse;
import com.example.spring_course.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ApiErrorResponse> handleAppException(AppException appException){

    }
}
