package com.freestyle.util;

import org.apache.shiro.SecurityUtils;

import com.freestyle.domain.entity.ShiroUser;

public class ShiroUtil {

	public static Long getUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.getUserId();
	}
	
	public static String getUsername() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.getUsername();
	}
	
	public static String getName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.getName();
	}
	
	public static ShiroUser getUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

}
