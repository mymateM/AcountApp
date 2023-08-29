package com.connect.accountApp.global.common.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${application.security.jwt.expiration}")
  public long accessTokenExpiration;
  @Value("${application.security.jwt.expiration.refresh-token.expiration}")
  public long refreshTokenExpiration;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }


  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }


  public String generateAccessToken(UserDetails userDetails) {
    return generateAccessToken(new HashMap<>(), userDetails);
  }

  public String generateRefreshToken(UserDetails userDetails) {
    return generateRefreshToken(new HashMap<>(), userDetails);
  }

  public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return generateToken(extraClaims, userDetails, accessTokenExpiration);
  }

  public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return generateToken(extraClaims, userDetails, refreshTokenExpiration);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration ) {
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isExpiredToken(token);
  }

  private boolean isExpiredToken(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }


  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }


}
