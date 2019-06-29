package com.security.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.demo.model.data.UserMenuViewDO;
import com.security.demo.model.vo.MenuVO;

import java.util.List;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
public interface UserMenuViewService extends IService<UserMenuViewDO> {


    /**
     * 根据用户名获取菜单实体
     *
     * @param username 用户名
     * @return java.util.List<com.wanda.commodity.model.vo.MenuVO>
     * @author Relic
     * @title listAll
     * @date 2019/6/26 20:21
     */
    List<MenuVO> listAll(String username);
}

