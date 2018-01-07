package com.freestyle.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台菜单管理控制器
 * @author Leo Lien
 * 2016年10月11日 下午3:34:32 创建
 */
@Controller
@RequestMapping("/admin/sys-menu")
public class SysMenuController {

    /**
     * 后台菜单管理主页
     * @author Leo Lien
     * 2016年10月11日 下午3:36:06 创建
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "admin/sys-menu-index";
    }
}
