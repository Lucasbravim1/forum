package com.alura.forum.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alura.forum.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value(value = "${forum.jwt.expiration}")
	private String expirationDate;

	@Value(value = "${forum.jwt.secret}")
	private String secretKey;

	public String generateToken(Authentication authentication) {

		User user = (User) authentication.getPrincipal();
		Date date = new Date();
		Date finalDate = new Date(date.getTime() + Long.parseLong(expirationDate));

		return Jwts.builder().setIssuer("Api Forum").setSubject(user.getId().toString()).setIssuedAt(date)
				.setExpiration(finalDate).signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public Long getUserID(String token) {

		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String token) {

		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
