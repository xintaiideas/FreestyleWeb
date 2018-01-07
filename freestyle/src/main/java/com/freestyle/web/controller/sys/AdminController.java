package com.freestyle.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freestyle.service.admin.ISystemPropertyService;

/**
 * 管理后台入口控制器
 * @author Leo Lien
 * 2016年10月19日 上午12:07:08 创建
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private ISystemPropertyService systemPropertyService;

    /*管理后台登录页面*/
    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginPage(Model model) {
        String appName = systemPropertyService.getAppName();
        String copyRight = systemPropertyService.getCopyRight();
        String appVersion = systemPropertyService.getAppVersion();
        String company = systemPropertyService.getCompanyName();
        model.addAttribute("appName", appName);
        model.addAttribute("copyRight", copyRight);
        model.addAttribute("appVersion", appVersion);
        model.addAttribute("company", company);
        return "admin/login";
    }
    
    /*管理后台登录入口*/
    @RequestMapping(value="login", method=RequestMethod.POST)
    public void login() {
        
    }
    
    /*管理后台主页*/
    @RequestMapping(value="")
    public String index(Model model) {
        String appName = systemPropertyService.getAppName();
        String copyRight = systemPropertyService.getCopyRight();
        String appVersion = systemPropertyService.getAppVersion();
        String company = systemPropertyService.getCompanyName();
        model.addAttribute("appName", appName);
        model.addAttribute("copyRight", copyRight);
        model.addAttribute("appVersion", appVersion);
        model.addAttribute("company", company);
        return "admin/index";
    }

}
