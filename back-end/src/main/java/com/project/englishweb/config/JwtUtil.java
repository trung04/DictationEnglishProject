package com.project.englishweb.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_256_bit_long_secret_key_here_1234567890"; // Đảm bảo chiều dài là ít nhất 32 ký tự
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 giờ

    private final Key key;

    public JwtUtil() {
        if (SECRET_KEY.length() < 32) {
            throw new IllegalArgumentException("SECRET_KEY must be at least 256 bits (32 characters).");
        }
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token); // Nếu parse thành công thì token hợp lệ
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace("Bearer ", "")) // Loại bỏ "Bearer " prefix
                .getBody();
    }
}
