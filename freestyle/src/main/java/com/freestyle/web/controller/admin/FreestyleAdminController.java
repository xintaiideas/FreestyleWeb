package com.freestyle.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.core.IAccountService;

/**
 * 后台管理登录http接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/admin/extend")
public class FreestyleAdminController {
	
	@Autowired
	private IAccountService accountService;

	@RequestMapping("main")
	public String index() {
		return "admin/index";
	}
	
	/**
	 * apache shiro
	 * 打开后台管理登录页面
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return "redirect:/index";
		}
		return "admin/login";
	}
	
	/**
	 * 后台管理登录请求处理
	 * @param username	用户名
	 * @param password	密码
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public @ResponseBody ApiResponse login(
			@RequestParam(required=true) String username,
			@RequestParam(required=true) String password,
			@RequestParam(required=false,defaultValue="false") Boolean rememberMe,
			HttpServletRequest request
			) {
		password = accountService.password(password);
		if(!username.equals("admin")) {
			return ApiResponse.buildFailed("用户名或密码错误");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return ApiResponse.OK;
		}catch(AuthenticationException e) {
			return ApiResponse.buildFailed("用户名或密码错误");
		}
	}

}
