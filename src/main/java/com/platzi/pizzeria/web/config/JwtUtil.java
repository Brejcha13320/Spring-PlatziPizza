package com.platzi.pizzeria.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final static String SECRET_KEY = "platzi_pizza";
    private final static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("platzi-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt((new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15))))
                .sign(ALGORITHM);
    }

    public boolean isValid(String jwt){
        try {
        JWT.require(ALGORITHM).build().verify(jwt);
        return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String jwt){
        return JWT.require(ALGORITHM).build().verify(jwt).getSubject();
    }

}
