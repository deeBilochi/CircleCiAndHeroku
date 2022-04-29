package com.example.springdemo.controller;

import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> users() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return  users;
    }


    @PostMapping
    public User createUser(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return userRepository.save(user);
    }


    @GetMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        return userRepository.findById(userId).get();
    }
}

