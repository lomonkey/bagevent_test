package com.test.service.impl;

import com.test.common.MD5Utils;
import com.test.dao.UserDao;
import com.test.pojo.User;
import com.test.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 1:04
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User queryUser(User user) throws RuntimeException{
        String username = user.getUserName();
        String password = user.getPassword();
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            // 冲数据库中获取用户
            User selectUser = userDao.selectUser(username);
            // 获取盐
            String salt = selectUser.getSalt();
            // 加密表单密码是否与数据库中查询密码相同,相同则登陆成功
            String md5Password = MD5Utils.encodeMd5(salt + password);
            if (selectUser.getPassword().equals(md5Password)) {
                // 记录日志
                return selectUser;
            }
            return null;
        }
        throw new RuntimeException("参数获取失败!");
    }

    @Override
    public User verify(String token) {
        try {
            String[] info = token.split("-");
            User user = new User();
            user.setUserName(info[1]);
            user.setPassword(info[2]);
            return userDao.selectOne(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean register(User user) throws RuntimeException{
        User user1 = userDao.selectUser(user.getUserName());
        if (user1 != null) {
            throw new RuntimeException("用户已存在");
        }
        String uuid = MD5Utils.uuid();
        user.setSalt(uuid);
        user.setCreateTime(new Date());
        user.setState(1);
        user.setPassword(MD5Utils.encodeMd5(uuid + user.getPassword()));
        int rs = userDao.insert(user);
        return rs > 0;
    }

    @Override
    public User queryUserByUserName(String userName) {
        return userDao.selectUser(userName);
    }
}
