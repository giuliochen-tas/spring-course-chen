package com.example.spring_course.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private BigDecimal total;

    public OrderResponse(Long id, BigDecimal total){
        this.id = id;
        this.total = total;
    }
}
