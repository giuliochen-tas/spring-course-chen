package com.example.spring_course.service;

import com.example.spring_course.exception.OrdineNotFoundException;
import com.example.spring_course.exception.StatoTransizioneNonConsentitaException;
import com.example.spring_course.model.*;
import com.example.spring_course.repository.OrdineRepository;
import com.example.spring_course.repository.model.OrdineEntity;
import com.example.spring_course.repository.model.StatoOrdine;
import com.example.spring_course.service.mapper.OrdineMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdineService {

    private final OrdineRepository ordineRepository;
    private final OrdineMapper ordineMapper;

    @Transactional
    public OrdineResponse creaOrdine(OrdineRequest request) {
        OrdineEntity entity = ordineMapper.toEntity(request);
        Double totale = request.getPrezzoUnitario() * request.getQuantita();
        entity.setTotale(totale);
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        entity.setCodiceOrdine("ORD-" + uuid.substring(0, 26));
        entity.setDataOrdine(LocalDateTime.now());
        entity.setStato(StatoOrdine.RICEVUTO);

        OrdineEntity entitySalvata = ordineRepository.save(entity);
        return ordineMapper.toResponse(entitySalvata);
    }

    @Transactional
    public OrdineResponse getOrdineById(Long id) {
        OrdineEntity entity = ordineRepository.findById(id)
                .orElseThrow(() -> new OrdineNotFoundException("Ordine id " + id + " non trovato", HttpStatus.NOT_FOUND));
        return ordineMapper.toResponse(entity);
    }

    @Transactional
    public List<OrdineResponse> getAllOrdini(StatoOrdine stato) {
        List<OrdineEntity> ordini;

        if (stato != null) {
            ordini = ordineRepository.findByStato(stato);
        } else {
            ordini = ordineRepository.findAll();
        }

        return ordini.stream()
                .map(ordineMapper::toResponse)
                .toList();
    }

    @Transactional
    public OrdineResponse aggiornaStatoOrdine(Long id, AggiornaStatoOrdineRequest request) {
        OrdineEntity entity = ordineRepository.findById(id)
                .orElseThrow(() -> new OrdineNotFoundException("Ordine id " + id + " non trovato", HttpStatus.NOT_FOUND));
        StatoOrdine statoAttuale = entity.getStato();
        StatoOrdine nuovoStato = request.getNuovoStato();
        validaTransizioneStato(statoAttuale, nuovoStato);
        entity.setStato(nuovoStato);
        OrdineEntity savedEntity = ordineRepository.save(entity);
        return ordineMapper.toResponse(savedEntity);
    }

    private void validaTransizioneStato(StatoOrdine statoAttuale, StatoOrdine nuovoStato) {
        boolean valida = switch (statoAttuale) {
            case RICEVUTO -> nuovoStato == StatoOrdine.IN_LAVORAZIONE || nuovoStato == StatoOrdine.ANNULLATO;
            case IN_LAVORAZIONE -> nuovoStato == StatoOrdine.SPEDITO || nuovoStato == StatoOrdine.ANNULLATO;
            case SPEDITO, ANNULLATO -> false;
        };

        if (!valida) {
            String message = String.format("Transizione di stato non consentita da %s a %s", statoAttuale, nuovoStato);
            throw new StatoTransizioneNonConsentitaException(message, HttpStatus.CONFLICT);
        }
    }

    @Transactional
    public List<StatisticaStatoResponse> getStatisticheStato() {
        List<StatisticaStatoProjection> projections = ordineRepository.countOrdiniGroupedByStato();
        return ordineMapper.toStatisticaResponseList(projections);
    }
}
