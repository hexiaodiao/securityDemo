package com.security.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.demo.model.data.UserMenuViewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Mapper
public interface UserMenuViewMapper extends BaseMapper<UserMenuViewDO> {

}
