package com.freestyle.web.controller.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.entity.ShiroUser;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.entity.core.Friend;
import com.freestyle.domain.entity.core.FriendGroup;
import com.freestyle.domain.view.core.FriendVo;
import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IFriendService;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.ShiroUtil;

/**
 * 好友管理相关接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/friend")
public class FriendController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*好友管理服务*/
	@Autowired
	private IFriendService friendService;
	
	/*账号管理服务*/
	@Autowired
	private IAccountService accountService;
	
	/**
	 * 打开好友的页面
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("")
	public String index(Model model) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		Account account = this.accountService.getAccount(ShiroUtil.getUserId());
		model.addAttribute("account", account);
		
		return "core/friend";
	}
	
	/**
	 * 获取好友分组列表数据
	 * @return
	 */
	@RequestMapping("group-list")
	public @ResponseBody ApiResponse groupList() {
		long accountId = ShiroUtil.getUserId();
		List<FriendGroup> list = this.friendService.getMyFriendGroup(accountId);
		return ApiResponse.buildOK("获取成功").putAttribute("groups", list);
	}
	
	/**
	 * 获取好友列表数据
	 * @param groupId
	 * @param username
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("friend-list")
	public @ResponseBody ApiResponse friendList(
			@RequestParam(required=false) Long groupId,
			@RequestParam(required=false) String username
			) throws EntityNotFoundException {
		long accountId = ShiroUtil.getUserId();
		List<FriendVo> friends = this.friendService.friendList(accountId, groupId, username);
		return ApiResponse.buildOK("获取成功").putAttribute("friends", friends);
	}
	
	/**
	 * 打开添加分组页面
	 * @return
	 */
	@RequestMapping(value="add-group",method=RequestMethod.GET)
	public String addGroup() {
		return "core/friend/add-group";
	}
	
	/**
	 * 添加分组请求处理
	 * @param name
	 * @return
	 */
	@RequestMapping(value="add-group",method=RequestMethod.POST)
	public @ResponseBody ApiResponse addGroup(
			@RequestParam(required=true) String name
			) {
		try {
			long accountId = ShiroUtil.getUserId();
			FriendGroup group = this.friendService.addGroup(accountId, name);
			return ApiResponse.buildOK("创建成功").putAttribute("group", group);
		}catch(Exception e) {
			return ApiResponse.buildFailed("分组已存在");
		}
	}
	
	/**
	 * 打开修改好友分组信息页面
	 * @param groupId	分组ID
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="modify-group/{id}",method=RequestMethod.GET)
	public String editGroup(@PathVariable("id") Long groupId, Model model) throws EntityNotFoundException {
		FriendGroup group = this.friendService.getFriendGroup(groupId);
		model.addAttribute("group", group);
		return "core/friend/modify-group";
	}
	
	/**
	 * 修改分组请求处理
	 * @param groupId
	 * @param name
	 * @return
	 */
	@RequestMapping(value="modify-group",method=RequestMethod.POST)
	public @ResponseBody ApiResponse eidtGroup(
			@RequestParam(required=true) Long groupId,
			@RequestParam(required=true) String name
			) {
		try {
			this.friendService.modifyGroupName(groupId, name);
			return ApiResponse.buildOK("修改成功");
		} catch (EntityNotFoundException e1) {
			return ApiResponse.buildFailed("分组不存在");
		}catch(Exception e) {
			return ApiResponse.buildFailed("分组名已存在"); 
		}
	}
	
	/**
	 * 删除好友分组请求处理
	 * @param groupId	分组ID
	 * @return
	 */
	@RequestMapping(value="delete-group",method=RequestMethod.POST)
	public @ResponseBody ApiResponse deleteGroup(
			@RequestParam(required=true) Long groupId
			) {
		long accountId = ShiroUtil.getUserId();
		this.friendService.deleteGroup(accountId, groupId);
		return ApiResponse.buildOK("删除成功");
	}
	
	/**
	 * 打开添加好友页面
	 * @param accountId	好友的用户ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add-friend/{id}",method=RequestMethod.GET)
	public String addFriend(@PathVariable("id") Long accountId, Model model) {
		model.addAttribute("accountId", accountId);
		long myId = ShiroUtil.getUserId();
		List<FriendGroup> groups = this.friendService.getMyFriendGroup(myId);
		model.addAttribute("groups", groups);
		return "core/friend/add-friend";
	}

	/**
	 * 添加好友请求处理
	 * @param groupId	分组ID，可以为null，为null表示默认分组
	 * @param friendId	好友的用户ID
	 * @param remark	好友备注名称，可以为null
	 * @return
	 */
	@RequestMapping("add-friend")
	public @ResponseBody ApiResponse addFriend(
			@RequestParam(required=false) Long groupId,
			@RequestParam(required=true) Long accountId,
			@RequestParam(required=false) String remark
			) {
		try {
			long myId = ShiroUtil.getUserId();
			this.friendService.addFriend(myId,groupId, accountId, remark);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			logger.warn("实体不存在",e);
			return ApiResponse.FAILED;
		}
	}
	
	/**
	 * 删除好友
	 * @param friendId	好友ID
	 * @return
	 */
	@RequestMapping("delete-friend")
	public @ResponseBody ApiResponse deleteFriend(
			@RequestParam(required=true) Long friendId
			) {
		this.friendService.deleteFriend(friendId);
		return ApiResponse.OK;
	}
	
	/**
	 * 移动好友到指定分组
	 * @param groupId	指定分组ID
	 * @param friendId	好友ID
	 * @return
	 */
	/*@RequestMapping("move-to-group")
	public @ResponseBody ApiResponse moveToGroup(
			@RequestParam(required=true) Long groupId,
			@RequestParam(required=true) Long friendId
			) {
		try {
			this.friendService.moveToGroup(groupId, friendId);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			logger.warn("实体未找到",e);
			return ApiResponse.FAILED;
		}
	}*/
	
	/**
	 * 打开修改好友信息页面
	 * @param friendId	好友ID
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="modify-friend/{id}",method=RequestMethod.GET)
	public String modifyFriend(@PathVariable("id") Long friendId,Model model) throws EntityNotFoundException {
		Friend friend = this.friendService.getFriend(friendId);
		model.addAttribute("friend", friend);
		long myId = ShiroUtil.getUserId();
		List<FriendGroup> groups = this.friendService.getMyFriendGroup(myId);
		model.addAttribute("groups", groups);
		return "core/friend/modify-friend";
	}
	
	/**
	 * 修改好友备注
	 * @param friendId	好友ID
	 * @param groupId	分组ID
	 * @param remark	好友备注
	 * @return
	 */
	@RequestMapping(value="modify-friend",method=RequestMethod.POST)
	public @ResponseBody ApiResponse modifyRemark(
			@RequestParam(required=true) Long friendId,
			@RequestParam(required=true) Long groupId,
			@RequestParam(required=false) String remark
			) {
		try {
			this.friendService.modifyRemark(friendId, groupId,remark);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			logger.warn("实体未找到",e);
			return ApiResponse.FAILED;
		}
		
	}
	
}
