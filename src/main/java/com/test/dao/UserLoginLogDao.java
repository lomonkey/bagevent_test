package com.test.dao;

import com.test.pojo.User;
import com.test.pojo.UserLoginLog;
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
public interface UserLoginLogDao extends Mapper<UserLoginLog> {

}
