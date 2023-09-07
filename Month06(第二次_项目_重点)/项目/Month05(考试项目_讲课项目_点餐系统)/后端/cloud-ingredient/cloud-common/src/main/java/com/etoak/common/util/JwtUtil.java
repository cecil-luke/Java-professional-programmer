package com.etoak.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static final String SECRET = "et2302";

    public static final long EXPIRE = 1000 * 60 * 3000; // 30

    /**
     * 创建JWT
     *
     * @param username
     * @return
     */
    public static String createToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRE);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setIssuedAt(now) // 签发时间
                .setExpiration(expiration) // 过期时间
                .setSubject(username)
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

/*    public static void main(String[] args) {
        String token = createToken("lisi");
        System.out.println(getUsername("eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODczMTM5NDcsImV4cCI6MTY4NzMxNTc0Nywic3ViIjoibGlzaSJ9.ZD-dnGpXR1fiKBgE5-Rvq2c0fAeHgcnc6tqC4Ia3ox8"));
    }*/
}
