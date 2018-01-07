package com.freestyle.domain.view.core;

import java.util.Date;

public class CommentVo {

    private Long id;

    private Date createdTime;

    private Date updatedTime;

    private Long moodId;

    private String content;

    private Long accountId;

    private Long parentId;
    
    private String commentor;
    
    private Long headImgId;

	public Long getHeadImgId() {
		return headImgId;
	}

	public void setHeadImgId(Long headImgId) {
		this.headImgId = headImgId;
	}

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

	public Long getMoodId() {
		return moodId;
	}

	public void setMoodId(Long moodId) {
		this.moodId = moodId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCommentor() {
		return commentor;
	}

	public void setCommentor(String commentor) {
		this.commentor = commentor;
	}

}
