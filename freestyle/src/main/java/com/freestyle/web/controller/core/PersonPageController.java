package com.freestyle.web.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.freestyle.domain.entity.ShiroUser;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.view.core.MoodVo;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IFriendService;
import com.freestyle.service.core.IMoodService;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.ShiroUtil;

/**
 * 个人主页相关接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/person-page")
public class PersonPageController {
	
	/*心情管理服务*/
	@Autowired
	private IMoodService moodService;
	
	/*账号管理服务*/
	@Autowired
	private IAccountService accountService;
	
	/*好友管理服务*/
	@Autowired
	private IFriendService friendService;
	
	/**
	 * 打开我的主页
	 * @param pageIndex	当前页号
	 * @param pageSize	每页条数
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("my-page")
	public String myPage(
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=true,defaultValue="9") Integer pageSize,
			Model model) throws EntityNotFoundException {
		Account account = this.accountService.getAccount(ShiroUtil.getUserId());
		model.addAttribute("account", account);
		ShiroUser user = ShiroUtil.getUser();
		Page<MoodVo> moods = this.moodService.pageList(user.getUserId(),pageIndex, pageSize);
		model.addAttribute("user", user);
		model.addAttribute("moods", moods);
		return "core/person-page";
	}

	/**
	 * 打开好友主页
	 * @param id	好友ID
	 * @param pageIndex	当前页号
	 * @param pageSize	每页条数
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("friend/{id}")
	public String friendPage(@PathVariable("id") Long id,
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=true,defaultValue="9") Integer pageSize,
			Model model) throws EntityNotFoundException {
		model.addAttribute("user", ShiroUtil.getUser());
		Account account = this.accountService.getAccount(id);
		model.addAttribute("account", account);
		boolean isFriend = this.friendService.isFriend(ShiroUtil.getUserId(),id);
		boolean isHisFriend = this.friendService.isFriend(id,ShiroUtil.getUserId());
		Page<MoodVo> moods = this.moodService.pageList(id,pageIndex, pageSize);
		model.addAttribute("moods", moods);
		model.addAttribute("isFriend", isFriend);
		model.addAttribute("isHisFriend", isHisFriend);
		return "core/person-page";
	}

}
