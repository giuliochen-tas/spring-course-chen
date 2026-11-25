package com.example.spring_course.model;

import com.example.spring_course.repository.model.CustomerEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private Long id;
    private BigDecimal total;
    private CustomerEntity customer;
}
