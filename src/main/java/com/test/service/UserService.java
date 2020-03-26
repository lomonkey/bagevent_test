package com.test.service;


import com.test.pojo.User;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 1:03
 */
public interface UserService{
    /**
     * 查询用户
     * @param user 根据用户信息查询用户
     * @return 查询到的用户
     */
    User queryUser(User user);

    /**
     * 验证用户是否登录
     * @param token token
     * @return user
     */
    User verify(String token);

    /**
     * 用户注册
     * @param user user
     * @return true 成功 false 失败
     */
    boolean register(User user);

    User queryUserByUserName(String userName);
}
