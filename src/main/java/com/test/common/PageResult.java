package com.test.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description       ：分页查询的  通用类
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2019/11/23 21:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 查询数据
     */
    private List<T> items;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long totalPage;

    public PageResult(List<T> items, Long total){
        this.items = items;
        this.total = total;
    }

}
