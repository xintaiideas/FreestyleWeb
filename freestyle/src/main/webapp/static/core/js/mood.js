Date.prototype.format = function(format)
	{
	 var o = {
	 "M+" : this.getMonth()+1, //month
	 "d+" : this.getDate(),    //day
	 "h+" : this.getHours(),   //hour
	 "m+" : this.getMinutes(), //minute
	 "s+" : this.getSeconds(), //second
	 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
	 "S" : this.getMilliseconds() //millisecond
	 }
	 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
	 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	 for(var k in o)if(new RegExp("("+ k +")").test(format))
	 format = format.replace(RegExp.$1,
	 RegExp.$1.length==1 ? o[k] :
	 ("00"+ o[k]).substr((""+ o[k]).length));
	 return format;
	}
	
		function deleteComment(id) {
			layer.confirm('您确定要删除评论吗？', {
				skin: '#227dc5',
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.post(_ctx + '/comment/delete-comment',{commentId:id},function(r) {
						if(r.code == 'OK') {
							$('#comment_id_' + id).remove();
							layer.msg('评论删除成功');
						}else {
							layer.msg(r.message, {
				        		  offset: 't',
				        		  anim: 6
				        		});
						}
					},'json');
				}, function(){
				  
				});
			
		}
		$(function() {
			$('.cancel-comment').click(function() {
				var replyInput = $(this).parent().prev().find('.reply-input');
				var comment = replyInput.text();
				replyInput.text('');
				replyInput.blur();
			});
			$('.add-comment').click(function() {
				var replyInput = $(this).parent().prev().find('.reply-input');
				var commentArea = $(this).parent().parent().parent().prev().find('.feed-replies');
				var comment = replyInput.text();
				if(comment.length > 240) {
					layer.msg('评论不能超过240个字符', {
		        		  offset: 't',
		        		  anim: 6
		        		});
					return ;
				}
				var $parentCommentId = replyInput.find('.at');
				var parentCommentId = null;
				if($parentCommentId.length) {
					parentCommentId = $parentCommentId.attr('parentCommentId');
				}
				$.post(_ctx + '/comment/publish',{moodId:replyInput.attr('moodId'),parentCommentId:parentCommentId,content:comment},function(r) {
					if(r.code == 'OK') {
						replyInput.text('');
						replyInput.blur();
						var withHeadImg = _headImgId;
	    				
						if(withHeadImg == 'true') {
							commentArea.append(
									'<div id="comment_id_'+r.attributes.id+'" commentId="'+r.attributes.id+'" class="a-reply clearfix">' +
							        '<a href="" class="avatar" target="_blank">'+
							        '<img src="' + _ctx + '/head-image" class="user-avatar"></a>' + 
							        '<div class="reply-content">' + 
							          '<p class="reply-info clearfix">' + 
							            '<a href="" target="_blank" class="user-name">'+_username+'</a>'+
							          '<span class="time">'+ new Date().format("MM-dd hh:mm")+'</span>'+
							          '<span class="reply-tool clearfix">'+
							          '<a href="javascript:deleteComment('+ r.attributes.id+')" class="delete">删除</a></span>'+
							         '</p><p class="text">'+comment+'</p></div>'+
							        '</div>'
							        );
						}else {
							commentArea.append(
									'<div id="comment_id_'+r.attributes.id+'" commentId="'+r.attributes.id+'" class="a-reply clearfix">' +
							        '<a href="" class="avatar" target="_blank">'+
							        '<img src="' + _staticPath + '/core/image/men_tiny.gif" class="user-avatar"></a>' + 
							        '<div class="reply-content">' + 
							          '<p class="reply-info clearfix">' + 
							            '<a href="" target="_blank" class="user-name">'+_username+'</a>'+
							          '<span class="time">'+ new Date().format("MM-dd hh:mm")+'</span>'+
							          '<span class="reply-tool clearfix">'+
							          '<a href="javascript:deleteComment('+ r.attributes.id+')" class="delete">删除</a></span>'+
							         '</p><p class="text">'+comment+'</p></div>'+
							        '</div>'
							        );
						}
						
					}else {
						layer.msg(r.message, {
			        		  offset: 't',
			        		  anim: 6
			        		});
					}
				},'json');
			});
			$('.reply-input').click(function() {
				if($(this).text() == '评论...') {
					$(this).text('');	
				}
				$(this).height('40px');
				$(this).next().show();
				$(this).parent().next().show();
			});
			$('.reply-input').blur(function() {
				var len = $(this).text().length;
				if(len == 0) {
					$(this).text('评论...');
					$(this).height('16px');
					$(this).next().hide();	
					$(this).parent().next().hide();
				}
			});
			$('.reply-input').keyup(function() {
				var len = $(this).text().length;
				$(this).next().find('.rest-count').text(len);
			});
			
			$('.image-list').viewer();
			
			$('.a-reply').on('mouseover',function() {
				$(this).find('.delete').show();
				$(this).css('background','#E6EEF5');
			}).on('mouseout',function() {
				$(this).find('.delete').hide();
				$(this).css('background','');
			}).click('click',function(){
				var parentCommentId = $(this).attr('commentId');
				var username = $(this).find('.user-name').text();
				var replyInput = $(this).parent().parent().next().find('.reply-input');
				replyInput.find('.at').remove();
				replyInput.prepend('<span class="at" parentCommentId="'+parentCommentId+'" >@'+username+'</span> ');
			});
		});
		