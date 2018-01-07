package com.freestyle.domain.view.core;

import java.util.Date;
import java.util.List;

import com.freestyle.domain.entity.core.MoodFile;

public class MoodVo {
	
	private String publisherName;
	
	private Long headImgId;

    private Long id;

    private Date createdTime;

    private Date updatedTime;

    private Long accountId;

    private String content;

    private String type;
    
    private List<MoodFile> moodFile;
    
    private List<CommentVo> comments;

	public List<CommentVo> getComments() {
		return comments;
	}

	public void setComments(List<CommentVo> comments) {
		this.comments = comments;
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

	public List<MoodFile> getMoodFile() {
		return moodFile;
	}

	public void setMoodFile(List<MoodFile> moodFile) {
		this.moodFile = moodFile;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Long getHeadImgId() {
		return headImgId;
	}

	public void setHeadImgId(Long headImgId) {
		this.headImgId = headImgId;
	}

}
