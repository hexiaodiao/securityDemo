package com.security.demo.security.service.impl;

import com.security.demo.security.jwt.JwtTokenUtil;
import com.security.demo.security.jwt.JwtUser;
import com.security.demo.security.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Relic
 * @date 2019/6/27 11:59
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(userDetails, jwtUser);
        final String token = jwtTokenUtil.generateToken(jwtUser);
        redisTemplate.opsForValue().set("token" + username, token, expiration);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(token, user)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public boolean logout(String token) {
        String realToken = token.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(realToken);
        try {
            redisTemplate.delete("token" + username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
