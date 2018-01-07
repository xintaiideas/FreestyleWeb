package com.freestyle.service.core;

import java.io.IOException;
import java.io.InputStream;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.service.commons.Page;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;

/**
 * 账号管理服务
 * @author 大爱阳哥
 *
 */
public interface IAccountService {

	/**
	 * 修改密码
	 * @param accountId	用户账号ID
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 */
	void modifyPassword(Long accountId, String oldPassword, String newPassword) throws EntityNotFoundException;

	/**
	 * 获取用户账号信息
	 * @param accountId	账号ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	Account getAccount(long accountId) throws EntityNotFoundException;
	
	/**
	 * 检查用户账号是否存在
	 * @param accountId	账号ID
	 * @throws EntityNotFoundException
	 */
	void checkExistAccount(long accountId) throws EntityNotFoundException;
	
	/**
	 * 根据账号名称获取账户信息
	 * @param account	用户名
	 * @return
	 * @throws EntityNotFoundException
	 */
	Account getAccount(String account) throws EntityNotFoundException;
	
	/**
	 * 根据账号名称判断账号是否存在
	 * @param account	用户名
	 * @return
	 */
	boolean existAccount(String account);

	/**
	 * 注册账户
	 * @param username	用户名
	 * @param password	密码
	 * @throws EntityExistException
	 */
	void register(String username, String password) throws EntityExistException;

	/**
	 * 发送注册验证邮件
	 * @param to
	 */
	/*void sendValidEmail(String to);*/

	/**
	 * 修改个人信息
	 * @param accountId	账号ID
	 * @param name	真实姓名
	 * @param email	邮箱
	 * @param tel	电话
	 * @param fileId	头像文件ID
	 * @throws EntityNotFoundException
	 */
	void modifyPersonInfo(long accountId, String name, String email,
			String tel, Long fileId) throws EntityNotFoundException;

	/**
	 * 设置头像
	 * @param accountId	账号ID
	 * @param md5	头像文件MD5
	 * @param name	头像文件名称
	 * @param type	文件类型
	 * @param size	文件尺寸
	 * @param inputStream	文件输入流
	 * @throws IOException
	 * @throws EntityExistException
	 * @throws EntityNotFoundException
	 */
	void setHeadImage(long accountId, String md5, String name, String type, Long size,
			InputStream inputStream) throws IOException, EntityExistException, EntityNotFoundException;

	/**
	 * 获取头像文件
	 * @param accountId	账号ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	SysFile getHeadImage(long accountId) throws EntityNotFoundException;

	/**
	 * 账号分页列表数据
	 * @param pageIndex	页号
	 * @param pageSize	每页条数
	 * @return
	 */
	Page<Account> pageList(Integer pageIndex, Integer pageSize);

	/**
	 * 修改密码
	 * @param accountId	账号ID
	 * @param newPassword	新密码
	 */
	void modifyPassword(Long accountId, String newPassword);
	
	/**
	 * 对密码进行加密
	 * @param pwd
	 * @return
	 */
	String password(String pwd);
}
