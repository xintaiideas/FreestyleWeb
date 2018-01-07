package com.freestyle.web.controller.core;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.admin.ISysFileService;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.ShiroUtil;

/**
 * 该类的作用是为web前端提供账号相关的http接口
 * 例如注册、登录、修改密码、修改个人信息等
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("")
public class AccountController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 账号管理服务
	 */
	@Autowired
	private IAccountService accountService;
	
	/**
	 * 文件管理服务
	 */
	@Autowired
	private ISysFileService sysFileService;
	
	/**
	 * 用户访问这个方法可以打开注册页面
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register() {
		return "core/register";
	}
	
	/**
	 * 用户点击注册按钮就会把注册的信息提交到这个方法进行处理
	 * @param username	用户名
	 * @param password	密码
	 * @param captchca 验证码
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public @ResponseBody ApiResponse register(
			HttpSession session,
			@RequestParam(required=true) String username,
			@RequestParam(required=true) String password,
			@RequestParam(required=true) String captchca
			) {
		if(!checkCaptcha(session, captchca)) {
			return ApiResponse.buildFailed("验证码输入错误");
		}
        try {
			accountService.register(username,password);
			return ApiResponse.OK;
		} catch (EntityExistException e) {
			logger.debug("实体已存在",e);
			return ApiResponse.buildFailed("用户名已存在");
		}
	}
	
	/**
	 * 检查用户名是否存在，这个用在注册的时候检查用户名
	 * @param username	用户名
	 * @return
	 */
	@RequestMapping("check-username")
	public @ResponseBody Boolean checkUsername(@RequestParam(required=true) String username) { 
		return !accountService.existAccount(username);
	}
	
	/**
	 * 检查验证码，用于检查验证码是否输入正确
	 * @param session
	 * @param captchca
	 * @return
	 */
	@RequestMapping("check-captchca")
	public @ResponseBody Boolean checkCaptchca(HttpSession session,@RequestParam(required=true) String captchca) {
		return checkCaptcha(session, captchca);
	}
	
	private boolean checkCaptcha(HttpSession session,String captcha) {
		String kaptchaExpected = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
        //校验验证码是否正确  
        if (!captcha.equals(kaptchaExpected)) {
            return false; 
        }
        return true;
	}
	
	/**
	 * 发送注册验证邮件
	 * @param to
	 * @return
	 */
	/*@RequestMapping("send-valid-email")
	public @ResponseBody ApiResponse sendValidEmail(
			@RequestParam(required=true) String to
			) {
		this.accountService.sendValidEmail(to);
		return ApiResponse.OK;
	}*/

	/**
	 * apache shiro
	 * 访问这个方法可以打开登录页面
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return "redirect:/index";
		}
		return "core/login";
	}
	
	/**
	 * 用户点击登录按钮会把账号和密码发到这个方法进行登录处理
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
		password = this.accountService.password(password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return ApiResponse.OK;
		}catch(AuthenticationException e) {
			return ApiResponse.buildFailed("用户名或密码错误");
		}
	}
	
	/*
	@RequestMapping("forget-password")
	public String forgetPassword() {
		return "core/forget-password";
	}*/
	
	/**
	 * 打开修改密码页面
	 * @return
	 */
	@RequestMapping(value="modify-password",method=RequestMethod.GET)
	public String modifyPassword() {
		return "core/account/modify-password";
	}
	
	/**
	 * 点击修改密码按钮后会把新密码和旧密码发送到这个方法进行处理
	 * @param accountId	用户账号ID
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 * @return
	 */
	@RequestMapping(value="modify-password",method=RequestMethod.POST)
	public @ResponseBody ApiResponse modifyPassword(
			@RequestParam(required=true) String oldPassword,
			@RequestParam(required=true) String newPassword
			) {
		try {
			long accountId = ShiroUtil.getUserId();
			this.accountService.modifyPassword(accountId, oldPassword, newPassword);
			return ApiResponse.OK;
		} catch(IllegalArgumentException e) {
			return ApiResponse.buildFailed(e.getMessage());
		} catch (EntityNotFoundException e) {
			logger.warn("实体不存在",e);
			return ApiResponse.FAILED;
		}
		
	}
	
	/**
	 * 上传头像的处理方法
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="upload-head-image",method=RequestMethod.POST)
	public ResponseEntity uploadHeadImage(
			@RequestParam(required=true) String md5,
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String type,
			@RequestParam(required=true) Long size,
			@RequestParam(required=true) MultipartFile file
			) {
		long accountId = ShiroUtil.getUserId();
		try {
			this.accountService.setHeadImage(accountId, md5,name,type,size, file.getInputStream());
			return new ResponseEntity(HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (EntityExistException e) {

		} catch (EntityNotFoundException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * 打开修改个人信息页面
	 * @return
	 * @throws EntityNotFoundException 
	 */
	@RequestMapping(value="modify-person-info",method=RequestMethod.GET)
	public String personInfo(Model model) throws EntityNotFoundException {
		long accountId = ShiroUtil.getUserId();
		Account account = this.accountService.getAccount(accountId);
		model.addAttribute("account", account);
		return "core/account/modify-person-info";
	}
	
	/**
	 * 修改个人信息的数据会发送到这个页面进行处理
	 * @return
	 */
	@RequestMapping(value="modify-person-info",method=RequestMethod.POST)
	public @ResponseBody ApiResponse modifyPersonInfo(
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String email,
			@RequestParam(required=true) String tel,
			@RequestParam(required=false) Long fileId	//多余
			) {
		long accountId = ShiroUtil.getUserId();
		try {
			this.accountService.modifyPersonInfo(accountId,name,email,tel,fileId);
			return ApiResponse.OK;
		} catch (EntityNotFoundException e) {
			logger.error("修改账号信息失败",e);
			return ApiResponse.FAILED;
		}
	}
	
	/**
	 * 获取当前账号头像图片文件
	 * @param response
	 * @throws IOException
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("head-image")
	public void download(HttpServletResponse response) throws IOException, EntityNotFoundException {
		long accountId = ShiroUtil.getUserId();
		SysFile sysFile = this.accountService.getHeadImage(accountId);
		File file=new File(sysFileService.getFileFolder() + "/" + sysFile.getUri());
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(sysFile.getMime())); 
        FileUtils.copyFile(file, response.getOutputStream());
	}
	
	/**
	 * 获取指定账号的头像图片文件
	 * @param accountId
	 * @param response
	 * @throws IOException
	 * @throws EntityNotFoundException
	 */
	@RequestMapping("head-image/{id}")
	public void download(@PathVariable("id") Long accountId, HttpServletResponse response) throws IOException, EntityNotFoundException {
		SysFile sysFile = this.accountService.getHeadImage(accountId);
		File file=new File(sysFileService.getFileFolder() + "/" + sysFile.getUri());
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(sysFile.getMime())); 
        FileUtils.copyFile(file, response.getOutputStream());
	}
}
