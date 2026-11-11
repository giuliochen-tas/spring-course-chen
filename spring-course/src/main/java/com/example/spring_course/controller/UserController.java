package com.example.spring_course.controller;

import com.example.spring_course.model.UserRequest;
import com.example.spring_course.model.UserResponse;
import com.example.spring_course.repository.model.UserEntity;
import com.example.spring_course.service.UserService;
import com.example.spring_course.service.dto.UserRequestDTO;
import com.example.spring_course.service.mapper.UserRequestMapper;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Controller", description = "Controller for user management")
public class UserController {

    private final UserService userService;
    private final UserRequestMapper userRequestMapper;

    @PostMapping("/createUser")
    @Operation(summary = "Create user", description = "Creates an user if it doesnt exist")
    @ApiResponse(
            responseCode = "200",
            description = "User created successfully",
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
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest user){
        UserRequestDTO requestDTO = userRequestMapper.toRequestDTO(user);
        UserResponse response = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAllUsers")
    @Operation(summary = "Get all users", description = "Gets all the users")
    @ApiResponse(
            responseCode = "200",
            description = "Users retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/getUser/{name}")
    public ResponseEntity<List<UserEntity>> getUsersByName(@PathVariable String name){
        return ResponseEntity.ok(userService.searchByName(name));
    }

    @GetMapping("/getUser/{email}")
    public ResponseEntity<List<UserEntity>> getUsersByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.searchByEmail(email));
    }
}
