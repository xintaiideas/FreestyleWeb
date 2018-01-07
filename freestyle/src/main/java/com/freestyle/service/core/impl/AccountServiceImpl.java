package com.freestyle.service.core.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.entity.core.AccountExample;
import com.freestyle.mapper.core.AccountMapper;
import com.freestyle.service.admin.IEmailService;
import com.freestyle.service.admin.ISysFileService;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;
import com.freestyle.util.DigestUtil;

@Service
@Transactional(readOnly=true)
public class AccountServiceImpl implements IAccountService {
	
	public static final String SALT_FREESTYLE = "freestyle";
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private ISysFileService sysFileService;

	@Override
	@Transactional(readOnly=false)
	public void modifyPassword(Long accountId, String oldPassword,
			String newPassword) throws EntityNotFoundException {
		oldPassword = this.password(oldPassword);
		newPassword = this.password(newPassword);
		Account account = this.getAccount(accountId);
		//	TODO 密码摘要后进行对比
		if(account.getPassword().equals(oldPassword)) {
			account.setPassword(newPassword);
			this.accountMapper.updateByPrimaryKeySelective(account);
		}else {
			throw new IllegalArgumentException("旧密码输入错误！");
		}
	}

	@Override
	public Account getAccount(long accountId) throws EntityNotFoundException {
		Account account = this.accountMapper.selectByPrimaryKey(accountId);
		if(account == null) {
			throw new EntityNotFoundException("账号不存在，ID：" + accountId, Account.class);
		}
		return account;
	}

	@Override
	public void checkExistAccount(long accountId)
			throws EntityNotFoundException {
		this.getAccount(accountId);
	}

	@Override
	@Transactional(readOnly=false)
	public void register(String username, String password)
			throws EntityExistException {
		password = this.password(password);
		if(existAccount(username)) {
			throw new EntityExistException("账号已存在,用户名：" + username,Account.class);
		}
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setRealname(username);
		this.accountMapper.insert(account);
	}

	@Override
	public Account getAccount(String account) throws EntityNotFoundException {
		AccountExample example = new AccountExample();
		example.or().andUsernameEqualTo(account);
		List<Account> accounts = this.accountMapper.selectByExample(example);
		if(accounts.isEmpty()) {
			throw new EntityNotFoundException("账号不存在，用户名：" + account, Account.class);
		}
		return accounts.get(0);
	}

	@Override
	public boolean existAccount(String account) {
		try {
			getAccount(account);
			return true;
		} catch (EntityNotFoundException e) {
			return false;
		}
	}

	/*@Override
	public void sendValidEmail(String to) {
		emailService.send("您好", "您好," + to, to);
	}*/

	@Override
	@Transactional(readOnly=false)
	public void modifyPersonInfo(long accountId, String name, String email,
			String tel, Long fileId) throws EntityNotFoundException {
		Account account = this.getAccount(accountId);
		account.setRealname(name);
		account.setEmail(email);
		account.setTel(tel);
		if(fileId != null) {
			account.setHeadImgId(fileId);
			//	缩略图
			account.setHeadImgThumbId(fileId);
		}
		this.accountMapper.updateByPrimaryKeySelective(account);
	}

	@Override
	@Transactional(readOnly=false)
	public void setHeadImage(long accountId, String md5, String name, String type, Long size,
			InputStream in) throws IOException, EntityExistException, EntityNotFoundException {
		long fileId = this.sysFileService.saveFile(md5, name, type, size, in);
		if(fileId != -1) {
			Account account = this.getAccount(accountId);
			account.setHeadImgId(fileId);
			account.setHeadImgThumbId(fileId);
			this.accountMapper.updateByPrimaryKeySelective(account);
		}
	}

	@Override
	public SysFile getHeadImage(long accountId) throws EntityNotFoundException {
		Account account = this.getAccount(accountId);
		Long fileId = account.getHeadImgId();
		if(fileId == null) {
			throw new IllegalArgumentException("未设置头像");
		}
		return this.sysFileService.getFile(fileId);
	}

	@Override
	public Page<Account> pageList(Integer pageIndex, Integer pageSize) {
		AccountExample example = new AccountExample();
		int total = this.accountMapper.countByExample(example);
		example.setOffset((pageIndex - 1) * pageSize);
		example.setLimit(pageSize);
		List<Account> list = this.accountMapper.selectByExample(example);
		Page<Account> page = new Page<Account>(total,list,pageIndex,pageSize);
		return page;
	}

	@Override
	@Transactional(readOnly=false)
	public void modifyPassword(Long accountId, String newPassword) {
		newPassword = this.password(newPassword);
		Account account = this.accountMapper.selectByPrimaryKey(accountId);
		account.setPassword(newPassword);
		this.accountMapper.updateByPrimaryKey(account);
	}

	@Override
	public String password(String pwd) {
		return DigestUtil.md5(pwd + SALT_FREESTYLE);
	}
	
}
