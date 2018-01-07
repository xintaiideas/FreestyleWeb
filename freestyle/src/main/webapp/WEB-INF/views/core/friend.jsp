<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>随变网</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" href="${staticPath}/commons/layer/layui/css/layui.css" media="all">
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/freestyle.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/index.css" />
	
	<style type="text/css">
		.group-op,.account-op,.add-friend-op {position:absolute;right:20px;}
		.group-op,.account-op {display:none;}
		.group-op a,.account-op a,.add-friend-op a {color:#7E7EF5;}
		.account-type {position:absolute;right:150px;color:#AAA;}
		.friend-list {
			position:absolute;
			top:59px;
			right:0px;
			left:0px;
			bottom:0px;
			overflow-y:scroll;
			list-style: none;
		}
		.friend-list li {
			position:relative;
			cursor:pointer;
			height:20px;
			line-height:20px;
			padding:10px 30px;
			color:#666;
			
			border-bottom:1px solid #eee;
		}
		.friend-list li:hover {
			background:#eee; 
		}
	</style>
	
	<script type="text/javascript">
		var _ctx = '${ctx}';
		var _staticPath = '${staticPath}';
	</script>
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	
	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layui/layui.js"></script>
	<script type="text/javascript" src="${staticPath}/core/js/index.js"></script>
	
</head>
<body>
	<!--头部-->
	<div class="g-hd">
    	<!--左边logo-->
        <div class="logo"></div>
    	<!--右边用户信息-->
	    <dl class="hd-account" style="margin-top:12px;">
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
           <dd style="margin-left:40px;">
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
            	<a href="${ctx}/album">
                	<img class="app-icon my-ablum" width="32" height="32" src="${staticPath}/core/image/a.gif">
                    <span class="app-title">我的相册</span>
                </a>
            </li>
			<li>
            	<a class="app-link" href="${ctx}/friend">
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
                        <a class="fd-nav-filter" href="#" onclick="return false;">我的主页</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="bd-main">
            
            
            <div style="width:760px;margin:0 auto;margin-top:65px;">
            	<div style="width:250px;height:500px;float:left;
				    display: block;
				    margin-bottom: 15px;
				    background-color: #FFF;
				    border: 1px solid #e1dfdf;
				    position:relative;
				    border-radius: 2px;
				    box-shadow: 0 1px 1px #CCC;
				    word-break: break-all;">
    				<div style="border-bottom:1px solid #ddd;">
    					<h3 style="line-height:58px;margin-left:10px;color:#aaa;display:inline-block;">好友分组</h3>
    					<a id="btnNewGroup" href="javascript:void(0);" style="margin-left:140px;color:blue;">新建</a>
    				</div>
            		<ul class="friend-list" id="group-list">
            			
            		</ul>
            	</div>
            	
            	<div style="width:490px;float:left;margin-left:15px;height:500px;display: block;
				    margin-bottom: 15px;
				    background-color: #FFF;
				    border: 1px solid #e1dfdf;
				    border-radius: 2px;
				    position:relative;
				    box-shadow: 0 1px 1px #CCC;
				    word-break: break-all;">
    				<div style="border-bottom:1px solid #ddd;padding:10px 10px;">
    					<input id="inputUsername" type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入用户名进行搜索" class="layui-input" style="display:inline-block;width:400px;">
    					<button id="btnSearch" class="layui-btn" lay-submit="" lay-filter="demo1" style="display:inline-block;height:35px;">搜索</button>
    				</div>
            		<ul class="friend-list" id="account-list" style="top:59px;">
            			<li>点击分组或搜索用户</li>
            		</ul>
            	</div>
            </div>
            
            
        </div>
		<!-- InstanceEndEditable -->
	</div>

	<script type="text/javascript">
	
		function editFriend(id) {
			layer.open({
			      type: 2,
			      title: '修改备注',
			      skin: '#227dc5',
			      shade: 0.4,
			      shadeClose: true,
			      maxmin: false, //开启最大化最小化按钮
			      area: ['400px', '260px'],
			      content: _ctx + '/friend/modify-friend/' + id
			    });
		}
		function deleteFriend(id,accountId) {
			layer.confirm('您确定删除好友吗？', {
				skin: '#227dc5',
				  btn: ['确定','取消'] //按钮
				}, function(){
				  $.post('${ctx}/friend/delete-friend',{friendId:id},function(r) {
					  if(r.code == 'OK') {
						  layer.msg('好友删除成功');
						  $('#accountId_' + accountId).remove();
					  }else {
						  layer.msg(r.message);
					  }
				  },'json');
				}, function(){
				  
				});
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
		
		function editGroup(id) {
			layer.open({
			      type: 2,
			      title: '修改分组名称',
			      skin: '#227dc5',
			      shade: 0.4,
			      shadeClose: true,
			      maxmin: false, //开启最大化最小化按钮
			      area: ['400px', '200px'],
			      content: _ctx + '/friend/modify-group/' + id
			    });
		}
		function deleteGroup(id) {
			layer.confirm('您确定删除分组吗？', {
				skin: '#227dc5',
				  btn: ['确定','取消'] //按钮
				}, function(){
				  $.post('${ctx}/friend/delete-group',{groupId:id},function(r) {
					  if(r.code == 'OK') {
						  layer.msg('分组删除成功');
						  loadGroup();
					  }else {
						  layer.msg(r.message);
					  }
				  },'json');
				}, function(){
				  
				});
		}
		function loadGroup() {
			$.get('${ctx}/friend/group-list',function(r) {
				if(r.code == 'OK') {
					var groups = r.attributes.groups;
					var html = '';
					if(r.attributes.groups.length) {
						for(var index in groups) {
							html += '<li groupId="' + groups[index].id + '">' + groups[index].name + 
							'<span class="group-op"><a href="javascript:editGroup('+groups[index].id+')">编辑</a>'+
							' <a href="javascript:deleteGroup('+groups[index].id+')">删除</a></span></li>';
						}
					}else {
						html = '<li>您还没有进行好友分组</li>';
					}
					$('#group-list').html(html);
				}
			},'json');
		}
		function loadFriend(groupId,username) {
			$.get('${ctx}/friend/friend-list',{groupId:groupId,username:username},function(r) {
				if(r.code == 'OK') {
					var friends = r.attributes.friends;
					var html = '';
					if(r.attributes.friends.length) {
						for(var index in friends) {
							html += '<li id="accountId_' + friends[index].accountId + '" accountId="' + friends[index].accountId + '">';
							if(friends[index].friendRemark) {
								html += friends[index].friendRemark + '(' + friends[index].accountName + ')';	
							}else {
								html += '<span class="account">' + friends[index].accountName + '</span>';
							}
							if(friends[index].isSelf) {
								html += '<span class="account-type">自己</span>';
							}else {
								if(friends[index].isFriend) {
									html += '<span class="account-type">好友</span><span class="account-op">'+
									'<a href="${ctx}/person-page/friend/'+friends[index].friendId+'">主页</a> <a href="javascript:editFriend('+friends[index].id+')">编辑</a>'+
									' <a href="javascript:deleteFriend('+friends[index].id+','+friends[index].accountId+')">删除</a></span>';
								}else {
									html += '<span class="add-friend-op"><a href="javascript:addFriend('+friends[index].accountId+')">添加好友</a></span>';
								}
							}
							html += '</li>';
						}
					}else {
						if(groupId) {
							html = '<li>该分组下没有好友</li>';	
						}else {
							html = '<li>没有搜索到匹配的用户</li>';	
						}
					}
					$('#account-list').html(html);
				}
			},'json');
		}
		$(function() {
			loadGroup();
			$('#group-list').delegate('li','click',function() {
				var groupId = $(this).attr('groupId');
				loadFriend(groupId);
			}).delegate('li','mouseover',function() {
				$(this).find('.group-op').show();
			}).delegate('li','mouseout',function() {
				$(this).find('.group-op').hide();
			});
			$('#account-list').delegate('li','mouseover',function() {
				$(this).find('.account-op').show();
			}).delegate('li','mouseout',function() {
				$(this).find('.account-op').hide();
			});
			$('#btnSearch').click(function() {
				loadFriend(undefined,$('#inputUsername').val());
			});
			
			$('#btnNewGroup').click(function() {
				layer.open({
				      type: 2,
				      title: '新建分组',
				      skin: '#227dc5',
				      shade: 0.4,
				      shadeClose: true,
				      maxmin: false, //开启最大化最小化按钮
				      area: ['400px', '200px'],
				      content: _ctx + '/friend/add-group'
				    });
			});
		});
	</script>
</body>
</html>