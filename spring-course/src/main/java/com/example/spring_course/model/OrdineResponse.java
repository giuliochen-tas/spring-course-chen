package com.example.spring_course.model;

import com.example.spring_course.repository.model.StatoOrdine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdineResponse {

    @Schema(description = "ID dell'ordine, generato dal sistema", example = "2")
    private Long id;
    @Schema(description = "Codice univoco dell'ordine, generato dal sistema", example = "ORD-0F1827886A6345E09CBCB6CA30")
    private String codiceOrdine;
    @Schema(description = "Totale dell'ordine", example = "99.98")
    private Double totale;
    @Schema(description = "Stato dell'ordine", example = "RICEVUTO")
    private StatoOrdine stato;
    @Schema(description = "Data dell'ordine", example = "2026-11-18T17:46:02.109131")
    private LocalDateTime dataOrdine;
}
