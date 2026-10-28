package com.example.spring_course.service.mapper;

import com.example.spring_course.controller.model.UserRequest;
import com.example.spring_course.service.dto.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserRequestMapper {
    @Mapping(
        target = "address",
        expression = "java(mapAddress(userRequest.getCity(), userRequest.getNation()))"
    )
    public abstract UserRequestDTO toRequestDTO(UserRequest userRequest);

    public String mapAddress(String city, String nation){
        return city + " - " + nation;
    }
}
