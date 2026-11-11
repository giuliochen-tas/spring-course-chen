package com.example.spring_course.client;

import com.example.spring_course.exception.AppException;
import com.example.spring_course.model.ExternalUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@RequiredArgsConstructor
public class ExternalUserClient {

    private final RestClient restClient;

    public ExternalUserResponse findUser(Long id){
        try {
            return restClient.get()
                    .uri("/users/{id}", id)
                    .retrieve()
                    .body(ExternalUserResponse.class);
        } catch(HttpClientErrorException.NotFound e) {
            throw new AppException("External user " + id + " not found", HttpStatus.NOT_FOUND);
        } catch(RestClientException e) {
            throw new AppException("Error while calling external service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
