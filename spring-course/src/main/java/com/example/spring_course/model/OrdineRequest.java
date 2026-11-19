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

    @Schema(description = "Nome del cliente", example = "Mario")
    @NotBlank(message = "Il nome del cliente è obbligatorio")
    @Size(min = 2, max = 50, message = "Il nome del cliente deve essere compreso tra 2 e 50 caratteri")
    private String nomeCliente;

    @Schema(description = "Cognome del cliente", example = "Rossi")
    @NotBlank(message = "Il cognome del cliente è obbligatorio")
    @Size(min = 2, max = 50, message = "Il cognome del cliente deve essere compreso tra 2 e 50 caratteri")
    private String cognomeCliente;

    @Schema(description = "Email del cliente", example = "mario.rossi@example.com")
    @NotBlank(message = "L'email del cliente è obbligatoria")
    @Email(message = "Il formato dell'email non è valido")
    @Size(max = 100, message = "L'email del cliente non può superare i 100 caratteri")
    private String emailCliente;

    @Schema(description = "Telefono del cliente", example = "+393401234567")
    @Size(max = 20, message = "Il telefono non può superare i 20 caratteri")
    private String telefonoCliente;

    @Schema(description = "Indirizzo di spedizione del cliente", example = "Via Roma 10")
    @NotBlank(message = "L'indirizzo di spedizione è obbligatorio")
    @Size(min = 5, max = 120, message = "L'indirizzo di spedizione deve essere compreso tra 5 e 120 caratteri")
    private String indirizzoSpedizione;

    @Schema(description = "Città di spedizione del cliente", example = "Milano")
    @NotBlank(message = "La città di spedizione è obbligatoria")
    @Size(min = 2, max = 50, message = "La città di spedizione deve essere compresa tra 2 e 50 caratteri")
    private String cittaSpedizione;

    @Schema(description = "CAP del cliente", example = "20121")
    @NotBlank(message = "Il CAP è obbligatorio")
    @Pattern(regexp = "^\\d{5}$", message = "Il CAP deve contenere esattamente 5 cifre")
    private String cap;

    @Schema(description = "Nome del prodotto", example = "Lenovo ThinkPad")
    @NotBlank(message = "Il prodotto è obbligatorio")
    @Size(min = 2, max = 80, message = "Il nome del prodotto deve essere compreso tra 2 e 80 caratteri")
    private String prodotto;

    @Schema(description = "Quantità del prodotto", example = "5")
    @NotNull(message = "La quantità è obbligatoria")
    @Min(value = 1, message = "La quantità minima deve essere 1")
    @Max(value = 999, message = "La quantità massima non può superare 999")
    private Integer quantita;

    @Schema(description = "Prezzo unitario del prodotto", example = "49.99")
    @NotNull(message = "Il prezzo unitario è obbligatorio")
    @Positive(message = "Il prezzo unitario deve essere maggiore di zero")
    private Double prezzoUnitario;
}
