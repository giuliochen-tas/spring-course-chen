package com.example.spring_course.repository;

import com.example.spring_course.repository.model.OrdineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<OrdineEntity, Long> {
}
