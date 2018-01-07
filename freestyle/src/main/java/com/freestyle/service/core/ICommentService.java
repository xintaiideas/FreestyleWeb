package com.freestyle.service.core;

import com.freestyle.domain.entity.core.Comment;
import com.freestyle.service.exception.EntityNotFoundException;

public interface ICommentService {

	/**
	 *  发表评论
	 * @param accountId	账号ID
	 * @param moodId	心情ID
	 * @param parentCommentId	父级评论ID
	 * @param content	评论内容
	 * @return
	 */
	long publish(long accountId,long moodId, Long parentCommentId, String content);
	
	/**
	 * 删除评论
	 * @param commentId	评论ID
	 */
	void deleteComment(long commentId);
	
	/**
	 * 获取评论信息
	 * @param commentId	评论ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	Comment getComment(long commentId) throws EntityNotFoundException;
	
	/**
	 * 检查评论是否存在
	 * @param commentId 评论ID
	 * @throws EntityNotFoundException
	 */
	void checkExistComment(long commentId) throws EntityNotFoundException;
}
