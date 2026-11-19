package com.example.spring_course.controller;

import com.example.spring_course.model.*;
import com.example.spring_course.repository.model.StatoOrdine;
import com.example.spring_course.service.OrdineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ordini")
@Tag(name = "Gestione ordini", description = "API per la gestione degli ordini dei clienti")
public class OrdineController {

    private final OrdineService ordineService;

    @PostMapping
    @Operation(summary = "Creazione di un nuovo ordine", description = "Registra un nuovo ordine, calcola il totale e imposta lo stato iniziale a RICEVUTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordine creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati di input non validi")
    })
    public ResponseEntity<OrdineResponse> creaOrdine(@Valid @RequestBody OrdineRequest request) {
        OrdineResponse response = ordineService.creaOrdine(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Recupera dettaglio ordine", description = "Restituisce i dettagli dell'ordine corrispondente all'ID fornito.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine trovato"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrdineResponse> getOrdineById(
            @Parameter(description = "ID dell'ordine da recuperare", required = true)
            @PathVariable Long id) {
        OrdineResponse response = ordineService.getOrdineById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Elenca gli ordini",
            description = "Restituisce la lista di tutti gli ordini. È possibile filtrare per stato tramite query parameter facoltativo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordini recuperata con successo"),
            @ApiResponse(responseCode = "400", description = "Valore del filtro stato non valido")
    })
    @GetMapping
    public ResponseEntity<List<OrdineResponse>> getAllOrdini(
            @Parameter(description = "Filtro facoltativo per stato (RICEVUTO, IN_LAVORAZIONE, SPEDITO, ANNULLATO)", required = false)
            @RequestParam(required = false) StatoOrdine stato) {

        List<OrdineResponse> ordini = ordineService.getAllOrdini(stato);
        return ResponseEntity.ok(ordini);
    }

    @PatchMapping("/{id}/stato")
    @Operation(
            summary = "Aggiorna lo stato di un ordine",
            description = "Modifica lo stato dell'ordine se la transizione di stato richiesta è valida."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stato aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato"),
            @ApiResponse(responseCode = "409", description = "Transizione di stato non consentita")
    })
    public ResponseEntity<OrdineResponse> aggiornaStato(
            @Parameter(description = "ID dell'ordine da aggiornare", required = true)
            @PathVariable Long id,
            @Valid @RequestBody AggiornaStatoOrdineRequest request) {

        OrdineResponse response = ordineService.aggiornaStatoOrdine(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistiche/stato")
    @Operation(
            summary = "Mostra le statistiche degli ordini per stato",
            description = "Restituisce l'elenco dei conteggi degli ordini raggruppati per ogni stato disponibile."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistiche recuperate con successo")
    })
    public ResponseEntity<List<StatisticaStatoResponse>> getStatisticheStato() {
        List<StatisticaStatoResponse> statistiche = ordineService.getStatisticheStato();
        return ResponseEntity.ok(statistiche);
    }
}
