package com.security.demo.model.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Data
@TableName("admin")
@Accessors(chain = true)
public class AdminDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 权限 1超级管理员 2普通管理员
     */
    private Integer roleId;
    /**
     * 密码
     */
    private String password;
    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer tombstone;

}
