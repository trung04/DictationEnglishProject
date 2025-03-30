package com.project.englishweb.controllers.user;

import com.project.englishweb.Entity.User;
import com.project.englishweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        return userService.registerUser(username, email, password);
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }
}
