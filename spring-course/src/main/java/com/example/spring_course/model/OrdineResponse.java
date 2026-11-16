package com.example.spring_course.model;

import com.example.spring_course.repository.model.StatoOrdine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdineResponse {

    private Long id;
    private String codiceOrdine;
    private Double totale;
    private StatoOrdine stato;
    private LocalDateTime dataOrdine;
}
