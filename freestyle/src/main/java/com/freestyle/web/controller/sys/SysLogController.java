package com.freestyle.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统日志管理控制器
 * @author Leo Lien
 * 2016年10月11日 下午3:42:34 创建
 */
@Controller
@RequestMapping("/admin/sys-log")
public class SysLogController {

    /**
     * 系统日志管理主页
     * @author Leo Lien
     * 2016年10月11日 下午3:42:57 创建
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "admin/sys-log-index";
    }
}
