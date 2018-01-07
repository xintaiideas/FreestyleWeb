package com.freestyle.web.controller.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.freestyle.domain.entity.ShiroUser;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.entity.core.Album;
import com.freestyle.domain.entity.core.Photo;
import com.freestyle.domain.entity.core.VPhoto;
import com.freestyle.domain.entity.core.VRecentlyPhoto;
import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.admin.ISysFileService;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IAlbumService;
import com.freestyle.service.core.IPhotoService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.ShiroUtil;

/**
 * 照片管理http接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*相册管理服务*/
	@Autowired
	private IAlbumService albumService;
	
	/*照片管理服务*/
	@Autowired
	private IPhotoService photoService;
	
	/*文件管理服务*/
	@Autowired
	private ISysFileService sysFileService;
	
	/*账号管理服务*/
	@Autowired
	private IAccountService accountService;
	
	/**
	 * 获取照片文件
	 * @param id	照片ID
	 * @param response
	 * @return
	 */
	@RequestMapping("{id}")
	public ResponseEntity<byte[]> download(@PathVariable("id") Long id, HttpServletResponse response) {
		try {
			VPhoto photo = photoService.getVPhoto(id);
			File file=new File(sysFileService.getFileFolder() + "/" + photo.getUri());
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType(photo.getMime())); 
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                                              headers, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}catch(IOException e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * 打开上传照片页面
	 * @param albumId	相册ID
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="upload/{albumId}",method=RequestMethod.GET)
	public String upload(@PathVariable("albumId") Long albumId, Model model) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		Account account = this.accountService.getAccount(ShiroUtil.getUserId());
		model.addAttribute("account", account);
		
		model.addAttribute("albumId", albumId);
		return "core/album/upload";
	}
	
	/**
	 * 判断照片是否已经存在
	 * @param md5	文件MD5
	 * @return
	 */
	@RequestMapping("exist-photo")
	public @ResponseBody Boolean existPhoto(
			@RequestParam(required=true) String md5
			) {
		return this.sysFileService.existFile(md5);
	}
	
	/**
	 * 打开最近上传照片页面
	 * @param pageIndex	当前页号
	 * @param pageSize	每页条数
	 * @param model
	 * @return
	 * @throws EntityNotFoundException 
	 */
	@RequestMapping("recently-photo")
	public String pageList(
			@RequestParam(required=true,defaultValue="1") Integer pageIndex,
			@RequestParam(required=true,defaultValue="9") Integer pageSize,
			Model model
			) throws EntityNotFoundException {
		ShiroUser user = ShiroUtil.getUser();
		model.addAttribute("user", user);
		Account account = this.accountService.getAccount(ShiroUtil.getUserId());
		model.addAttribute("account", account);
		
		long accountId = ShiroUtil.getUserId();
		Page<VRecentlyPhoto> photos = this.photoService.recentlyPhoto(accountId,pageIndex,pageSize);
		model.addAttribute("photos", photos);
		return "core/album/recently-photo";
	}
	
	/**
	 * 上传照片
	 * @param albumId	相册ID
	 * @param md5		文件md5
	 * @param name	文件名称
	 * @param type	文件类型
	 * @param size	文件尺寸
	 * @param file	文件数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ResponseEntity uploadPhoto(
			@RequestParam(required=true) Long albumId,
			@RequestParam(required=true) String md5,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String type,
			@RequestParam(required=true) Long size,
			@RequestParam(required=true) MultipartFile file
			) {
		try {
			this.photoService.addPhoto(albumId,name,type,size,md5,file.getInputStream());
			return new ResponseEntity(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} catch (IOException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (EntityExistException e) {
			return new ResponseEntity(HttpStatus.OK);
		}
	}

	/**
	 * 删除照片
	 * @param photoId	照片ID
	 * @return
	 */
	@RequestMapping("delete-photo")
	public @ResponseBody ApiResponse deletePhoto(
			@RequestParam(required=true) Long photoId
			) {
		this.photoService.deletePhoto(photoId);
		return ApiResponse.OK;
	}
	
	/**
	 * 打开修改照片信息页面
	 * @param id
	 * @param model
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("modify-photo/{id}")
	public String modifyPhoto(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
		Photo photo = this.photoService.getPhoto(id);
		model.addAttribute("photo", photo);
		long accountId = ShiroUtil.getUserId();
		List<Album> albums = this.albumService.list(accountId);//列出相册
		model.addAttribute("albums", albums);
		return "core/album/modify-photo";
	}
	
	
	/**
	 * 修改照片信息
	 * @param photoId	照片ID
	 * @param name	照片名称
	 * @param description
	 * @return
	 */
	@RequestMapping("modify-photo")
	public @ResponseBody ApiResponse modifyPhoto(
			@RequestParam(required=true) Long albumId,
			@RequestParam(required=true) Long photoId,
			@RequestParam(required=true) String name,
			@RequestParam(required=false) String description
			) {
		try {
			this.photoService.modifyPhoto(albumId, photoId, name);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			logger.warn("实体未找到",e);
			return ApiResponse.FAILED;
		}
	}
	
}
