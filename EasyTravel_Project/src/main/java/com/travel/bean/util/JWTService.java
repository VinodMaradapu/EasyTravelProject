package com.travel.bean.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JWTService {

	@Value("${app.jwt.secretKey}")
	private String jwtSecretKey;
	
	public String generateToken(String email,String firstName) {
		 try {
		        Claims claims = Jwts.claims().setSubject(toString());
		        claims.put("firstName",firstName);
		        claims.put("email", email);
		        Date now = new Date();
		        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 10);
		        String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expiration)
		                .signWith(SignatureAlgorithm.HS256,jwtSecretKey).compact();
		        return token;
		    } catch (JwtException e) {
		        return null;
		    }
	}
	public boolean validateToken(String token) {
		try {
			Claims claims = extractClaims(token);
			isTokenExpired(claims);
			return Boolean.TRUE;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isTokenExpired(Claims claims) {
		Date expiration = claims.getExpiration();
		boolean isExpired = expiration.before(new Date());
		return isExpired;
	}

	public Claims extractClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
		} catch (Exception e) {
		}
		return claims;
	}
	public int extractCaptainId(String token) {
		Claims claims = extractClaims(token);
		int role = claims.get("id", Integer.class);
		return role;
	}
}