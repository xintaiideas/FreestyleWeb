<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>我的相册 - 随变网</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/freestyle.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/index.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/album.css" />
	<script type="text/javascript">
		var _ctx = '${ctx}';
		var _staticPath = '${staticPath}';
	</script>
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/laypage/laypage.js"></script>
	<script type="text/javascript" src="${staticPath}/core/js/index.js"></script>
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
            	<a href="${ctx}/person-page/my-page">
                	<img class="app-icon person-page" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">个人主页</span>
				</a>
            </li>
        	<li>
            	<a class="app-link" href="${ctx}/album">
                	<img class="app-icon my-ablum" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">相册</span>
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
    <div class="g-mn">
    	<div id="frameFixedNav" class="frame-nav-fixed-wraper">
            <div class="frame-nav-inner">
                <ul class="fd-nav-list">
                
                    <li class="fd-nav-item fd-nav-item-list fd-nav-cur-item">
                        <a class="fd-nav-filter" data-feed-type="0" href="#" onclick="return false;">相册</a>
                    </li>
		
                </ul>
            </div>
        </div>
        <div class="bd-main">
            <div class="bd-content"> 
		<!-- InstanceBeginEditable name="content" -->            
                <div class="album-list-main">
                	<!--相册工具栏-->
                    <div class="album-list-bar">
                        <ul id="album-tab">
                            <li><a class="a-b-t" href="${ctx}/photo/recently-photo">最近照片</a></li>
                            <li><a class="a-b-t a-b-t-hover" href="#">相册</a></li>
                        </ul>
                        <div class="album-ctrl">
                            <div class="album-btn">
                            	<c:if test="${user.userId eq account.id}">
                                	<a id="btnCreate" class="a-b-btn btn-create"></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <!--相册列表-->
                    <div class="album-list" style="min-height: 288px;">
                        <ul class="a-b-tab" id="album-list">
                        	<c:forEach items="${albums.rows}" var="album">
	                            <li class="album-box">
	                                <a class="album-item album-bg-single" href="${ctx}/album/${album.id}">
	                                    <c:if test="${album.uri eq null}">
	                                		<img class="album-cover" src="${staticPath}/core/image/album.png" alt="${album.name}">	
	                                	</c:if>
	                                    <c:if test="${album.uri ne null}">
	                                		<img class="album-cover" src="${ctx}/photo/${album.coverPhotoId}" alt="${album.name}">	
	                                	</c:if>
	                                    <span class="album-del-icon">
	                                        <span class="btn-del-album">
	                                        </span>
	                                    </span>
	                                </a>
	                                <span class="album-count">${album.photoCount}</span>
	                                <div class="album-info">	
	                                    <a class="album-name" title="我的相册" src="我的相册" href="${ctx}/album/${album.id}">${album.name}</a>	
	                                    <span class="album-limit" id="">
	                                    	<c:if test="${user.userId eq account.id}">
		                                    	<a class="album-link-btn" href="javascript:editAblum(${album.id});">编辑</a>
		                                    	<a class="album-link-btn" href="javascript:delAblum(${album.id},'${album.name}');">删除</a>
	                                    	</c:if>
	                                    </span>
	                                </div>
	                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>  
                <div id="pager"></div>                                 
            </div>
        </div>
    </div>

	<script type="text/javascript">
		laypage({
		  cont: 'pager',
		  pages: parseInt('${albums.totalPageNum}'),
		  curr: function(){ //通过url获取当前页，也可以同上（pages）方式获取
		    var page = location.search.match(/pageIndex=(\d+)/);
		    return page ? page[1] : 1;
		  }(), 
		  jump: function(e, first){ //触发分页后的回调
		    if(!first){ //一定要加此判断，否则初始时会无限刷新
		      location.href = '?pageIndex='+e.curr;
		    }
		  }
		});
		
		
		$(function() {
			$('#btnCreate').click(function() {
				layer.open({
			      type: 2,
			      title: '新建相册',
			      skin: '#227dc5',
			      shade: 0.4,
			      shadeClose: true,
			      maxmin: false, //开启最大化最小化按钮
			      area: ['500px', '300px'],
			      content: '${ctx}/album/create-album'
			    });
			});
			$('#btnUpload').click(function() {
				layer.open({
			      type: 2,
			      title: '上传照片',
			      skin: '#227dc5',
			      shade: 0.4,
			      shadeClose: true,
			      maxmin: false, //开启最大化最小化按钮
			      area: ['600px', '500px'],
			      content: '${ctx}/album/upload'
			    });
			});
		});
		
		function editAblum(id) {
			layer.open({
			      type: 2,
			      title: '修改相册',
			      skin: '#227dc5',
			      shade: 0.4,
			      shadeClose: true,
			      maxmin: false, //开启最大化最小化按钮
			      area: ['500px', '300px'],
			      content: '${ctx}/album/modify-album/' + id
			    });
		}
		
		function delAblum(id,name) {
			layer.confirm('您确定要删除相册 - ' + name + ' 吗？', {
			  btn: ['确定', '取消']
			},
			function(index, layero){
			  $.post('${ctx}/album/delete-album',{albumId:id},function(r) {
				  if(r.code == 'OK') {
					  layer.msg('相册删除成功！');
					  window.location.reload();
				  }else {
					  layer.msg(r.message);
				  }
			  },'json');
			}, function(index){
			  //按钮【按钮二】的回调
			});
		}
	</script>
</body>
</html>