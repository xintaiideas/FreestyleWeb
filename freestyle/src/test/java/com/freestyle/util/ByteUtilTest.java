package com.freestyle.util;

import org.junit.Assert;
import org.junit.Test;

import com.freestyle.util.ByteUtil;

public class ByteUtilTest {

    @Test
    public final void testByte2Hex() {
        byte[] arr = new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
        String hex = ByteUtil.byte2Hex(arr);
        String m = "0102030405060708090a0b0c0d0e0f10";
        Assert.assertEquals(hex, m);
    }

}
