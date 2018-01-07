package com.freestyle.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统用户管理控制器
 * @author Leo Lien
 * 2016年10月18日 下午11:07:24 创建
 */
@Controller
@RequestMapping("/admin/sys-user")
public class SysUserController {

    /**
     * 系统用户管理主页
     * @author Leo Lien
     * 2016年10月18日 下午11:07:40 创建
     * @return
     */
    public String index() {
        return "admin/sys-user-index";
    }
    
}
