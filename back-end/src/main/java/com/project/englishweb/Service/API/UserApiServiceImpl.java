package com.project.englishweb.Service.API;

import com.project.englishweb.DTO.ChangeEmailRequest;
import com.project.englishweb.DTO.ChangePasswordRequest;
import com.project.englishweb.DTO.UserAccountDTO;
import com.project.englishweb.DTO.UserPublicProfileDTO;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Repository.UserRepository;
import com.project.englishweb.config.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;

@Service
public class UserApiServiceImpl implements UserApiService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserApiServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserPublicProfileDTO getPublicProfile(String username) {
        User user = userRepository.findByUsername(username)
                 .orElseThrow(() -> new RuntimeException("User not found"));

        UserPublicProfileDTO dto = new UserPublicProfileDTO();
        dto.setUserId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setActiveDays(user.getActiveDays());
        dto.setActiveHours(user.getActiveHours());
        return dto;
    }

   
    @Override
    public UserAccountDTO getAccountInfo(String token) {
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserAccountDTO dto = new UserAccountDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dto.setCreatedAt(user.getCreatedAt().format(formatter));
        return dto;
    }


    @Override
    public void changePassword(String token, ChangePasswordRequest request) {
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }


    @Override
    public void changeEmail(String token, ChangeEmailRequest request) {
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(request.getNewEmail());
        userRepository.save(user);
    }
}
