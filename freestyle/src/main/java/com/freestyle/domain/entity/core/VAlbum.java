package com.freestyle.domain.entity.core;

import java.util.Date;

public class VAlbum {
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

    /*账号ID*/
    private Long accountId;

    /*相册名称*/
    private String name;

    /*相册描述*/
    private String description;

    /*封面文件ID*/
    private Long coverPhotoId;
    /*图片数量*/
    private Integer photoCount;
    
    /*文件存储相对路径*/
    private String uri;

    /************下面三个不要写文档里************/
    private Integer visitsCount;

    private Integer commentCount;

    private Integer agreeCount;

    

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCoverPhotoId() {
        return coverPhotoId;
    }

    public void setCoverPhotoId(Long coverPhotoId) {
        this.coverPhotoId = coverPhotoId;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount(Integer visitsCount) {
        this.visitsCount = visitsCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}