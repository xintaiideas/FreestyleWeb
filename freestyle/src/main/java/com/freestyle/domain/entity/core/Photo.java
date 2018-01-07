package com.freestyle.domain.entity.core;

import java.util.Date;

public class Photo {
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

    /*相册ID*/
    private Long albumId;

    /*照片名称*/
    private String name;

    /*照片描述*/
    private String description;

    /*文件ID*/
    private Long fileId;

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

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}