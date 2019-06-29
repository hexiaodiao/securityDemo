package com.security.demo.common.enumration;

/**
 * @author Relic
 * @desc 角色枚举
 * @date 2019-06-26 17:09
 */
public enum RoleEnum {

    /**
     * 超级管理用户
     */
    SUPER_ADMIN(1, "ROLE_SUPER_ADMIN"),

    /**
     * 管理用户
     */
    ADMIN(2, "ROLE_ADMIN");

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;


    RoleEnum(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public static String roleName(Integer roleId) {
        for (RoleEnum value : RoleEnum.values()) {
            if (roleId.equals(value.roleId)) {
                return value.roleName;
            }
        }
        return null;
    }

    public Integer roleId() {
        return roleId;
    }

    public String roleName() {
        return roleName;
    }
}
