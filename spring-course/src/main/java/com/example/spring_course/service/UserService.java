package com.example.spring_course.service;

import com.example.spring_course.controller.model.UserResponse;
import com.example.spring_course.repository.model.UserEntity;
import com.example.spring_course.repository.UserRepository;
import com.example.spring_course.service.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserRequestDTO requestDTO){
        String email = requestDTO.getEmail();
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if(userEntity.isEmpty()){
            UserEntity user = new UserEntity();
            user.setName(requestDTO.getName());
            user.setEmail(email);
            user.setAge(requestDTO.getAge());
            user.setAddress(requestDTO.getAddress());
            userRepository.save(user);

            return new UserResponse("" + user.getId(), user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress());
        }
        return new UserResponse("" + userEntity.get().getId(), userEntity.get().getName(),
                userEntity.get().getEmail(), userEntity.get().getAge(), userEntity.get().getAddress());
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public List<UserEntity> searchByName(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        List<UserEntity> user = userRepository.searchByName(name);
        if (user == null || user.isEmpty()){
            throw new NoSuchElementException("User not found");
        }
        return user;
    }

    public List<UserEntity> searchByEmail(String email){
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        List<UserEntity> user = userRepository.searchByEmail(email);
        if (user == null || user.isEmpty()){
            throw new NoSuchElementException("User not found");
        }
        return user;
    }
}
