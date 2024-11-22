package com.example.MSAddress.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;



@Component
public class JwtUtils {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }


    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String validateTokenAndGetEmail(String token) {
        try {
            return extractEmail(token); // Assurez-vous que cette méthode vérifie également la validité du token
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new IllegalArgumentException("Token has expired", e);
        } catch (io.jsonwebtoken.SignatureException e) {
            throw new IllegalArgumentException("Invalid token signature", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token", e);
        }
    }

}
