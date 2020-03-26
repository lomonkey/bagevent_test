package com.test;

import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 * description       ：
 *
 * @author ：lvhan
 * @version ：1.0
 * @date ：2020/3/26 9:33
 */
public class UserServiceTest {

    @Test
    public void md5Test() {
        System.out.println(DigestUtils.md5DigestAsHex(("qweqwe").getBytes()));
    }
}
