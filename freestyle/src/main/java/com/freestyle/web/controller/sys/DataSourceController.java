package com.freestyle.web.controller.sys;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.domain.view.sys.DataSourceConfig;
import com.freestyle.service.admin.IDataSourceService;
import com.freestyle.util.ValidationUtil;

/**
 * 数据源管理控制器
 * @author Leo Lien
 * 2016年10月18日 下午9:48:39 创建
 */
@Controller
@RequestMapping("/admin/data-source")
public class DataSourceController {
    
    @Autowired
    private IDataSourceService dataSourceService;

    /**
     * 数据源管理主页
     * @author Leo Lien
     * 2016年10月18日 下午9:48:33 创建
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "admin/data-source-index";
    }
    
    /**
     * 配置数据源
     * @author Leo Lien
     * 2016年10月18日 下午9:56:45 创建
     * @param config    配置对象
     */
    @RequestMapping("configure")
    public @ResponseBody ApiResponse configure(@Valid DataSourceConfig config, BindingResult bindingResult) {
        /* 验证参数 start */
        ApiResponse resp = new ApiResponse();
        if(ValidationUtil.validate(bindingResult, resp)) {
            return resp;
        }
        /* 验证参数 end */
        
        try {
            dataSourceService.configure(config);
            return ApiResponse.OK;
        }catch(IllegalArgumentException e) {
            //  错误参数
            e.getMessage();
            return ApiResponse.ILLEGAL_ARGUMENT;
        }catch(IllegalStateException e) {
            //  当前数据连接状态不允许修改配置
            e.getMessage();
            return ApiResponse.FAILED;
        }
    }
}
