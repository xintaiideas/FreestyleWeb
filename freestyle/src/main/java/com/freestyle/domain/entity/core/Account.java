package com.freestyle.domain.entity.core;

import java.util.Date;

public class Account {
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

    /*用户名*/
    private String username;

    /*密码*/
    private String password;

    /*真实姓名*/
    private String realname;

    /*邮箱*/
    private String email;

    /*电话*/
    private String tel;

    /*头像文件ID*/
    private Long headImgId;

    /*头像缩略图文件ID*/
    private Long headImgThumbId;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(Long headImgId) {
        this.headImgId = headImgId;
    }

    public Long getHeadImgThumbId() {
        return headImgThumbId;
    }

    public void setHeadImgThumbId(Long headImgThumbId) {
        this.headImgThumbId = headImgThumbId;
    }
}