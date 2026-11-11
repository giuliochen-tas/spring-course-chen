package com.example.spring_course.model;

import com.example.spring_course.service.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private List<OrderResponse> orders;

    public CustomerResponse(Long id, String name, List<OrderResponse> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }
}
