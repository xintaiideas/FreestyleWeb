package com.freestyle.web.controller.core;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.admin.ISysFileService;
import com.freestyle.service.core.IMoodService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.ShiroUtil;

/**
 * 心情管理相关接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/mood")
public class MoodController {
	
	/*心情管理服务*/
	@Autowired
	private IMoodService moodService;
	
	/*文件管理服务*/
	@Autowired
	private ISysFileService sysFileService;

	/**
	 * 发表心情
	 * @param content	心情内容
	 * @param type		心情类型
	 * @param moodFiles	心情附带媒体文件ID数组
	 * @return
	 */
	@RequestMapping("publish")
	public @ResponseBody ApiResponse publish(
			@RequestParam(required=true) String content,
			@RequestParam(required=true) String type,
			@RequestParam(name="moodFiles[]",required=false) Long[] moodFiles
					) {
		long accountId = ShiroUtil.getUserId();
		this.moodService.publish(accountId,content, type, moodFiles);
		return ApiResponse.OK;
	}
	
	/**
	 * 获取心情附带的文件，包括图片、视频
	 * @param moodFileId	心情文件ID
	 * @param response
	 * @throws IOException
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("mood-file/{id}")
	public void download(@PathVariable("id") Long moodFileId, HttpServletResponse response) throws IOException, EntityNotFoundException {
		SysFile moodFile = this.moodService.getMoodFile(moodFileId);
		File file=new File(sysFileService.getFileFolder() + "/" + moodFile.getUri());
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(moodFile.getMime())); 
        FileUtils.copyFile(file, response.getOutputStream());
	}
	
	/**
	 * 上传心情文件，包括图片、视频
	 * @param md5	文件md5值
	 * @param name	文件名称
	 * @param type	文件类型
	 * @param size	文件尺寸
	 * @param file	文件数据
	 * @return
	 */
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ResponseEntity<ApiResponse> uploadPhoto(
			@RequestParam(required=true) String md5,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String type,
			@RequestParam(required=true) Long size,
			@RequestParam(required=true) MultipartFile file
			) {
		long fileId = -1;
		try {
			fileId = this.moodService.uploadMoodFile(name,type,size,md5,file.getInputStream());
			return new ResponseEntity<ApiResponse>(ApiResponse.buildOK("成功").putAttribute("fileId", fileId),HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<ApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (EntityExistException e) {
			return new ResponseEntity<ApiResponse>(ApiResponse.buildOK("成功").putAttribute("fileId", fileId),HttpStatus.OK);
		}
	}
	
	/**
	 * 删除心情
	 * @param moodId	心情ID
	 * @return
	 */
	@RequestMapping(value="delete-mood",method=RequestMethod.POST)
	public @ResponseBody ApiResponse deleteMood(
			@RequestParam(required=true) Long moodId
			) {
		this.moodService.deleteMood(moodId);
		return ApiResponse.OK;
	}
	
	
}
