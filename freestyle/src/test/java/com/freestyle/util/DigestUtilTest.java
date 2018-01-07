package com.freestyle.util;

import org.junit.Test;

import com.freestyle.util.DigestUtil;

public class DigestUtilTest {

    @Test
    public final void testMd5() {
        System.out.println(DigestUtil.md5("123"));
    }

    @Test
    public final void testSha1() {
        System.out.println(DigestUtil.sha1("123"));
    }

    @Test
    public final void testDigest() {

    }

}
