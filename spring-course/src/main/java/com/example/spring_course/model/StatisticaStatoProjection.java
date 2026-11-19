package com.example.spring_course.model;

import com.example.spring_course.repository.model.StatoOrdine;

public interface StatisticaStatoProjection {
    StatoOrdine getStato();
    Long getNumeroOrdini();
}