package com.test.common;

import org.junit.Test;

public class MD5UtilsTest {

    @Test
    public void testToken() {
        System.out.println(MD5Utils.parseToken("cXdlLWVmZTYzOTgxMjc5MjhmMWIyZTllZjMyMDdmYjgyNjYz"));
    }

    @Test
    public void uuidTest() {
        System.out.println(MD5Utils.uuid());
    }
}
