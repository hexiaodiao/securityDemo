package com.security.demo.security.controller;

import com.security.demo.common.Result;
import com.security.demo.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Relic
 * @date 2019/6/27 11:57
 */
@Api(tags = "错误请求处理", description = "错误请求处理")
@RestController
public class BadRequestController {

    @ApiOperation("401无携带token或者token无效或者登录失败")
    @RequestMapping(value = "/401")
    public Result unauthorized(HttpServletResponse response) {
        response.setStatus(200);
        return ResultGenerator.genTokenErrorResult();
    }

    @ApiOperation("403权限不足")
    @RequestMapping("/403")
    public Result accessDenied(HttpServletResponse response) {
        response.setStatus(200);
        return ResultGenerator.genUnauthorizedResult();
    }
}
