package com.freestyle.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.freestyle.domain.entity.admin.SysUrl;
import com.freestyle.service.admin.ISysUrlService;
import com.freestyle.service.commons.Page;

/**
 * 系统URL资源管理服务测试
 * @author Leo Lien
 * 2016年10月25日 下午7:11:15 创建
 */
@ActiveProfiles("production")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext.xml"})
public class SysUrlServiceTest {
    
    @Autowired
    private ISysUrlService sysUrlService;

    @Test
    public final void testCRUD() {
//        sysUrlService.createUrl("/admin", "管理路径");
        
    }
    
    @Test
    public final void testFindPage() {
//        Page<SysUrl> page = sysUrlService.findPage(1, 10, "/admin");
//        System.out.println(page);
    }
    
}
