package com.example.spring_course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdineRequest {

    @NotBlank(message = "Il nome del cliente è obbligatorio")
    @Size(min = 2, max = 50, message = "Il nome del cliente deve essere compreso tra 2 e 50 caratteri")
    private String nomeCliente;

    @NotBlank(message = "Il cognome del cliente è obbligatorio")
    @Size(min = 2, max = 50, message = "Il cognome del cliente deve essere compreso tra 2 e 50 caratteri")
    private String cognomeCliente;

    @NotBlank(message = "L'email del cliente è obbligatoria")
    @Email(message = "Il formato dell'email non è valido")
    @Size(max = 100, message = "L'email del cliente non può superare i 100 caratteri")
    private String emailCliente;

    @Size(max = 20, message = "Il telefono non può superare i 20 caratteri")
    private String telefonoCliente; // Facoltativo

    @NotBlank(message = "L'indirizzo di spedizione è obbligatorio")
    @Size(min = 5, max = 120, message = "L'indirizzo di spedizione deve essere compreso tra 5 e 120 caratteri")
    private String indirizzoSpedizione;

    @NotBlank(message = "La città di spedizione è obbligatoria")
    @Size(min = 2, max = 50, message = "La città di spedizione deve essere compresa tra 2 e 50 caratteri")
    private String cittaSpedizione;

    @NotBlank(message = "Il CAP è obbligatorio")
    @Pattern(regexp = "^\\d{5}$", message = "Il CAP deve contenere esattamente 5 cifre")
    private String cap;

    @NotBlank(message = "Il prodotto è obbligatorio")
    @Size(min = 2, max = 80, message = "Il nome del prodotto deve essere compreso tra 2 e 80 caratteri")
    private String prodotto;

    @NotNull(message = "La quantità è obbligatoria")
    @Min(value = 1, message = "La quantità minima deve essere 1")
    @Max(value = 999, message = "La quantità massima non può superare 999")
    private Integer quantita;

    @NotNull(message = "Il prezzo unitario è obbligatorio")
    @Positive(message = "Il prezzo unitario deve essere maggiore di zero")
    private Double prezzoUnitario;
}
