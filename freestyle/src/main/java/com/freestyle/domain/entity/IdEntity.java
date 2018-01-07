package com.freestyle.domain.entity;

import java.util.Date;

/**
 * 数据库实体抽象
 * @author Leo Lien
 * 2016年10月19日 下午8:26:46 创建
 */
public abstract class IdEntity {
	
    /*主键*/
    private Long id;
    
    /*记录创建时间*/
    private Date createdTime;
    
    /*记录最后更新时间*/
    private Date updatedTime;

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
    
}
