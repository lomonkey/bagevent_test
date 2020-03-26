package com.test.dao;

import com.test.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 1:05
 */
public interface UserDao extends Mapper<User> {
    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return User
     */
    @Select("select * from user where user_name=#{username}")
    User selectUser(@Param("username")String username);
}
