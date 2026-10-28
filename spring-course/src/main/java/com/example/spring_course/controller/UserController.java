package com.example.spring_course.controller;

import com.example.spring_course.controller.model.UserRequest;
import com.example.spring_course.controller.model.UserResponse;
import com.example.spring_course.repository.model.UserEntity;
import com.example.spring_course.service.UserService;
import com.example.spring_course.service.dto.UserRequestDTO;
import com.example.spring_course.service.dto.UserResponseDTO;
import com.example.spring_course.service.mapper.UserRequestMapper;
import com.example.spring_course.service.mapper.UserResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequest user){
        UserRequestDTO requestDTO = userRequestMapper.toRequestDTO(user);
        UserResponse response = userService.createUser(requestDTO);
        UserResponseDTO responseDTO = userResponseMapper.toResponseDTO(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
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
