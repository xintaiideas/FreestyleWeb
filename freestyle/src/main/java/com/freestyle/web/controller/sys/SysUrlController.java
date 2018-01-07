package com.freestyle.web.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.entity.admin.SysUrl;
import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.admin.ISysUrlService;
import com.freestyle.service.commons.Page;

/**
 * 系统URL资源管理控制器
 * @author Leo Lien
 * 2016年10月11日 下午3:37:24 创建
 */
@Controller
@RequestMapping("/admin/sys-url")
public class SysUrlController {
    
    @Autowired
    private ISysUrlService sysUrlService;

    /**
     * 系统URL资源管理主页
     * @author Leo Lien
     * 2016年10月11日 下午3:37:50 创建
     * @return
     */
    @RequestMapping("")
    public String index(
            @RequestParam(defaultValue = "1") int pageIndex, 
            @RequestParam(defaultValue = "10") int rows,
            @RequestParam(required=false) String url, 
            Model model) {
        Page<SysUrl> page = sysUrlService.findPage(pageIndex, rows, url);
        model.addAttribute("page", page);
        model.addAttribute("url", url);
        return "admin/sys-url-index";
    }
    
    /**
     * 创建系统URL资源
     * @author Leo Lien
     * 2016年10月25日 下午9:27:45 创建
     * @param url   系统URL资源
     * @param remark    备注
     * @return
     */
    @RequestMapping("create")
    public @ResponseBody ApiResponse create(
            @RequestParam(required=true) String url, String remark
            ) {
        sysUrlService.createUrl(url, remark);
        return ApiResponse.OK;
    }
    
    /**
     * 更新系统URL资源
     * @author Leo Lien
     * 2016年10月25日 下午7:54:05 创建
     * @param url   系统URL资源
     * @param remark    备注
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody ApiResponse update(
            @RequestParam(required=true) Long id,
            @RequestParam(required=true) String url, String remark) {
        try {
            sysUrlService.update(id, url, remark);
            return ApiResponse.OK;
        }catch(Exception e) {
            return ApiResponse.NOT_FOUND;
        }
    }
    
    /**
     * 删除系统URL资源接口
     * @author Leo Lien
     * 2016年10月25日 下午7:52:10 创建
     * @param id    URL ID
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse delete(@PathVariable("id") Long id) {
        sysUrlService.delete(id);
        return ApiResponse.OK;
    }
}
