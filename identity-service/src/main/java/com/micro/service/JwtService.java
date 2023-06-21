package com.micro.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private String SECRET = "e268e06278f12138c87dfb5ab506cf453b253b58d22e8ef579225a28d62e0a86";

	public void validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}
	
	public String generateToken(String userName) {
		Map<String,Object> claims = new HashMap<>();
		return createToken(claims,userName);
	}

	private String  createToken(Map<String, Object> claims, String userName) {
	  return Jwts.builder().setClaims(claims)
			  .setSubject(userName)
			  .setIssuedAt(new Date(System.currentTimeMillis()))
			  .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
			  .signWith(getSignKey(),SignatureAlgorithm.HS256)
			  .compact();
		
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
