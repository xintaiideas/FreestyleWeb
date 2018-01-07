package com.freestyle.domain.view.core;

import java.util.Date;

public class FriendVo {
	
	private Long id;

	private Long friendId;
	
	private Long accountId;
	
	private String accountName;
	
	private String realname;
	
	private String friendRemark;
	
	private Boolean isFriend;
	
	private Boolean isSelf;
	
	private Date friendTime;
	
	

	public Boolean getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(Boolean isSelf) {
		this.isSelf = isSelf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getFriendRemark() {
		return friendRemark;
	}

	public void setFriendRemark(String friendRemark) {
		this.friendRemark = friendRemark;
	}

	public Boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Boolean isFriend) {
		this.isFriend = isFriend;
	}

	public Date getFriendTime() {
		return friendTime;
	}

	public void setFriendTime(Date friendTime) {
		this.friendTime = friendTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((friendId == null) ? 0 : friendId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendVo other = (FriendVo) obj;
		if(friendId == null) {
			return false;
		}else if(other.friendId == null) {
			return false;
		}else if (!friendId.equals(other.friendId))
			return false;
		return true;
	}

}
