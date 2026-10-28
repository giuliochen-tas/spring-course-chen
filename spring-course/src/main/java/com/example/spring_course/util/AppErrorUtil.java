package com.example.spring_course.util;

import com.example.spring_course.controller.advice.model.ApiErrorResponse;
import com.example.spring_course.exception.AppErrorCodeMessageEnum;
import com.example.spring_course.exception.AppException;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;

import java.util.List;

public class AppErrorUtil {

    public Pair<HttpStatus, ApiErrorResponse> buildApiErrorResponse(AppException appException, List<ApiErrorResponse.ErrorMessage> errorMessageList){
        AppErrorCodeMessageEnum codeMessageEnum = appException.getCodeMessage();
        HttpStatus httpStatus = codeMessageEnum.getHttpStatus();
        return Pair.of();
    }
}
