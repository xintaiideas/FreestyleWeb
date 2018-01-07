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
	</script>
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${staticPath}/core/timeline/css/default.css" />

		<link rel="stylesheet" type="text/css" href="${staticPath}/core/timeline/css/component.css" />

	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
		<script src="${staticPath}/core/timeline/js/modernizr.custom.js"></script>
		<script type="text/javascript" src="${staticPath}/core/js/index.js"></script>
		<script type="text/javascript" src="${staticPath}/commons/viewer/viewer-jquery.min.js"></script>
    
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
            	<a href="${ctx}/about-me">
                	<img class="app-icon about-me" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">与我相关</span>
				</a>
            </li>
        	<li>
            	<a class="app-link" href="${ctx}/person-page/my-page">
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
                        <a class="fd-nav-filter" href="#" onclick="return false;">主页</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="bd-main">
            
            
            <div class="container">

			<header class="clearfix" style="color:white;margin-top:15px;height:120px;background:url(${staticPath}/core/image/person-background.jpg);background-size:100% 100%;">

				<h1>${account.realname}的个人主页</h1>
				<c:if test="${user.userId ne account.id and !isFriend}">
					<a id="linkAddFriend" href="javascript:addFriend(${account.id})" style="display:inline-block;margin-top:15px;text-decoration: none;"> ( 添加好友  )</a>
				</c:if>
				<c:if test="${user.userId ne account.id and isFriend}">
					<a href="javascript:void(0)" style="display:inline-block;margin-top:15px;text-decoration: none;"> ( 已加为好友  )</a>
				</c:if>
				<c:if test="${user.userId ne account.id and !isHisFriend}">
					<a id="linkAddFriend" href="javascript:unableSee()" style="display:inline-block;margin-top:15px;text-decoration: none;"> ( 查看相册  )</a>
				</c:if>
				<c:if test="${user.userId ne account.id and isHisFriend}">
					<a id="linkSeeAlbum" href="${ctx}/album/friend/${account.id}" style="display:inline-block;margin-top:15px;text-decoration: none;"> ( 查看相册  )</a>
				</c:if>
				<c:if test="${user.userId eq account.id}">
					<a href="${ctx}/album" style="display:inline-block;margin-top:15px;text-decoration: none;">  （我的相册）  </a>
				</c:if>
			</header>	

			<div class="main">

				<ul class="cbp_tmtimeline">
					<c:if test="${moods.rows.size() eq 0}">
						<h3>还未发表过心情</h3>
					</c:if>
					<c:forEach items="${moods.rows}" var="mood">
						<li>
							<time class="cbp_tmtime" datetime="<fmt:formatDate timeZone="GTM+8" value="${mood.createdTime}" pattern="MM-dd HH:mm"/>">
								<span><fmt:formatDate timeZone="GTM+8" value="${mood.createdTime}" pattern="dd/MM/yy"/></span> 
								<span><fmt:formatDate timeZone="GTM+8" value="${mood.createdTime}" pattern="HH:mm"/></span>
							</time>
							<div class="cbp_tmicon cbp_tmicon-phone"></div>
							<div class="cbp_tmlabel">
								<h3>${mood.content}</h3>
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
						</li>
					</c:forEach>
				</ul>

			</div>

		</div>
            
            
        </div>
		<!-- InstanceEndEditable -->
	</div>

	<script type="text/javascript">
	function reloadPage() {
		location.reload();
	}
	function unableSee() {
		layer.msg('您不是对方的好友');
	}
	function addFriend(id) {
		layer.open({
		      type: 2,
		      title: '添加好友',
		      skin: '#227dc5',
		      shade: 0.4,
		      shadeClose: true,
		      maxmin: false, //开启最大化最小化按钮
		      area: ['400px', '250px'],
		      content: _ctx + '/friend/add-friend/' + id
		    });
	}
		$(function() {
			$('.image-list').viewer();
		});
	</script>
</body>
</html>