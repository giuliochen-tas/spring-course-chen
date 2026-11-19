package com.example.spring_course.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ordine")
public class OrdineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 20, max = 30)
    @Column(name = "codice_ordine", nullable = false, unique = true, length = 30)
    private String codiceOrdine;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "nome_cliente", nullable = false, length = 50)
    private String nomeCliente;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "cognome_cliente", nullable = false, length = 50)
    private String cognomeCliente;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email_cliente", nullable = false, length = 100)
    private String emailCliente;

    @Size(max = 20)
    @Column(name = "telefono_cliente", nullable = true, length = 20)
    private String telefonoCliente;

    @NotBlank
    @Size(min = 5, max = 120)
    @Column(name = "indirizzo_spedizione", nullable = false, length = 120)
    private String indirizzoSpedizione;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "citta_spedizione", nullable = false, length = 50)
    private String cittaSpedizione;

    @NotBlank
    @Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da esattamente 5 cifre")
    @Column(name = "cap", nullable = false, length = 5)
    private String cap;

    @NotBlank
    @Size(min = 2, max = 80)
    @Column(name = "prodotto", nullable = false, length = 80)
    private String prodotto;

    @NotNull
    @Min(1)
    @Max(999)
    @Column(name = "quantita", nullable = false)
    private Integer quantita;

    @NotNull
    @Positive
    @Column(name = "prezzo_unitario", nullable = false)
    private Double prezzoUnitario;

    @NotNull
    @Column(name = "totale", nullable = false)
    private Double totale;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false, length = 20)
    private StatoOrdine stato;

    @NotNull
    @Column(name = "data_ordine", nullable = false, updatable = false)
    private LocalDateTime dataOrdine;
}
