package com.example.spring_course.repository;

import com.example.spring_course.model.StatisticaStatoProjection;
import com.example.spring_course.model.StatisticaStatoResponse;
import com.example.spring_course.repository.model.OrdineEntity;
import com.example.spring_course.repository.model.StatoOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdineRepository extends JpaRepository<OrdineEntity, Long> {

    List<OrdineEntity> findByStato(StatoOrdine stato);

    @Query("SELECT o.stato AS stato, COUNT(o) AS numeroOrdini FROM OrdineEntity o GROUP BY o.stato")
    List<StatisticaStatoProjection> countOrdiniGroupedByStato();
}
