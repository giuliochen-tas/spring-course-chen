package com.example.spring_course.service;

import com.example.spring_course.model.UserResponse;
import com.example.spring_course.exception.AppException;
import com.example.spring_course.repository.model.UserEntity;
import com.example.spring_course.repository.UserRepository;
import com.example.spring_course.service.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
            UserEntity savedUser = userRepository.save(user);

            return new UserResponse(savedUser.getId(), user.getName(), user.getEmail(),
                    user.getAge(), user.getAddress());
        }
        return new UserResponse(userEntity.get().getId(), userEntity.get().getName(),
                userEntity.get().getEmail(), userEntity.get().getAge(), userEntity.get().getAddress());
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public List<UserEntity> searchByName(String name){
        List<UserEntity> user = userRepository.searchByName(name);
        return user;
    }

    public List<UserEntity> searchByEmail(String email){
        List<UserEntity> user = userRepository.searchByEmail(email);
        log.info("User found for email {} and size {}", email, user.size());
        return user;
    }

    public UserResponse findById(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User ID " + id + " not found", HttpStatus.NOT_FOUND));
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getAge(), user.getAddress());
    }
}
