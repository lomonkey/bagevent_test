package com.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.common.IpUtils;
import com.test.common.MD5Utils;
import com.test.common.PageResult;
import com.test.dao.UserDao;
import com.test.dao.UserLoginLogDao;
import com.test.pojo.User;
import com.test.pojo.UserLoginLog;
import com.test.service.UserLoginLogService;
import com.test.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 1:04
 */
@Service
@Transactional
public class UserLoginLogServiceImpl implements UserLoginLogService {

    @Autowired
    private UserLoginLogDao logDao;

    @Override
    public void saveLog(User selectUser, HttpServletRequest request) {
        UserLoginLog log = new UserLoginLog();
        log.setLoginIp(IpUtils.getIpAddr(request));
        log.setLoginTime(new Date());
        log.setUserId(selectUser.getUserId());
        logDao.insert(log);
    }

    @Override
    public PageResult<UserLoginLog> queryLogByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<UserLoginLog> logs = logDao.selectAll();
        PageInfo<UserLoginLog> info = new PageInfo<>(logs);
        return new PageResult<>(info.getList(), info.getTotal(), (long) info.getPages());
    }

    @Override
    public int deleteLog(Long id) {
        return logDao.deleteByPrimaryKey(id);
    }
}
