package com.example.spring_course.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final transient AppErrorCodeMessageEnum codeMessage;
    private final transient Object[] args;

    public AppException(AppErrorCodeMessageEnum codeMessage, Object[] args) {
        super();
        this.codeMessage = codeMessage;
        this.args = args;
    }


}
