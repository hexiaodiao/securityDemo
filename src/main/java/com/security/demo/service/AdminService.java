package com.security.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.demo.model.data.AdminDO;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
public interface AdminService extends IService<AdminDO> {

    /**
     * 根据用户名返回用户对象
     *
     * @param username 用户名
     * @return com.wanda.commodity.model.data.AdminDO 用户对象
     * @author Relic
     * @title getUserByUsername
     * @date 2019/6/26 16:51
     */
    AdminDO getUserByUsername(String username);
}

