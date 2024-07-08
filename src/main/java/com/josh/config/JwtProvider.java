

package com.josh.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String JWT_HEADER_PREFIX = "Bearer ";

    public static String generateJwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(key)
                .compact();
    }

    public static String getEmailFromJwtToken(String token) {
        if (token.startsWith(JWT_HEADER_PREFIX)) {
            token = token.substring(JWT_HEADER_PREFIX.length());
        }
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // This should handle parsing correctly
                .getBody()
                .getSubject();
    }
}

