package com.test.controller;

import com.test.common.PageResult;
import com.test.common.Result;
import com.test.pojo.UserLoginLog;
import com.test.service.UserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 19:21
 */
@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private UserLoginLogService logService;

    /**
     * 分页查询日志
     * @return Result
     */
    @GetMapping("page")
    public PageResult<UserLoginLog> page(@RequestParam Integer page, @RequestParam Integer size) {
        return logService.queryLogByPage(page, size);
    }

    @DeleteMapping("{id}")
    public Result deleteLog(@PathVariable Long id) {
        int rs = logService.deleteLog(id);
        if (rs > 0) {
            return new Result("删除成功!", HttpStatus.OK.value());
        }
        return new Result("删除失败!", HttpStatus.BAD_REQUEST.value());
    }
}
