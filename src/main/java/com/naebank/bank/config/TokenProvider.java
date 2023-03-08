package com.naebank.bank.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class TokenProvider {
    private static final String SECRET = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E";

    public String createToken(String userEmail) {

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(SECRET)
                )
                .compact();
    }

    public String getUserEmail(String token) {
        Claims claims = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(SECRET))
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(TextCodec.BASE64.decode(SECRET))
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
}
