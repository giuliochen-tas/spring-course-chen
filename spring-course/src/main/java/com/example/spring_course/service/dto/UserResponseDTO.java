package com.example.spring_course.service.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
    private String address;
}
