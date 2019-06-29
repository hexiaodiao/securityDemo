package com.security.demo.security.jwt;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Relic
 * @date 2019/6/27 11:58
 */
@Data
@Accessors(chain = true)
public class JwtUser implements UserDetails {
    private static final long serialVersionUID = -5477864747092434199L;
    /**
     * 用户id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 登录账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 认证类型
     */
    private Integer identityType;

    /**
     * 统一认证编号
     */
    private Long unionId;

    /**
     * 统一认证标识
     */
    private String unionUuid;

    /**
     * 统一认证名称
     */
    private String unionName;
    /**
     * 权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String name, String username, String password, Integer identityType, Long unionId, String unionUuid, String unionName, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.identityType = identityType;
        this.unionId = unionId;
        this.unionUuid = unionUuid;
        this.unionName = unionName;
        this.authorities = authorities;
    }

    public JwtUser(String username, String password, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.authorities = grantedAuthorities;
    }

    public JwtUser(Long id, String name, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public JwtUser() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
