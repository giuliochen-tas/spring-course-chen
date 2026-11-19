package com.example.spring_course.service.mapper;

import com.example.spring_course.model.OrdineRequest;
import com.example.spring_course.model.OrdineResponse;
import com.example.spring_course.model.StatisticaStatoProjection;
import com.example.spring_course.model.StatisticaStatoResponse;
import com.example.spring_course.repository.model.OrdineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdineMapper {

    OrdineEntity toEntity(OrdineRequest request);

    OrdineResponse toResponse(OrdineEntity entity);

    StatisticaStatoResponse toStatisticaResponse(StatisticaStatoProjection projection);

    List<StatisticaStatoResponse> toStatisticaResponseList(List<StatisticaStatoProjection> projections);
}