package com.example.spring_course.service;

import com.example.spring_course.exception.AppException;
import com.example.spring_course.model.CustomerResponse;
import com.example.spring_course.repository.CustomerRepository;
import com.example.spring_course.repository.model.CustomerEntity;
import com.example.spring_course.service.dto.CustomerDTO;
import com.example.spring_course.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id){
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new AppException("Customer with id " + id + " not found", HttpStatus.NOT_FOUND));
        CustomerDTO customerDTO = customerMapper.toDTO(customerEntity);
        return customerMapper.toResponse(customerDTO);
    }
}
