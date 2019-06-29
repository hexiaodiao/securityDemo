package com.security.demo.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Data
public class UserMenuViewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @TableId(type = IdType.ID_WORKER)
    private String menuName;
    /**
     * 菜单url
     */
    private String url;
    /**
     * 类型   0：目录   1：菜单
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String adminName;
    /**
     * 权限 1超级管理员 2普通管理员
     */
    private Integer roleId;

}
