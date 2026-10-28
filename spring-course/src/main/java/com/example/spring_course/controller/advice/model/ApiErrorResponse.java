package com.example.spring_course.controller.advice.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private int httpStatusCode;
    private String appErrorCode;
    private String appErrorMessage;
    private List<ErrorMessage> errors;

    @Data
    public static class ErrorMessage {
        private String path;
        private String message;
    }
}
