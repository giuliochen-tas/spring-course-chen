package com.example.spring_course.model;

import com.example.spring_course.repository.model.StatoOrdine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticaStatoResponse {

    @Schema(description = "Stato dell'ordine", example = "RICEVUTO")
    private StatoOrdine stato;

    @Schema(description = "Numero di ordini nello stato specificato", example = "4")
    private Long numeroOrdini;
}