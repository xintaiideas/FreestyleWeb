package com.freestyle.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.freestyle.util.DateUtil;

public class DateUtilTest {

    @Test
    public final void testFormat() {
        Date currrentDate = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1 = sdf.format(currrentDate);
        
        String result2 = DateUtil.format(currrentDate, DateUtil.YYYY_MM_DD_24H_MM_SS);
        
        Assert.assertEquals(result1, result2);
        
        Assert.assertEquals(DateUtil.TIME_12H_MM, "hh:mm");
        Assert.assertEquals(DateUtil.TIME_12H_MM_SS, "hh:mm:ss");
        Assert.assertEquals(DateUtil.TIME_24H_MM, "HH:mm");
        Assert.assertEquals(DateUtil.TIME_24H_MM_SS, "HH:mm:ss");
        Assert.assertEquals(DateUtil.YYYY_MM_DD, "yyyy-MM-dd");
        Assert.assertEquals(DateUtil.YYYY_MM_DD_12H_MM, "yyyy-MM-dd hh:mm");
        Assert.assertEquals(DateUtil.YYYY_MM_DD_12H_MM_SS, "yyyy-MM-dd hh:mm:ss");
        Assert.assertEquals(DateUtil.YYYY_MM_DD_24H_MM, "yyyy-MM-dd HH:mm");
        Assert.assertEquals(DateUtil.YYYY_MM_DD_24H_MM_SS, "yyyy-MM-dd HH:mm:ss");
        
    }

    @Test
    public final void testFormatCurrentDateString() {
        
    }

    @Test
    public final void testFormatCurrentDate() {
       
    }

    @Test
    public final void testParse() {
        
    }

}
