package com.freestyle.web.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.freestyle.domain.entity.ShiroUser;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.view.core.HotPhotoVo;
import com.freestyle.domain.view.core.MoodVo;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IAlbumService;
import com.freestyle.service.core.IMoodService;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.ShiroUtil;

/**
 * 主页和与我相关页面http接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("")
public class IndexController {
	
	/*心情管理服务*/
	@Autowired
	private IMoodService moodService;
	
	/*账号管理服务*/
	@Autowired
	private IAccountService accountService;
	
	/*相册管理服务*/
	@Autowired
	private IAlbumService albumService;

	/**
	 * 打开首页
	 * @param pageIndex	当前页号
	 * @param pageSize	每页条数
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("index")
	public String index(
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=false,defaultValue="9") Integer pageSize, 
			Model model) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		Account account = this.accountService.getAccount(ShiroUtil.getUserId());
		model.addAttribute("account", account);
		
		Page<MoodVo> moods = this.moodService.pageList(null,pageIndex,pageSize);
		model.addAttribute("moods", moods);
		HotPhotoVo vo = this.albumService.getHotPhoto();
		model.addAttribute("hotPhoto", vo);
		return "core/index";
	}
	
	/**
	 * 打开与我相关页面
	 * @param pageIndex	当前页号	
	 * @param pageSize	每页条数
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("about-me")
	public String aboutMe(
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=true,defaultValue="9") Integer pageSize, 
			Model model) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		Account account = this.accountService.getAccount(ShiroUtil.getUserId());
		model.addAttribute("account", account);
		
		Page<MoodVo> moods = this.moodService.aboutMe(user.getUserId(),pageIndex,pageSize);
		model.addAttribute("moods", moods);
		HotPhotoVo vo = this.albumService.getHotPhoto();
		model.addAttribute("hotPhoto", vo);
		return "core/about-me";
	}
	
}
