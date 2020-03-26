package com.test.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 0:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Result {
    /** 返回消息*/
    private String message;
    /** 返回状态码*/
    private Integer code;
    /** 返回的数据*/
    private Object data;

    public Result(String message, Integer code){
        this.message = message;
        this.code = code;
    }

}
