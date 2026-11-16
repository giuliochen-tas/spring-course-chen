package com.example.spring_course.controller;

import com.example.spring_course.model.OrdineResponse;
import com.example.spring_course.model.UserRequest;
import com.example.spring_course.model.UserResponse;
import com.example.spring_course.service.OrdineService;
import com.example.spring_course.service.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ordini")
@Tag(name = "Controller", description = "Controller for order management")
public class OrdineController {

    private final OrdineService ordineService;

    /*
    @PostMapping("/createOrder")
    @Operation(summary = "Create order", description = "Creates an order")
    @ApiResponse(
            responseCode = "200",
            description = "Order created successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            """
                                {
                                      "id": 1
                                      "name": "Giulio",
                                      "email": "giulio.chen@tasgroup.eu",
                                      "age": 29,
                                      "address": "MI - IT"
                                }
                            """
                    ),
                    schema = @Schema(implementation = UserResponse.class)
            )
    )
    public ResponseEntity<OrdineResponse> createOrder(@RequestBody @Valid OrdineRequest ordine){
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    */
}
