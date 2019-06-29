package com.security.demo.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Data
public class AdminVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 显示名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Date createTime;

}
