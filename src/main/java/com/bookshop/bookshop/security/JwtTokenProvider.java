package com.bookshop.bookshop.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bookshop.bookshop.exception.ApplicationException;
import com.bookshop.bookshop.security.constants.JwtConstants;

import org.springframework.http.HttpStatus;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenProvider {

    // generating Jwt Token
    public String generateJwtToken(Authentication authentication){
        String userName = authentication.getName();

        Date issued = new Date();

        Date expired = new Date(issued.getTime() + JwtConstants.JWT_TOKEN_EXPIRATION);

        String jwtToken = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(issued)
                .setExpiration(expired)
                .signWith(signingKey())
                .compact();
        return jwtToken;
    }

    //obtaining signing key
    private Key signingKey(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(JwtConstants.JWT_SECRET_KEY)
        );
    }

    // getting user name from token
    public String getUserName(String jwtToken){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        String userName = claims.getSubject();
        return userName;
    }

    // Jwt token validation
    public boolean validateJwtToken(String jwtToken){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(signingKey())
                    .build()
                    .parse(jwtToken);
            return true;
        } 
        catch (IllegalArgumentException ex) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Jwt claims are incorrect");
        }
        catch (MalformedJwtException ex) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Jwt token is malformed or incorrect");
        } 
        catch (UnsupportedJwtException ex) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Jwt token is not supported");
        } 
        catch (ExpiredJwtException ex) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "The Jwt token has expired");
        } 
    }
    
}
