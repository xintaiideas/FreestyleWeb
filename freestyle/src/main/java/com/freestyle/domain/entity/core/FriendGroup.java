package com.freestyle.domain.entity.core;

import java.util.Date;

public class FriendGroup {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 记录创建时间
    */
    private Date createdTime;

    /**
    * 记录更新时间
    */
    private Date updatedTime;

    /*分组名称*/
    private String name;

    /*账号ID*/
    private Long accountId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}