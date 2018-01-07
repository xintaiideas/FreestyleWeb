package com.freestyle.domain.entity.core;

import java.util.Date;

public class Friend {
    /**
    * 好友ID
    */
    private Long id;

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 更新时间
    */
    private Date updatedTime;

    /**
     * 好友分组ID
     */
    private Long groupId;

    /*好友ID*/
    private Long friendId;

    /*账号ID*/
    private Long accountId;

    /*好友备注*/
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}