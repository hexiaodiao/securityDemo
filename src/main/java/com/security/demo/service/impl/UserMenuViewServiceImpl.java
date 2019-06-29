package com.security.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.demo.dao.UserMenuViewMapper;
import com.security.demo.model.data.UserMenuViewDO;
import com.security.demo.model.vo.MenuVO;
import com.security.demo.service.UserMenuViewService;
import com.security.demo.utils.ModelUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Service
public class UserMenuViewServiceImpl extends ServiceImpl<UserMenuViewMapper, UserMenuViewDO> implements UserMenuViewService {

    @Override
    public List<MenuVO> listAll(String username) {
        QueryWrapper<UserMenuViewDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<UserMenuViewDO> list = list(queryWrapper);
        return ModelUtils.copyList(list, MenuVO.class);
    }


}