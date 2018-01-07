package com.freestyle.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统任务管理控制器
 * @author Leo Lien
 * 2016年10月11日 下午3:41:45 创建
 */
@Controller
@RequestMapping("/admin/sys-task")
public class SysTaskController {

    /**
     * 系统任务管理主页
     * @author Leo Lien
     * 2016年10月11日 下午3:41:32 创建
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "admin/sys-task-index";
    }
}
