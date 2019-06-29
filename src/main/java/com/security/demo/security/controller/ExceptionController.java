package com.security.demo.security.controller;

import com.security.demo.common.Result;
import com.security.demo.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

/**
 * @author Relic
 * @date 2019/6/27 11:58
 */
@Api(tags = "异常处理,支持返回所有http请求方式")
@ControllerAdvice
public class ExceptionController {

    @ApiOperation("用户密码验证失败")
    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseBody
    public Result badCredentials(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        response.setStatus(200);
        return ResultGenerator.genFailResult("密码有误，或者token失效");
    }

    @ApiOperation("找不到文件")
    @ExceptionHandler(value = {FileNotFoundException.class})
    @ResponseBody
    public Result fileNotFound(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        response.setStatus(404);
        return ResultGenerator.genNotFoundError();
    }

    @ApiOperation("请求方法不允许")
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Result methodNotSupported(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        response.setStatus(404);
        return ResultGenerator.genNotFoundError();
    }
}
