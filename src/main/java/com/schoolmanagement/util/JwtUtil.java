package com.schoolmanagement.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // Token expiration time (10 hours)
    // Replace this with your Base64 encoded 256-bit secret key
    private static final String SECRET_KEY = "yourNewBase64Encoded256bitKey"; // Replace with the generated key

    public String generateToken(String username, List<String> roles) {
        // Decode the base64-encoded secret key
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY); // Decode the Base64 key
        Key signingKey = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName()); // Create the Key object

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)  // Store roles in the JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signingKey)  // Sign the token with the strong key
                .compact();
    }
    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            return false;
        }
    }

    // Extract Username
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // Extract Roles from the JWT token
    public List<String> extractRoles(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("roles", List.class);  // Extract roles from JWT claim
    }
}
