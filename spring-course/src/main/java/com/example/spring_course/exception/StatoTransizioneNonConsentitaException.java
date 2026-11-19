package com.example.spring_course.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StatoTransizioneNonConsentitaException extends RuntimeException {
    private HttpStatus status;

    public StatoTransizioneNonConsentitaException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
