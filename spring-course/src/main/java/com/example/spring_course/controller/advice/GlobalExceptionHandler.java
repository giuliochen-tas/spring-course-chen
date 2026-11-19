package com.example.spring_course.controller.advice;

import com.example.spring_course.controller.advice.model.ApiErrorResponse;
import com.example.spring_course.exception.OrdineNotFoundException;
import com.example.spring_course.exception.StatoTransizioneNonConsentitaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

    @ExceptionHandler(OrdineNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleOrdineNotFoundException(OrdineNotFoundException ordineNotFoundException, HttpServletRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(ordineNotFoundException.getStatus());
        apiErrorResponse.setError(ordineNotFoundException.getStatus().getReasonPhrase());
        apiErrorResponse.setMessage(ordineNotFoundException.getMessage());
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(ordineNotFoundException.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiErrorResponse.setMessage("Filtro non valido");
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(StatoTransizioneNonConsentitaException.class)
    public ResponseEntity<ApiErrorResponse> handleStatoTransizioneNonConsentitaException(StatoTransizioneNonConsentitaException e, HttpServletRequest request){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(e.getStatus());
        apiErrorResponse.setError(e.getStatus().getReasonPhrase());
        apiErrorResponse.setMessage(e.getMessage());
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(e.getStatus()).body(apiErrorResponse);
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
