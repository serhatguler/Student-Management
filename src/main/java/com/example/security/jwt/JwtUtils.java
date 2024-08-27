package com.example.security.jwt;

import com.example.security.service.UserDetailsmpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.executable.ValidateOnExecution;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

     @Value("${backendapi.app.jwtExpirationMs}")//application.propertiese git oradaki degeri buraya ata
    private Long jwtExpirationMs;

    @Value("${backendapi.app.jwtSecret}")
    private String jwtSecret;

    /*
        Jwt de 3 tane ADim var

        1- Generate JWT token
        2-Validate  JWT token
        3- getUsernameFromJWT
     */

    // -----------------------------GENERATE JWT TOKEN------------------------------
    //bize gelen user ile jWT token create ettik.
    public String generateJwtToken(Authentication authentication){

        UserDetailsmpl userDetails = (UserDetailsmpl) authentication.getPrincipal();
        return generateJwtTokenFromUsername(userDetails.getUsername());

    }

    public String generateJwtTokenFromUsername(String username){
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // -----------------------------VALIDATE JWT TOKEN------------------------------

    public  boolean validateJwtToken(String jwtToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Jwt token is expired : {}" ,e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Jwt token is unsupported : {}" ,e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Jwt token is invalid : {}" ,e.getMessage());
        } catch (SignatureException e) {
            LOGGER.error("Jwt token signature is invalid : {}" ,e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Jwt  is empty : {}" ,e.getMessage());
        }
        return false;
    }

    // -----------------------------getUsernameFromJWT------------------------------
    //Jwt tokenindan neden user i almak isteyelim
    //username unique oldugundan anlik olarak login olan userin username fieldini aliriz.unique oldugundan DB den istegimiz field i aliriz.
    public String getUsernameFromJwtToken(String jwtToken){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }


}
