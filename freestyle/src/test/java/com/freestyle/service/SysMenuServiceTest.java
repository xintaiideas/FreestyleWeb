package com.freestyle.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.freestyle.service.admin.ISysMenuService;

@ActiveProfiles("production")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext.xml"})
public class SysMenuServiceTest {
    
    @Autowired
    private ISysMenuService sysMenuService;

    @Test
    public final void testInsertMenu() {
//        sysMenuService.insertMenu("12323");
    }

}
