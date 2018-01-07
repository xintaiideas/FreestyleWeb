package com.freestyle.domain.entity.core;

import java.util.Date;

public class Mood {
    /**
    * 心情ID
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

    /*账号ID*/
    private Long accountId;

    /*心情内容*/
    private String content;

    /*心情类型*/
    private String type;

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}