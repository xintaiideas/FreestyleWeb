package com.freestyle.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.util.ShiroUtil;

/**
 * 提供后台账号管理http接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/admin/account")
public class AccountAdminController {
	
	/*账号管理服务*/
	@Autowired
	private IAccountService accountService;

	/**
	 * 打开账号管理列表页面
	 * @param pageIndex	页号
	 * @param pageSize	每页条数
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String index(
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=true,defaultValue="9") Integer pageSize, 
			Model model
			) {
		model.addAttribute("user", ShiroUtil.getUser());
		Page<Account> page = accountService.pageList(pageIndex, pageSize);
		model.addAttribute("page", page);
		return "admin/account";
	}
	
	/**
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping(value="modify-password/{id}",method=RequestMethod.GET)
	public String modifyPassword(@PathVariable("id") Long accountId, Model model) {
		model.addAttribute("accountId", accountId);
		return "admin/modify-password";
	}
	
	/**
	 * 修改密码
	 * @param accountId	用户账号ID
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 * @return
	 */
	@RequestMapping(value="modify-password",method=RequestMethod.POST)
	public @ResponseBody ApiResponse modifyPassword(
			@RequestParam(required=true) Long accountId,
			@RequestParam(required=true) String newPassword
			) {
		this.accountService.modifyPassword(accountId, newPassword);
		return ApiResponse.OK;
	}
	
}
