package org.example.springstudy.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRE_TIME = 1000 * 60 * 60;

    // THis method will generate JWT Token

    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    //This method is to Validate JWT Token
    public boolean validateToken(String token, String user) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(user) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }


    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());

    }
    //Here we steal instance of an object that implements the Claims interface. JJWT library provides this for us.

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) //Verify token with secrety key
                .build() //Must call build() before parsing
                .parseClaimsJws(token) //Parse the JWT token
                .getBody(); // Extract claims.

    }

}
