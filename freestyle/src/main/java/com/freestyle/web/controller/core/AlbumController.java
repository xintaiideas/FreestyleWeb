package com.freestyle.web.controller.core;

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
import com.freestyle.domain.entity.core.Album;
import com.freestyle.domain.entity.core.VAlbum;
import com.freestyle.domain.entity.core.VPhoto;
import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IAlbumService;
import com.freestyle.service.core.IPhotoService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.ShiroUtil;

/**
 * 这个类的作用是为web前端提供相册管理http接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*相册管理服务*/
	@Autowired
	private IAlbumService albumService; 
	
	/*照片管理服务*/
	@Autowired
	private IPhotoService photoService;
	
	/*账号管理服务*/
	@Autowired
	private IAccountService accountService;
	
	/**
	 * 相册列表页面，我的相册菜单点击下去显示的页面
	 * @param pageIndex 当前是哪一页
	 * @param pageSize 当前页面显示多少条
	 * @return
	 * @throws EntityNotFoundException 
	 * 
	 * @RequestParam 注解，用来注释请求的参数，required=true表示这个参数是必须的
	 * defaultValue="1" 这个表示默认值为1，比如pageIndex默认值为1，如果页面访问没有这个参数，那他就默认第一页
	 * Model 是spring mvc提供给我们对页面传递参数的对象
	 * 为什么返回值是字符串类型"core/album/album" 该字符串表示返回一个jsp页面，这个字符串是一个jsp文件的路径，core文件夹下面的album文件夹下的album.jsp文件
	 * 为什么没有写.jsp，因为spring mvc配置里默认设置了前缀和后缀，jsp文件位置在哪：在/freestyle/src/main/webapp/WEB-INF/views/core/album文件夹下
	 * @RequestMapping("")是什么：这是用来注释当前方法访问url，要访问这个方法的路径 = 本类上的@RequestMapping("/album") + 本方法上的@RequestMapping("")
	 */
	@RequestMapping("")
	public String index(
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=true,defaultValue="9") Integer pageSize, 
			Model model
			) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		Account account = this.accountService.getAccount(ShiroUtil.getUserId());
		model.addAttribute("account", account);
		
		Long accountId = ShiroUtil.getUserId();
		Page<VAlbum> albums = albumService.pageList(accountId, pageIndex, pageSize);
		model.addAttribute("albums", albums);
		return "core/album/album";
	}
	
	/**
	 * 查看好友的相册列表	
	 * @param id	好友的用户ID
	 * @param pageIndex	当前页号
	 * @param pageSize	每页条数
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("friend/{id}")
	public String index(
			@PathVariable("id") Long id,
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=true,defaultValue="9") Integer pageSize, 
			Model model
			) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		Account account = this.accountService.getAccount(id);
		model.addAttribute("account", account);
		
		Page<VAlbum> albums = albumService.pageList(id, pageIndex, pageSize);
		model.addAttribute("albums", albums);
		return "core/album/album";
	}
	
	/**
	 * 相册照片列表页面,点击具体相册，将显示本页面
	 * @param id	相册ID
	 * @param model
	 * @return
	 * @throws EntityNotFoundException 
	 * 
	 * 访问路径是 /album/{id}  id是变量
	 */
	@RequestMapping("{id}")
	public String album(
			@PathVariable("id") Long id, 
			@RequestParam(required=true,defaultValue="1") Integer pageIndex,
			@RequestParam(required=true,defaultValue="9") Integer pageSize,
			Model model) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		
		Album album = this.albumService.getAlbum(id);
		Account account = this.accountService.getAccount(album.getAccountId());
		model.addAttribute("account", account);
		Page<VPhoto> photos = this.photoService.pageList(id, pageIndex, pageSize);
		model.addAttribute("album", album);
		model.addAttribute("photos", photos);
		return "core/album/photo";
	}
	
	/**
	 * 打开创建相册页面
	 * @return
	 * 点击创建相册所显示的页面，使用get请求
	 */
	@RequestMapping(value="create-album",method=RequestMethod.GET)
	public String createAlbum() {
		return "core/album/create-album";
	}
	
	
	
	/**
	 * 设置封面
	 * @param albumId	相册ID
	 * @param photoId	照片ID
	 * @return
	 * @ResponseBody ApiResponse 表示 响应体是一个ApiResponse对象类型，响应到浏览器上会转换成json类型
	 */
	@RequestMapping("cover")
	public @ResponseBody ApiResponse cover(
			@RequestParam(required=true) Long albumId,
			@RequestParam(required=true) Long photoId
			) {
		try {
			//	设置封面
			this.albumService.cover(albumId,photoId);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			return ApiResponse.FAILED;
		}
		
	}

	/**
	 * 创建相册
	 * @param name	相册名称
	 * @param description	相册描述
	 * @return
	 */
	@RequestMapping(value="create-album",method=RequestMethod.POST)
	public @ResponseBody ApiResponse createAlbum(
			@RequestParam(required=true) String name,
			@RequestParam(required=false) String description
			) {
		long accountId = ShiroUtil.getUserId();
		try {
			this.albumService.createAlbum(accountId, name, description);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			return ApiResponse.buildNotFound("用户不存在");
		} catch (EntityExistException e) {
			return ApiResponse.buildIllegalArgument("相册名称已存在");
		}
		
	}
	
	/**
	 * 删除相册
	 * @param albumId	相册ID
	 * @return
	 */
	@RequestMapping("delete-album")
	public @ResponseBody ApiResponse deleteAlbum(
			@RequestParam(required=true) Long albumId
			) {
		this.albumService.deleteAlbum(albumId);
		return ApiResponse.OK;
	}
	
	/**
	 * 点击编辑相册所显示的页面
	 * 打开编辑相册页面
	 * @param id	相册ID
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="modify-album/{id}",method=RequestMethod.GET)
	public String modifyAlbum(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
		Album album = this.albumService.getAlbum(id);
		model.addAttribute("album", album);
		return "core/album/modify-album";
	}
	
	/**
	 * 处理修改相册信息的请求
	 * @param name	相册名称
	 * @param description	相册描述
	 * @return
	 */
	@RequestMapping("modify-album")
	public @ResponseBody ApiResponse modifyAlbum(
			@RequestParam(required=true) Long albumId,
			@RequestParam(required=true) String name,
			@RequestParam(required=false) String description
			) {		
		try {
			this.albumService.modifyAlbum(albumId, name, description);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			logger.warn("实体未找到",e);
			return ApiResponse.FAILED;
		}
	}
}
