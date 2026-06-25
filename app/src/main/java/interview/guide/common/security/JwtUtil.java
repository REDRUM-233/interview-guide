package interview.guide.common.security;


import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil() {
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    public String extractUserId(String token) {
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public String extractRole(String token) {
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        return claims.get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            extractUserId(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String generateToken(String userId) {
        return Jwts.builder().subject(userId).issuedAt(new java.util.Date())
                   .expiration(new java.util.Date(System.currentTimeMillis() + 86400000)).signWith(secretKey).compact();
    }

    public String generateToken(String userId, String role) {
        return Jwts.builder().subject(userId).claim("role", role).issuedAt(new java.util.Date())
                   .expiration(new java.util.Date(System.currentTimeMillis() + 86400000)).signWith(secretKey).compact();
    }
}
