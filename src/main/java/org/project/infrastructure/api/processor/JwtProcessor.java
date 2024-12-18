/*
  2024
*/
package org.project.infrastructure.api.processor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.user.model.TokenInfoModel;
import org.project.domain.user.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtProcessor implements AuthService {

    @Value("${jwt.secret}")
    public String secretKey;

    @Override
    public TokenInfoModel extractTokenInfo(String token) {
        var claims = extractAllClaims(token);
        return new TokenInfoModel(claims.getId(), token, claims.getExpiration());
    }

    @Override
    public TokenInfoModel generateToken(UserDetails userDetails) {
        var date = new Date();
        // TODO  handle the expiration date
        var expirationDate = new Date(date.getTime() + 1000 * 60 * 60 * 10);
        var id = UUID.randomUUID().toString();
        return new TokenInfoModel(id,
            Jwts.builder().id(id).subject(userDetails.getUsername()).issuedAt(date)
                .expiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))).compact(),
            expirationDate);
    }

    private Claims extractAllClaims(String token) {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        // Create the key for HMAC-SHA256
        var key = Keys.hmacShaKeyFor(decodedKey);
        // Parse the JWT
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

}
