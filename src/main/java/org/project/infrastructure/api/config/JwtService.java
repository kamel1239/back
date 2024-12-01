/*
  2024
*/
package org.project.infrastructure.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    public String secretKey;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        var date = new Date();
        var expirationDate = new Date(date.getTime() + 1000 * 60 * 60 * 10);
        return Jwts.builder().subject(userDetails.getUsername()).issuedAt(date)
            .expiration(expirationDate)
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        log.info("secret {}", secretKey);
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        // Create the key for HMAC-SHA256
        var key = Keys.hmacShaKeyFor(decodedKey);
        // Parse the JWT
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

}
