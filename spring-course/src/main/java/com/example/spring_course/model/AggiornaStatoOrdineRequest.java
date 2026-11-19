package com.example.spring_course.model;

import com.example.spring_course.repository.model.StatoOrdine;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggiornaStatoOrdineRequest {

    @NotNull(message = "Il nuovo stato è obbligatorio")
    StatoOrdine nuovoStato;
}
