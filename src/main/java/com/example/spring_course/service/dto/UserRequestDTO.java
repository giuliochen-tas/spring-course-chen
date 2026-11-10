package com.example.spring_course.service.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private int age;
    private String address;
}
