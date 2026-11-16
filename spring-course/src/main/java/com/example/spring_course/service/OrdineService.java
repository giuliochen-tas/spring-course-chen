package com.example.spring_course.service;

import com.example.spring_course.repository.OrdineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdineService {

    private final OrdineRepository ordineRepository;
}
