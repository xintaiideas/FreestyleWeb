package com.freestyle.service.core.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.freestyle.domain.entity.ShiroUser;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.exception.EntityNotFoundException;

public class AccountRealm extends AuthorizingRealm {
	
	@Autowired
	private IAccountService accountService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return new SimpleAuthorizationInfo();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		Account account;
		try {
			account = accountService.getAccount(token.getUsername());
			ShiroUser shiroUser = new ShiroUser();
			shiroUser.setName(account.getRealname());
			shiroUser.setUserId(account.getId());
			shiroUser.setUsername(account.getUsername());
			return new SimpleAuthenticationInfo(shiroUser, account.getPassword(), getName());
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

}
