package org.example.util;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.example.dto.request.AuthRequest;
import org.example.dto.request.ValidRequest;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String key;

    @PostConstruct
    public void init() {
        this.key = "dad1651459c1e86875da325a5a742dcd33435f9c6b71bc3af59b00dad885e77e";
    }

    public String extractUsername(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("username", String.class);
    }

    public String extractPassword(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("password", String.class);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        String prefix = "Bearer";
        if (token.contains(prefix)) {
            token = token.substring(prefix.length());
        }
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(AuthRequest authRequest) {
        long jwtExpiration = 1000 * 60 * 60 * 10;
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", authRequest.getUsername());
        extraClaims.put("password", authRequest.getPassword());
        return buildToken(extraClaims, authRequest, jwtExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, AuthRequest authRequest, Long expiration) {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(authRequest.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Boolean validateToken(ValidRequest validRequest) {

            String tokenUsername = extractUsername(validRequest.getToken());
            String tokenPassword = extractPassword(validRequest.getToken());

            String username = validRequest.getUsername();
            String password = validRequest.getPassword();

            return ((tokenUsername.equals(username))
                    &&  (tokenPassword.equals(password))
                    && !isTokenExpired(validRequest.getToken()));
        }
}
