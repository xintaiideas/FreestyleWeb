package com.freestyle.domain.entity;

import java.io.Serializable;

public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String username;
	
	private String name;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
