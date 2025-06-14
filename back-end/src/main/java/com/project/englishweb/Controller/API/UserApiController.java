package com.project.englishweb.Controller.API;

import com.project.englishweb.DTO.ChangeEmailRequest;
import com.project.englishweb.DTO.ChangePasswordRequest;
import com.project.englishweb.DTO.UserAccountDTO;
import com.project.englishweb.DTO.UserPublicProfileDTO;
import com.project.englishweb.Service.API.UserApiService;
import com.project.englishweb.Service.UserService;
import com.project.englishweb.config.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserApiController {

    private final UserApiService userApiService;
    private  final UserService userService;
    private final String secretKey = "yourSecretKey";
    @Autowired
    public UserApiController(UserApiService userApiService, UserService userService) {
        this.userApiService = userApiService;
        this.userService = userService;
    }

    @PostMapping("/add-time")
    public int addTime(@RequestParam Long userId, @RequestParam(defaultValue = "10") int seconds) {
        return userService.addTime(userId, seconds);
    }
    @GetMapping("/hello")
    public String hello(){
        return "haha";
    }




    // Lấy thông tin tài khoản của người dùng
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/account-info")
    public ResponseEntity<UserAccountDTO> getAccountInfo(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(null);
        }

        String token = authHeader.substring(7); // Loại bỏ "Bearer " prefix
        try {
            if (!jwtUtil.isTokenValid(token)) {
                return ResponseEntity.status(401).body(null);
            }

            UserAccountDTO accountInfo = userApiService.getAccountInfo(token); // Gọi service với token
            if (accountInfo == null) {
                return ResponseEntity.status(404).body(null);
            }

            return ResponseEntity.ok(accountInfo);
        } catch (Exception e) {
            System.out.println("Error during account info retrieval: " + e.getMessage());
            return ResponseEntity.status(400).body(null);
        }
    }




    // Trích xuất username từ JWT token
    public String extractUsernameFromToken(String token) {
        try {
            System.out.println("Extracting username from token: " + token);


            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)) // Đảm bảo rằng secretKey chính xác
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", "")) // Loại bỏ "Bearer " prefix
                    .getBody();

            String username = claims.getSubject();
            System.out.println("Extracted username: " + username);

            return username;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error extracting username: " + e.getMessage());
            throw new RuntimeException("Invalid token");
        }
    }


    // Lấy thông tin hồ sơ công khai của người dùng
    @GetMapping("/public-profile/{username}")
    public ResponseEntity<UserPublicProfileDTO> getPublicProfile(@PathVariable String username) {
        try {
            UserPublicProfileDTO publicProfile = userApiService.getPublicProfile(username);
            return ResponseEntity.ok(publicProfile);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null); // User not found
        }
    }

    // Đổi mật khẩu
    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestHeader(value = "Authorization", required = false) String token,
                                                 @RequestBody ChangePasswordRequest request) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Unauthorized: No token provided.");
        }

        try {
            userApiService.changePassword(token, request);
            return ResponseEntity.ok("Đổi mật khẩu thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Đổi mật khẩu thất bại!");
        }
    }

    // Đổi email
    @PostMapping("/update-email")
    public ResponseEntity<String> updateEmail(@RequestHeader(value = "Authorization", required = false) String token,
                                              @RequestBody ChangeEmailRequest request) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Unauthorized: No token provided.");
        }

        try {
            userApiService.changeEmail(token, request);
            return ResponseEntity.ok("Đổi email thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Đổi email thất bại!");
        }
    }
}