package com.example.springdemo.controller;

import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class UserControllerTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllUsers() throws Exception {
        List<User> users = Arrays.asList(new User("User 1", 23), new User("User 2", 45));
        when(userRepository.findAll()).thenReturn(users);

        String jsonContent = "[{\"name\": \"User 1\", \"age\" : 23}, {\"name\": \"User 2\", \"age\" : 45}]";
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(content().json(jsonContent));

    }


    @Test
    void shouldCreateTheUser() throws Exception {
        User userToBeCreated = new User("User2", 30);
        User createdUser = new User("User2", 30);
        createdUser.setId(2L);
        when(userRepository.save(any())).thenReturn(createdUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "User2")
                        .param("age", "30")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("User2"))
                .andExpect(jsonPath("$.age").value("30"));

        verify(userRepository, times(1)).save(userToBeCreated);
    }

    @Test
    void shouldReturnUserForProvidedUserId() throws Exception {
        User createdUser = new User("User2", 30);
        createdUser.setId(2L);
        when(userRepository.findById(any())).thenReturn(Optional.of(createdUser));

        mockMvc.perform(get("/api/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("User2"))
                .andExpect(jsonPath("$.age").value("30"));

        verify(userRepository, times(1)).findById(2L);
    }
}