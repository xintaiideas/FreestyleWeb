package com.freestyle.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统权限管理控制器
 * @author Leo Lien
 * 2016年10月11日 下午3:38:44 创建
 */
@Controller
@RequestMapping("/admin/sys-perm")
public class SysPermController {

    /**
     * 系统权限管理主页
     * @author Leo Lien
     * 2016年10月11日 下午3:39:17 创建
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "admin/sys-perm-index";
    }
}
