package com.project.englishweb.Controller.API;

import com.project.englishweb.Entity.User;
import com.project.englishweb.Service.UserService;
import com.project.englishweb.Service.UserServiceImpl;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")

public class UserApiController {
    private final UserServiceImpl userServiceImpl;


    public UserApiController( UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @PostMapping("/add-time")
    public long addTime(@RequestParam Long userId, @RequestParam(defaultValue = "10") long seconds) {
        return userServiceImpl.addTime(userId, seconds);
    }
    @GetMapping("/hello")
    public String hello() {
        return "hellooo";
    }
}
