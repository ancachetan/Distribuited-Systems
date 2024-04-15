package com.ds.monitoring_microservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${secret.key}")
    private String secretKey;

    public boolean authorizeBasedByRoleAndId(String token, UUID id) {
        if (checkTokenValidity(token)) {
            String actualToken = token.substring(7);
            Claims claims = extractAllClaims(actualToken);
            String role = claims.get("role").toString();
            UUID tokenId = UUID.fromString(claims.get("id").toString());
            if (role.equals("ADMIN")) {
                return true;
            } else if (role.equals("CLIENT") && id.equals(tokenId)) {
                return true;
            }
        }
        return false;
    }

    private Claims extractAllClaims(String token) {
        Claims claims = null;
        try {
            Key secretKey = getSignInKey();
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return claims;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private boolean checkTokenValidity(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        if (!token.contains("Bearer ")) {
            return false;
        }
        String actualToken = token.substring("Bearer ".length());
        if (isTokenExpired(actualToken)) {
            return false;
        }
        return true;
    }
}
