package com.security.demo.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Relic
 * @date 2019/6/27 11:57
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Qualifier("userDetailsServiceImpl")
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.header}")
    private String head;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(this.head);
        if (token != null && token.startsWith(tokenHead)) {
            final String authToken = token.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            log.info("checking authentication :{}", username);
            //判断redis有没有这个token
            boolean has = authToken.equals(redisTemplate.opsForValue().get("token" + username));
            if (!has) {
                request.getRequestDispatcher("/401").forward(request, response);
                return;
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //这里我们要重新去数据库中查询用户的权限
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //判断token是否有效和失效
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    log.info("authenticated user:{}, setting security context", username);
                    //这个来授权建立上下文对象
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
