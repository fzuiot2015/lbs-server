package edu.fzu.lbs.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

/**
 * JWT工具类
 */
public class JwtTokenUtil {

    /**
     * 根据用户名和密码生成相应 Token
     * 其中用户名作为有效负载中的声明，密码作为加密密钥
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的 Token
     */
    public static String createToken(String username, String password) {
        Algorithm algorithm = Algorithm.HMAC256(password);
        Calendar calendar = Calendar.getInstance();
        Date issuedAt = calendar.getTime();
        calendar.add(Calendar.HOUR, 12);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withClaim("username", username)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    /**
     * Token认证
     *
     * @param token    Token
     * @param username 用户名
     * @param password 密码
     */
    public static void verifyToken(String token, String username, String password) {
        Algorithm algorithm = Algorithm.HMAC256(password);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("username", username)
                .build();
        verifier.verify(token);
    }

    /**
     * 从指定Token获取用户名
     *
     * @param token Token
     * @return 用户名
     */
    public static String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }
}
