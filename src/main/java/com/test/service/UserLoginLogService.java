package com.test.service;


import com.test.common.PageResult;
import com.test.pojo.User;
import com.test.pojo.UserLoginLog;

import javax.servlet.http.HttpServletRequest;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 1:03
 */
public interface UserLoginLogService {

    void saveLog(User selectUser, HttpServletRequest request);

    PageResult<UserLoginLog> queryLogByPage(Integer page, Integer size);

    int deleteLog(Long id);
}
