package com.test.interceptor;

import com.test.common.CookieUtils;
import com.test.common.MD5Utils;
import com.test.dao.UserDao;
import com.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 18:13
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserDao userDao;


    /** token 开头*/
    private static final String TOKEN_PREFIX ="token-";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*String token = CookieUtils.getCookieValue(httpServletRequest, "token");
        // 解析过后的token
        String parseToken = MD5Utils.parseToken(token);
        assert parseToken != null;
        if (parseToken.startsWith("TOKEN_PREFIX")) {
            String[] info = token.split("-");
            User user = new User();
            user.setUserName(info[1]);
            user.setPassword(info[2]);
            User selectUser = userDao.selectOne(user);
            if (selectUser != null) {
                return true;
            }
        }*/
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
