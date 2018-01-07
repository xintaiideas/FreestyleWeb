package com.freestyle.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freestyle.util.SpringContextHolder;

@Controller
@RequestMapping("/admin/spring")
public class SpringController {
    
    @RequestMapping("")
    public String index() {
        return "admin/spring-index";
    }

    @RequestMapping("refresh")
    public void refresh() {
        SpringContextHolder.refresh();
    }
}
