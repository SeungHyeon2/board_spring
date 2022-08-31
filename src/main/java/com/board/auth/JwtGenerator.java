package com.board.auth;

import java.security.Key;
import java.sql.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.board.domain.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtGenerator {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("{jwt.expireHour}")
	private int expireHour;
	
	private Key jwtSecretKey;
	private int expireTime;
	
	@PostConstruct
	public void init() {
		jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes());
		expireTime = 1000 * 60 * 60 * expireHour;
	}
	
	AuthenticatedUser parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
			return new AuthenticatedUser(body.getId(), (String) body.get("nickname"));
			
		}catch(ExpiredJwtException e) {
			log.error(e.getMessage());
		}catch(JwtException | ClassCastException e) {
			log.error("jwt parse error", e);
		}
		
		return null;
	}
	
	
	public String generateToken(MemberVO member) {
		Claims claims = Jwts.claims().setId(member.getUserId());
		claims.setExpiration(new Date(System.currentTimeMillis() + expireTime));
		claims.put("nickname", member.getUserName());
		return Jwts.builder()
				.setClaims(claims)
				.signWith(jwtSecretKey, SignatureAlgorithm.HS512)
				.compact();
	}
	
}
