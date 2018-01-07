<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>随变网</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/freestyle.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/index.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/commons/viewer/viewer.min.css" />
	
	<script type="text/javascript">
		var _ctx = '${ctx}';
		var _staticPath = '${staticPath}';
		var _headImgId = '${account.headImgId ne null}';
		var _username = '${user.name}';
	</script>
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/jquery-validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/jquery-validate/messages_zh.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/viewer/viewer-jquery.min.js"></script>
	<script type="text/javascript" src="${staticPath}/core/js/index.js"></script>
	<script type="text/javascript" src="${staticPath}/core/js/mood.js"></script>
</head>
<body>
	<!--头部-->
	<div class="g-hd">
    	<!--左边logo-->
        <div class="logo"></div>
    	<!--右边用户信息-->
	    <dl class="hd-account">
           <dt>
               <a href="#">
               		<c:if test="${account.headImgId eq null}">
						<img class="hd-avatar" id="hd-avatar" width="30" height="30" src="${staticPath}/core/image/men_tiny.gif" alt="${account.realname}">
					</c:if>
					<c:if test="${account.headImgId ne null}">
						<img class="hd-avatar" id="hd-avatar" width="30" height="30" src="${ctx}/head-image" alt="${account.realname}">
					</c:if>
               </a>
           </dt>
           <dd>
               <a class="hd-name" id="hd-name" href="javascript:void(0)" title="用户名称">您好,${account.realname}</a>
               <span style="display:block;">
                   <a id="linkModifyPassword" class="account-operate" href="#">修改密码</a> 
                   <a id="linkLogout" class="account-operate" href="javascript:void(0);">退出</a>
               </span>
           </dd>
       </dl>
	</div>
    <!--左边菜单-->
    <div class="g-sd">
        <ul class="nav">
        	<li>
            	<a href="${ctx}/index">
                	<img class="app-icon index" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">首页</span>
                </a>
            </li>
            <li>
            	<a class="app-link" href="${ctx}/about-me">
                	<img class="app-icon about-me" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">与我相关</span>
				</a>
            </li>
        	<li>
            	<a href="${ctx}/person-page/my-page">
                	<img class="app-icon person-page" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">个人主页</span>
				</a>
            </li>
        	<li>
            	<a href="${ctx}/album">
                	<img class="app-icon my-ablum" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">我的相册</span>
                </a>
            </li>
			<li>
            	<a href="${ctx}/friend">
                	<img class="app-icon my-friend" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">好友</span>
				</a>
            </li>
        </ul>
    </div>
    <div class="g-mn" style="overflow-y:scroll;">
    	<div id="frameFixedNav" class="frame-nav-fixed-wraper">
            <div class="frame-nav-inner">
                <ul class="fd-nav-list">
                <!-- InstanceBeginEditable name="frameNav" -->
                    <li class="fd-nav-item fd-nav-item-list fd-nav-cur-item">
                        <a class="fd-nav-filter" href="#" onclick="return false;">与我相关</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="bd-main">
            <div class="bd-content clearfix"> 
            
	        <div class="bd-publisher"></div>
	        <div class="nx-right " style="top: 0px; bottom: 0px; position: relative; right: 0px; z-index: 99;">
	        	<c:if test="${hotPhoto ne null}">
        		<div class="side-item photopast" style="position:fixed;"> 
        			<div class="side-item-header">
        				<span>热门推荐</span>
        			</div>
        			<div class="side-item-body">
        				<div class="photopast-image">
        					<div class="photopast-notice">
        						<div class="photopast-notice-bg">
        						</div>
        						<p class="photopast-notice-text"></p>
        					</div>
        					<a href="${ctx}/album/friend/${hotPhoto.accountId}">
        						<img style="margin-left:0px;margin-top:0px;border:none;" width="240" height="240" src="${ctx}/photo/${hotPhoto.photoId}">
        						</a>
        						</div>
        					<div class="photopast-memo">
        						<a class="photopast-name" target="_blank" href="">${hotPhoto.account}</a>
        						<b class="photopast-date">上传于<fmt:formatDate timeZone="GTM+8" value="${hotPhoto.createdTime}" pattern="yyyy年MM月dd日"/></b>
        					</div>
        			</div>
        		</div>
        		</c:if>
        	</div>
        	<div class="nx-content">
        	<c:forEach items="${moods.rows}" var="mood">
		        <div class="a-feed" style="position:relative;">
		        	<div style="position:absolute;right:13px;top:10px;cursor:pointer;z-index:500;">
		        	<a href="javascript:deleteMood(${mood.id})" style="text-decoration: none;">X</a>
		        	</div>
		        	<div class="clearfix header">
		        		<div class="user-avatar" >
		        			<a target="_blank" href="${ctx}/person-page/friend/${mood.accountId}" class="avatar">
		        			<c:if test="${mood.headImgId eq null}">
		        				<img src="${staticPath}/core/image/men_tiny.gif" width="50" title="${mood.publisherName}">
							</c:if>
							<c:if test="${mood.headImgId ne null}">
								<img src="${ctx}/head-image/${mood.accountId}" width="50" title="${mood.publisherName}">
							</c:if>
		            		</a>
		            	</div>
		            	<div class="feed-user-info clearfix">
		            		<a target="_blank" href="" class="feed-user-name">${mood.publisherName}</a>&nbsp;
		            	</div>
		            	<div class="feed-action-info">
		            		<p>发布心情&nbsp;<span class="pub-time"> <fmt:formatDate timeZone="GTM+8" value="${mood.createdTime}" pattern="MM-dd HH:mm"/></span></p>
		        		</div>
		        	</div>
			        <div class="feed-content">
			        	<p class="status">${mood.content}</p>
			        	<div class="image-list clearfix" style="margin-bottom:20px;">
			        	<c:if test="${mood.type eq 'Photo'}">
			        		<c:forEach items="${mood.moodFile}" var="moodFile">
			        			<div style="float:left;width:150px;height:100px;margin-left:10px;cursor:pointer;">
			        				<img src="${ctx}/mood/mood-file/${moodFile.id}" style="width:100%;height:100%;" />
			        			</div>
			        		</c:forEach>
			        	</c:if>
			        	<c:if test="${mood.type eq 'Video'}">
			        		<c:forEach items="${mood.moodFile}" var="moodFile">
			        			<video src="${ctx}/mood/mood-file/${moodFile.id}" controls="controls" style="padding-left:15px;padding-right:15px;width:470px;max-height:300px;">
								</video>
			        		</c:forEach>
			        	</c:if>
			        	</div>
			        </div>
			        <div class="feed-reply-container">
			          <div class="feed-replies">
			          	
			          	<c:forEach items="${mood.comments}" var="comment">
				          	<div id="comment_id_${comment.id}" commentId="${comment.id}" class="a-reply clearfix">
								<a href="${ctx}/person-page/friend/${comment.accountId}" class="avatar" target="_blank">	
									<c:if test="${comment.headImgId eq null}">
										<img src="${staticPath}/core/image/men_tiny.gif" class="user-avatar"/>
									</c:if>
									<c:if test="${comment.headImgId ne null}">
										<img src="${ctx}/head-image/${comment.accountId}" class="user-avatar"/>
									</c:if>	
								</a>
								<div class="reply-content">
									<p class="reply-info clearfix"> 
							            <a href="" target="_blank" class="user-name">${comment.commentor}</a>
							          	<span class="time"><fmt:formatDate timeZone="GTM+8" value="${comment.createdTime}" pattern="MM-dd HH:mm"/>  </span>
							          	<span class="reply-tool clearfix">
							          	<c:if test="${user.userId eq comment.accountId or user.userId eq mood.accountId}">
							          		<a href="javascript:deleteComment(${comment.id})" class="delete">删除</a>
							          	</c:if>
							          	</span>
							         </p>
						         	<p class="text">${comment.content}</p>
						         </div>
							</div>
						</c:forEach>
			          	
			          </div>
			        </div>
		        	<div class="own-reply clearfix">
			            <a class="avatar" href="javascript:void(0)" target="_blank">
			            	<c:if test="${account.headImgId eq null}">
			            		<img src="${staticPath}/core/image/men_tiny.gif" title="${account.realname}"/>
							</c:if>
							<c:if test="${account.headImgId ne null}">
								<img src="${ctx}/head-image" title="${account.realname}"/>
							</c:if>
			                
			            </a>
		            	<div class="own-reply-content"> 
							<div class="input-container">
						    	<div contenteditable="true" moodId="${mood.id}" class="reply-input">评论...</div> 
						        	<div class="tool-bar clearfix">
				                        <p>
				                            <span class="rest-count">0</span>
				                            /
				                            <span>240</span>
				                            <span class="tips-text">Ctrl+Enter发送</span>
				                        </p>
						            </div>
						        </div>
				                <div class="reply-btns clearfix">
		                      		<a href="#nogo" class="add-comment disabled">发布评论</a>
		                      		<a href="#nogo" class="cancel-comment">取消</a>
								</div>
						</div>
					</div>
			        <div style="display:none;">
			       
			        </div>
		        </div>
        	</c:forEach>
        	</div>
        </div>
        
        </div>
		<!-- InstanceEndEditable -->
	</div>

	<script type="text/javascript">
		function deleteMood(id) {
			layer.confirm('您确定要删除心情吗？', {
				skin: '#227dc5',
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.post(_ctx + '/mood/delete-mood',{moodId:id},function(r) {
						if(r.code == 'OK') {
							location.reload();
							layer.msg('心情删除成功');
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
	</script>
</body>
</html>