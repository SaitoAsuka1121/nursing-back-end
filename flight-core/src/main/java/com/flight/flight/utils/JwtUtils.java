package com.flight.flight.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liu
 */
public class JwtUtils {

    private static final String JWT_TOKEN = "A-SOUL:Ava!Bella!Carol!Diana!Eileen";

    public static String createToken(String userId){
        Map<String,Object> claims = new HashMap<>(10000000);
        claims.put("userId",userId);
        /**
         *  <p>signWith 签发算法，秘钥为jwtToken
         *  setClaims  body数据，要唯一，自行设置
         *  .setIssuedAt(new Date()) // 设置签发时间
         *  setExpiration 时间
         *  </p>
         */
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, JWT_TOKEN)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 1000));
        return jwtBuilder.compact();
    }

    public static Map<String, Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(JWT_TOKEN).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
