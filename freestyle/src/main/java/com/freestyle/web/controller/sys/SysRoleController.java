package com.freestyle.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统角色管理控制器
 * @author Leo Lien
 * 2016年10月11日 下午3:40:19 创建
 */
@Controller
@RequestMapping("/admin/sys-role")
public class SysRoleController {

    /**
     * 系统角色管理主页
     * @author Leo Lien
     * 2016年10月11日 下午3:40:07 创建
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "admin/sys-role-index";
    }
}
