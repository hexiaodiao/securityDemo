package com.security.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.demo.model.data.AdminDO;
import com.security.demo.model.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Relic
 * @date 2019-06-26 16:40:50
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminDO> {

    /**
     * 根据id获取管理员
     *
     * @param id 管理员主键id
     * @return AdminVO 管理员
     * @author Relic
     * @title getById
     * @date 2019/6/28 10:52
     */
    @Select("select a.id, a.name, a.username, a.create_time as createTime, r.id as roleId, r.role_show_name as " +
            "roleName from admin a join role r on a.role_id = r.id where a.id = #{id}")
    AdminVO getById(@Param("id") Long id);

    /**
     * 获取所有
     *
     * @return java.util.List<AdminVO> 管理员集合
     * @author Relic
     * @title listAll
     * @date 2019/6/28 10:54
     */
    @Select("select a.id, a.name, a.username, a.create_time as createTime, r.id as roleId, r.role_show_name as " +
            "roleName from admin a join role r on a.role_id = r.id")
    List<AdminVO> listAll();

    /**
     * 根据用户名获取角色名
     *
     * @param username 用户名
     * @return java.lang.String
     * @author Relic
     * @title getRoleName
     * @date 2019/6/28 20:33
     */
    @Select("select r.role_name from admin a, role r where a.role_id = r.id and a.username = #{username}")
    String getRoleName(@Param("username") String username);


}
