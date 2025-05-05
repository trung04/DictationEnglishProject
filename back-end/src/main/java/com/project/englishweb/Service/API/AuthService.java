package com.project.englishweb.Service.API;

import com.project.englishweb.DTO.AuthRequest;
import com.project.englishweb.DTO.AuthResponse;
import com.project.englishweb.DTO.RegisterRequest;


public interface AuthService {
     AuthResponse login(AuthRequest request);
    void logout(String token);
    void register(RegisterRequest request);
}
