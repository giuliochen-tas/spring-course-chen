package com.example.spring_course.service.mapper;

import com.example.spring_course.controller.model.UserResponse;
import com.example.spring_course.service.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserResponseMapper {

    public abstract UserResponseDTO toResponseDTO(UserResponse userResponse);
}
