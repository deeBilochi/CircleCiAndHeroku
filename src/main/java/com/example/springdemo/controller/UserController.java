package com.example.springdemo.controller;

import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> users() {
        return userService.users();
    }

    @PostMapping
    public User createUser(@RequestParam String name, @RequestParam int age) {
        User user = new User(name, age);
        return userService.create(user);
    }


    @GetMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.user(userId);
    }
}

