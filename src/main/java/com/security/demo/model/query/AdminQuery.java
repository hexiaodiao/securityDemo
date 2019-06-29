package com.security.demo.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Data
@ApiModel("查询对象")
public class AdminQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 关键字
     */
    @ApiModelProperty(dataType = "String", value = "搜索关键字")
    private String keywords;

}
