package com.security.demo.security.controller;

import com.security.demo.common.Result;
import com.security.demo.model.dto.AdminDTO;
import com.security.demo.model.vo.MenuVO;
import com.security.demo.security.jwt.JwtTokenUtil;
import com.security.demo.security.service.AuthService;
import com.security.demo.service.UserMenuViewService;
import com.security.demo.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Relic
 * @date 2019/6/27 11:56
 */
@Api(tags = "登录和主界面相关api")
@RestController
public class HomeController {

    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMenuViewService userMenuViewService;
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${jwt.header}")
    private String headerAndToken;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    public HomeController(AuthService authService,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                          JwtTokenUtil jwtTokenUtil, UserMenuViewService userMenuViewService,
                          RedisTemplate<String, String> redisTemplate) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMenuViewService = userMenuViewService;
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation("登录")
    @PostMapping(value = "/sys/login")
    public Result createAuthenticationToken(AdminDTO adminDTO) throws AuthenticationException {
        String password = adminDTO.getPassword();
        String username = adminDTO.getUsername();
        String token = "";
        boolean has = false;
        //记住密码功能 判断密码是否为token 并校验
        if (password.startsWith(tokenHead) && jwtTokenUtil.validateToken(password.substring(tokenHead.length()), userDetailsService.loadUserByUsername(username))) {
            String tokenInRedis = redisTemplate.opsForValue().get("token" + username);
            //判断是否在redis中有 有则返回redis中的token 并且刷新过期时间
            if (tokenInRedis != null && tokenInRedis.length() != 0) {
                redisTemplate.expire("token" + username, expiration, TimeUnit.MILLISECONDS);
                token = tokenInRedis;
            } else {
                token = authService.refresh(password);
                redisTemplate.opsForValue().set("token" + username, token, expiration);
            }
            has = true;
        }

        if (!has) {
            token = authService.login(username, password);
        }
        // Return the token
        Map<String, Object> data = new HashMap<>(2);
        data.put("token", "Bearer " + token);
        data.put("expiration", new Date(System.currentTimeMillis() + expiration).getTime());
        return ResultGenerator.genSuccessResult(data);
    }


    @ApiOperation("重新获取token")
    @GetMapping("/sys/fresh")
    public Result refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(headerAndToken);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResultGenerator.genFailResult("获取token失败");
        } else {
            return ResultGenerator.genSuccessResult(refreshedToken);
        }
    }


    @ApiOperation("导航栏")
    @GetMapping("/nav")
    public Result index(HttpServletRequest request) {
        String token = request.getHeader(headerAndToken);
        String username = jwtTokenUtil.getUsernameFromToken(token.substring(tokenHead.length()));
        List<MenuVO> list = userMenuViewService.listAll(username);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("登出")
    @PostMapping(value = "/sys/logout")
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader(headerAndToken);
        authService.logout(token);
        return ResultGenerator.genSuccessResult("登出成功");
    }

}
