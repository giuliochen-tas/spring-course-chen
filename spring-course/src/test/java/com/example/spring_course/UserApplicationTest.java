package com.example.spring_course;

import com.example.spring_course.model.UserResponse;
import com.example.spring_course.repository.UserRepository;
import com.example.spring_course.repository.model.UserEntity;
import com.example.spring_course.service.UserService;
import com.example.spring_course.service.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserApplicationTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void calcolaTotale(){
        double prezzo = 0.0;
        int quantita = 3;
        double totale = prezzo * quantita;
        assertEquals(30.0, totale);
    }

    @Test
    public void createUserTest(){
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Giulio");
        requestDTO.setAddress("MI - IT");
        requestDTO.setAge(29);
        requestDTO.setEmail("giulio.chen@tasgroup.eu");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        UserResponse userResponse = userService.createUser(requestDTO);
        assertNotNull(userResponse);
        assertEquals(1L, userResponse.getId());

        //aggiungere un throw in userService.createUser
        assertThrows(Exception.class, () -> {
            userService.createUser(null);
        });
    }
}
