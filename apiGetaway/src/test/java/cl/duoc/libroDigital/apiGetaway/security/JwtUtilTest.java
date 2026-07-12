package cl.duoc.libroDigital.apiGetaway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String SECRET = "librodigital2026SecretKeyForJWTTokenGenerationAndValidation12345";
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void validateToken_acceptsValidToken() {
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder()
                .setSubject("admin_colegio")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60_000))
                .signWith(key)
                .compact();

        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    void validateToken_rejectsGarbage() {
        assertFalse(jwtUtil.validateToken("not-a-jwt"));
    }
}
