package com.chocolatestore.security.JWT;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtProvider {

    @Value(value = "${jwt-secret-key}")
    public String key;

    @Value(value = "${expire-time}")
    public int expireTime;

    public String createJwtToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(expireTime)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String getLoginFromJwt(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean isValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return false;
    }
}
