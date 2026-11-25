package com.example.spring_course.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerRequest {

    private Long id;
    private String name;
    private List<Order> orders;
}
