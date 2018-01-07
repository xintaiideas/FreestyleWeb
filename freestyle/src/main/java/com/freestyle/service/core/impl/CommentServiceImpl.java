package com.freestyle.service.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.freestyle.domain.entity.core.Comment;
import com.freestyle.mapper.core.CommentMapper;
import com.freestyle.service.core.ICommentService;
import com.freestyle.service.exception.EntityNotFoundException;

@Service
@Transactional(readOnly=true)
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	@Transactional(readOnly=false)
	public long publish(long accountId, long moodId, Long parentCommentId, String content) {
		Comment comment = new Comment();
		comment.setMoodId(moodId);
		comment.setAccountId(accountId);
		comment.setParentId(parentCommentId);
		comment.setContent(content);
		this.commentMapper.insertSelective(comment);
		return comment.getId();
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteComment(long commentId) {
		this.commentMapper.deleteByPrimaryKey(commentId);
	}

	@Override
	public Comment getComment(long commentId) throws EntityNotFoundException {
		Comment comment = this.commentMapper.selectByPrimaryKey(commentId);
		if(comment == null) {
			throw new EntityNotFoundException("评论不存在，ID：" + commentId, Comment.class);
		}
		return comment;
	}

	@Override
	public void checkExistComment(long commentId)
			throws EntityNotFoundException {
		this.getComment(commentId);
	}
	
}
