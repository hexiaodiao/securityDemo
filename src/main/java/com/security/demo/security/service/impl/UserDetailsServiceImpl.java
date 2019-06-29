package com.security.demo.security.service.impl;

import com.security.demo.common.enumration.RoleEnum;
import com.security.demo.model.data.AdminDO;
import com.security.demo.security.jwt.JwtUser;
import com.security.demo.service.AdminService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Relic
 * @date 2019/6/27 11:59
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private AdminService adminService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminDO user = adminService.getUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("找不到用户: " + username);
        } else {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(RoleEnum.roleName(user.getRoleId())));
            return new JwtUser(user.getId(), user.getName(), user.getUsername(), user.getPassword(), authorities);
        }
    }
}
