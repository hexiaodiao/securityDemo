package com.security.demo.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Data
@Accessors(chain = true)
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 排序
     */
    private Integer orderNum;
}
