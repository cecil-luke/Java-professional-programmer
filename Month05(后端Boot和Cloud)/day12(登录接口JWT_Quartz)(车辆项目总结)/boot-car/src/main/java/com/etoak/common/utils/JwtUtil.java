package com.etoak.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

  public static final String KEY = "et2301";

  /**
   * 10分钟毫秒数
   */
  public static final long expire = 1000 * 60 * 10;

  /**
   * 创建JWT
   */
  public static String createJwt(String username) {
    Date issueDate = new Date();
    return Jwts.builder()
      .signWith(SignatureAlgorithm.HS256, KEY)
      .setIssuedAt(issueDate) // 签发时间
      .setExpiration(new Date(issueDate.getTime() + expire))
      .setSubject(username)
      .compact();
  }

  public static String getUsername(String jwt) {
    return Jwts.parser()
      .setSigningKey(KEY)
      .parseClaimsJws(jwt)
      .getBody().getSubject();
  }

  public static void main(String[] args) {
    // MalformedJwtException
    // ExpiredJwtException
    // SignatureException

    // String token = createJwt("zs");
    // System.out.println(token);

    String username = getUsername("eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ0Nzk5OTAsImV4cCI6MTY4NDQ4MDU5MCwic3ViIjoienMifQ.hMVT73WhIrV9ouYGBAR9kqiyMPy784mh_UJg2QmC_bM");
    System.out.println(username);
  }






}
