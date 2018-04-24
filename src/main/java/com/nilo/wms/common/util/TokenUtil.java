package com.nilo.wms.common.util;

import com.nilo.wms.common.exception.IllegalTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * Created by admin on 2018/4/24.
 */
public class TokenUtil {

    private static String key = "wms";

    public static String createToken(String userId,
                                     Date expireDate) {
        String token = Jwts.builder().setSubject(userId)
                .signWith(SignatureAlgorithm.HS256, generalKey(key))
                .setIssuedAt(new Date()).setExpiration(expireDate).compact();
        return token;
    }

    public static Claims parseToken(String token) {
        if (token == null) {
            throw new IllegalTokenException();
        }
        Claims c = null;
        try {
            c = Jwts.parser().setSigningKey(generalKey(key))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new IllegalTokenException();
        }
        return c;
    }

    private static Key generalKey(String key) {
        byte[] encodedKey = Base64Codec.BASE64.encode(key.getBytes())
                .getBytes();
        return new SecretKeySpec(encodedKey, "AES");
    }
}
