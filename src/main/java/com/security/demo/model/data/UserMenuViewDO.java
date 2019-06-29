package com.security.demo.model.data;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Data
@TableName("user_menu_view")
@Accessors(chain = true)
public class UserMenuViewDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
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
     * 父菜单id
     */
    private Long parentId;
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
    private String adminName;
    /**
     * 权限 1超级管理员 2普通管理员
     */
    private Long roleId;

}
