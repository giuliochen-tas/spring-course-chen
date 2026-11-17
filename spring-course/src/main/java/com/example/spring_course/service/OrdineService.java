package com.example.spring_course.service;

import com.example.spring_course.model.OrdineRequest;
import com.example.spring_course.model.OrdineResponse;
import com.example.spring_course.repository.OrdineRepository;
import com.example.spring_course.repository.model.OrdineEntity;
import com.example.spring_course.repository.model.StatoOrdine;
import com.example.spring_course.service.mapper.OrdineMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

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
        entity.setCodiceOrdine("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setDataOrdine(LocalDateTime.now());
        entity.setStato(StatoOrdine.RICEVUTO);

        OrdineEntity entitySalvata = ordineRepository.save(entity);
        return ordineMapper.toResponse(entitySalvata);
    }
}
