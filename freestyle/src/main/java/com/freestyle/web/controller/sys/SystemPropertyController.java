package com.freestyle.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.admin.ISystemPropertyService;

/**
 * 系统配置管理控制器
 * @author Leo Lien
 * 2016年10月11日 下午3:31:37 创建
 */
@Controller
@RequestMapping("/admin/sys-property")
public class SystemPropertyController {
    
    @Autowired
    private ISystemPropertyService systemPropertyService;

    /**
     * 系统配置管理主页
     * @author Leo Lien
     * 2016年10月11日 下午3:32:03 创建
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "admin/system-property-index";
    }
    
    /**
     * 设置系统属性
     * @author Leo Lien
     * 2016年10月19日 下午9:04:56 创建
     * @param name
     * @param value
     * @return
     */
    @RequestMapping("set-property")
    public @ResponseBody ApiResponse setProperty(
            @RequestParam(required=true) String oldName, 
            @RequestParam(required=true) String newName, 
            @RequestParam(required=true) String value) {
        this.systemPropertyService.setProperty(oldName, newName, value);
        return ApiResponse.OK;
    }
    
    /**
     * 删除系统属性
     * @author Leo Lien
     * 2016年10月19日 下午9:11:07 创建
     * @param name
     * @return
     */
    @RequestMapping("delete-property")
    public @ResponseBody ApiResponse deleteProperty(
            @RequestParam(required=true) String name
            ) {
        this.systemPropertyService.deleteProperty(name);
        return ApiResponse.OK;
    }
}
