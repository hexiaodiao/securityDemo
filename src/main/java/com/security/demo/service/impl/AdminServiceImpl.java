package com.security.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.demo.dao.AdminMapper;
import com.security.demo.model.data.AdminDO;
import com.security.demo.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminDO> implements AdminService {

    @Override
    public AdminDO getUserByUsername(String username) {
        LambdaQueryWrapper<AdminDO> queryWrapper = Wrappers.lambdaQuery(new AdminDO());
        queryWrapper.eq(AdminDO::getUsername, username);
        return getOne(queryWrapper);
    }

}