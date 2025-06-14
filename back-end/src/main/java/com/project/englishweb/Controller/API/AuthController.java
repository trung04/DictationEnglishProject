package com.project.englishweb.Controller.API;

import com.project.englishweb.DTO.AuthRequest;
import com.project.englishweb.DTO.AuthResponse;
import com.project.englishweb.DTO.RegisterRequest;
import com.project.englishweb.Service.API.AuthService;
import com.project.englishweb.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        AuthResponse authResponse = authService.login(request);
        return authResponse;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "Đăng ký thành công!";
    }

    @PostMapping("/logout")
    public String logout() {
    
        return "Đăng xuất thành công!";
    }
}
