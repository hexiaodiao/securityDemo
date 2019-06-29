package com.security.demo.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * @author Relic
 * @date 2019/6/27 11:58
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;
    /**
     * 密钥
     */
    private final String secret = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKo++i9J9dzAFtbxwowKDCo2mxi7MXxE8A8VvssaydWjjgmEz/HHMPLOhi1182a1si4pWL0/MizKnquD7T2Bu4jpQbAFnkNYEMEyq/kw904Xl0JCQHYFuvnI99RE8Q3KlTP6kEUGDjV34EL6vBGJcQvArLtj1xoP8y0nIfJ2Pw5TAgMBAAECgYAGGB8IllMwxceLhjf6n1l0IWRH7FuHIUieoZ6k0p6rASHSgWiYNRMxfecbtX8zDAoG0QAWNi7rn40ygpR5gS1fWDAKhmnhKgQIT6wW0VmD4hraaeyP78iy8BLhlvblri2nCPIhDH5+l96v7D47ZZi3ZSOzcj89s1eS/k7/N4peEQJBAPEtGGJY+lBoCxQMhGyzuzDmgcS1Un1ZE2pt+XNCVl2b+T8fxWJH3tRRR8wOY5uvtPiK1HM/IjT0T5qwQeH8Yk0CQQC0tcv3d/bDb7bOe9QzUFDQkUSpTdPWAgMX2OVPxjdq3Sls9oA5+fGNYEy0OgyqTjde0b4iRzlD1O0OhLqPSUMfAkEAh5FIvqezdRU2/PsYSR4yoAdCdLdT+h/jGRVefhqQ/6eYUJJkWp15tTFHQX3pIe9/s6IeT/XyHYAjaxmevxAmlQJBAKSdhvQjf9KAjZKDEsa7vyJ/coCXuQUWSCMNHbcR5aGfXgE4e45UtUoIE1eKGcd6AM6LWhx3rR6xdFDpb9je8BkCQB0SpevGfOQkMk5i8xkEt9eeYP0fi8nv6eOUcK96EXbzs4jV2SAoQJ9oJegPtPROHbhIvVUmNQTbuP10Yjg59+8=";

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHead}")
    private String tokenHeader;

    @Value("${jwt.header}")
    private String tokenName;


    /**
     * 根据token返回负载中的数据
     *
     * @param token token信息
     * @return java.util.Map<java.lang.String, com.auth0.jwt.interfaces.Claim>
     * @author Relic
     * @title getClaimsFromToken
     * @date 2019/6/26 19:43
     */
    public Map<String, Claim> getClaimsFromToken(String token) {
        if (token.startsWith(tokenHeader)) {
            token = token.substring(tokenHeader.length());
        }
        Map<String, Claim> claims;
        try {
            //生成签名密钥
            DecodedJWT jwtDecode = getJwtDecode(token);
            claims = jwtDecode.getClaims();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 通过request获取token中保存的信息
     *
     * @param request http请求
     * @return java.util.Map<java.lang.String, com.auth0.jwt.interfaces.Claim>
     * @author Relic
     * @title getClaimsFromRequest
     * @date 2019/6/26 19:44
     */
    public Map<String, Claim> getClaimsFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenName);
        return getClaimsFromToken(token);
    }


    /**
     * 根据jwt封装类生成token
     *
     * @param jwtUser jwt信息封装类
     * @return java.lang.String 生成的token
     * @author Relic
     * @title generateToken
     * @date 2019/6/26 19:44
     */
    public String generateToken(JwtUser jwtUser) {
        return JWT.create().withClaim("username", jwtUser.getUsername())
                .withClaim("id", jwtUser.getId()).withClaim("name", jwtUser.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000)).sign(getAlgorithm());
    }


    /**
     * 在token中查询所需要查询的值
     *
     * @param token token
     * @param key   需要查询的值
     * @return java.lang.String
     * @author Relic
     * @title getClaimFromToken
     * @date 2019/6/26 19:45
     */
    private String getClaimFromToken(String token, String key) {
        if (token.startsWith(tokenHeader)) {
            token = token.substring(tokenHeader.length());
        }
        DecodedJWT jwtDecode = getJwtDecode(token);
        Map<String, Claim> claims = jwtDecode.getClaims();
        Date expiration = jwtDecode.getExpiresAt();
        if (expiration.before(new Date())) {
            return null;
        }
        return claims.get(key).asString();
    }


    private DecodedJWT getJwtDecode(String token) {
        Verification require = JWT.require(getAlgorithm());
        return require.build().verify(token);
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }

    /**
     * 通过token获取用户名
     *
     * @param token token
     * @return java.lang.String
     * @author Relic
     * @title getUsernameFromToken
     * @date 2019/6/26 19:46
     */
    public String getUsernameFromToken(String token) {
        if (token.startsWith(tokenHeader)) {
            token = token.substring(tokenHeader.length());
        }
        String username;
        try {
            DecodedJWT jwtDecode = getJwtDecode(token);
            Claim claim = jwtDecode.getClaim("username");
            username = claim.asString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据token获取用户id
     *
     * @param token token
     * @return java.lang.Long
     * @author Relic
     * @title getUserIdFromToken
     * @date 2019/6/26 19:48
     */
    public Long getUserIdFromToken(String token) {
        String value = getClaimFromToken(token, "id");
        if (null != value) {
            return Long.parseLong(value);
        } else {
            return null;
        }
    }

    /**
     * 根据http请求中的token获取用户id
     *
     * @param request http请求
     * @return java.lang.Long
     * @author Relic
     * @title getUserIdFromHttpRequest
     * @date 2019/6/26 19:48
     */
    public Long getUserIdFromHttpRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenName);
        return getUserIdFromToken(token);
    }

    /**
     * 根据http请求中的token获取用户名
     *
     * @param request http请求
     * @return java.lang.String
     * @author Relic
     * @title getUsernameFromRequest
     * @date 2019/6/26 19:47
     */
    public String getUsernameFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenName);
        return getUsernameFromToken(token);
    }

    /**
     * 验证token是否过期
     *
     * @param token token
     * @return java.lang.Boolean
     * @author Relic
     * @title isTokenExpired
     * @date 2019/6/26 19:47
     */
    public Boolean isTokenExpired(String token) {
        try {
            DecodedJWT jwtDecode = getJwtDecode(token);
            Date expiration = jwtDecode.getExpiresAt();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 刷新token
     *
     * @param token token
     * @return java.lang.String
     * @author Relic
     * @title refreshToken
     * @date 2019/6/26 19:46
     */
    public String refreshToken(String token) {
        String refreshToken;
        try {
            DecodedJWT jwtDecode = getJwtDecode(token);
            Map<String, Claim> claims = jwtDecode.getClaims();
            JWTCreator.Builder builder = JWT.create();
            claims.forEach((key, value) -> {
                builder.withClaim(key, value.asString());
            });
            refreshToken = builder.sign(getAlgorithm());
        } catch (Exception e) {
            refreshToken = null;
        }
        return refreshToken;
    }


    /**
     * 根据token与用户信息作比较 返回校验结果
     *
     * @param token       传递的token
     * @param userDetails 用户信息
     * @return boolean 校验结果
     * @author Relic
     * @title validateToken
     * @date 2019/6/26 19:07
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).withSubject(username).build();
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
