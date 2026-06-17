package interview.guide.common.security;


import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil() {
        this.secretKey = Keys.hmacShaKeyFor("RedRumIsLearningJWT".getBytes());
    }

    public String extractUserId(String token) {
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
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
}
