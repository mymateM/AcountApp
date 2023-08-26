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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String SECRET_KEY = "8f0bd83363c7a8a609eaf046b324283236616a9b8373085999b4b1ba2ce3c993315e6281dc53cf9d12a112909a944a065d6244a2efbdeeac11d3f97ed43b1f55";

  public static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 60 * 2; // 2시간
  public static final long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7 * 2; // 2주

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
    return generateToken(extraClaims, userDetails, REFRESH_TOKEN_VALIDITY);
  }

  public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return generateToken(extraClaims, userDetails, ACCESS_TOKEN_VALIDITY);
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
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }


}
