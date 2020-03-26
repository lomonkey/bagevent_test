package com.test.controller;

import com.test.common.CookieUtils;
import com.test.common.MD5Utils;
import com.test.common.Result;
import com.test.pojo.User;
import com.test.service.UserLoginLogService;
import com.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 0:54
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginLogService userLoginLogService;


    /** token 开头*/
    private static final String TOKEN_PREFIX ="token-";

    /**
     * 登陆功能
     *
     * @param user user
     * @return 结果信息
     */
    @PostMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        try {
            User selectUser = userService.queryUser(user);
            if (selectUser != null) {
                String token = MD5Utils.generateToken(TOKEN_PREFIX + selectUser.getUserName() + "-" + selectUser.getPassword());
                CookieUtils.setCookie(request, response, "token", token);
                // 记录登陆日志
                userLoginLogService.saveLog(selectUser, request);
                return new Result("登陆成功!", HttpStatus.OK.value());
            } else {
                return new Result("用户名或密码错误", HttpStatus.BAD_REQUEST.value());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new Result(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * 验证用户是否登录
     * @return Result
     */
    @PostMapping("verify")
    public Result verify(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isNotBlank(token)) {
            String parseToken = MD5Utils.parseToken(token);
            assert parseToken != null;
            if (!parseToken.startsWith(TOKEN_PREFIX)) {
                return new Result("登录错误!", HttpStatus.BAD_REQUEST.value());
            }
            User user = userService.verify(parseToken);
            if (user != null) {
                Map<String, Object> map = new HashMap(2);
                map.put("username", user.getUserName());
                map.put("id", user.getUserId());
                return new Result("验证成功!", HttpStatus.OK.value(), map);
            }
            return new Result("验证失败!", HttpStatus.BAD_REQUEST.value());
        } else {
            return new Result("token获取失败!", HttpStatus.NOT_FOUND.value());
        }
    }

    @PostMapping("register")
    public Result register(@RequestBody @Valid User user, BindingResult result) {
        if(result.hasErrors()){
            //如果没有通过,跳转提示
            Map<String, String> map = getErrors(result);
            return new Result("注册失败!", HttpStatus.BAD_REQUEST.value(), map);
        }else{
            try {
                boolean rs = userService.register(user);
                if (rs) {
                    return new Result("注册成功!", HttpStatus.OK.value());
                }
                return new Result("注册失败!", HttpStatus.BAD_REQUEST.value());
            } catch (Exception e) {
                return new Result(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            }
        }

    }

    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            System.out.println("error.getField():" + error.getField());
            System.out.println("error.getDefaultMessage():" + error.getDefaultMessage());

            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }

    /**
     * 根据用户名称 查询用户
     * @param userName userName
     * @return
     */
    @GetMapping("findByUserName")
    public Result queryUserByUserName(@RequestParam String userName) {
        User user = userService.queryUserByUserName(userName);
        if (user != null) {
            return new Result("用户存在", HttpStatus.OK.value());
        }
        return new Result("用户不存在", HttpStatus.NOT_FOUND.value());
    }
}
