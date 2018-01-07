package com.freestyle.web.controller.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.core.ICommentService;
import com.freestyle.util.ShiroUtil;

/**
 * 该类为web前端提供评论相关的http接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	/*评论服务*/
	@Autowired
	private ICommentService commentService;

	/**
	 * 发表评论
	 * @param moodId	心情ID
	 * @param parentCommentId	父级评论ID
	 * @param content	评论内容
	 * @return
	 */
	@RequestMapping("publish")
	public @ResponseBody ApiResponse publish(
			@RequestParam(required=true) Long moodId,
			@RequestParam(required=false) Long parentCommentId,
			@RequestParam(required=true) String content
			) {
		long accountId = ShiroUtil.getUserId();
		long commentId = this.commentService.publish(accountId,moodId, parentCommentId, content);
		return ApiResponse.OK.putAttribute("id", commentId);
	}
	
	/**
	 * 删除评论
	 * @param commentId	评论ID
	 * @return
	 */
	@RequestMapping("delete-comment")
	public @ResponseBody ApiResponse deleteComment(
			@RequestParam(required=true) Long commentId
			) {
		this.commentService.deleteComment(commentId);
		return ApiResponse.OK;
	}
}
