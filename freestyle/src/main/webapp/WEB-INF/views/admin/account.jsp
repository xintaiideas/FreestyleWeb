<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html><!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- InstanceBeginEditable name="doctitle" -->
	<title>用户管理 - 随变网</title>
	<!-- InstanceEndEditable -->
    <link rel="stylesheet" href="${staticPath}/commons/layer/layui/css/layui.css" media="all">
	<link href="${staticPath}/admin/css/index.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${staticPath}/commons/layer/layui/layui.js"></script>
	<!-- InstanceBeginEditable name="head" -->
	<!-- InstanceEndEditable -->
</head>

<body>

<div class="g-hd">
    <div style="position:absolute;color:#AAA;font-size:25px;line-height:70px;left:20px;width:500px;">随变网管理后台</div>
    <div style="position:absolute;line-height:70px;right:30px;">
    	您好，<span>admin</span>
         <a href="javascript:logout()"> 退出</a>
    </div>
</div>
<div class="g-sd">
    <ul class="layui-nav layui-nav-tree" lay-filter="test">
    <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
      <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">应用管理</a>
        <dl class="layui-nav-child">
          <dd><a href="${ctx}/admin/account">用户管理</a></dd>
          <dd><a href="${ctx}/admin/mood">心情管理</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">服务器管理</a>
        <dl class="layui-nav-child">
          <dd><a href="${ctx}/admin/monitor">服务器监控</a></dd>
        </dl>
      </li>
    </ul>
</div>
<div class="g-mn">
	
    <!-- InstanceBeginEditable name="main" -->
    	<div style="background:white;line-height:50px;border-bottom:1px solid #DDD;padding-left:20px;color:#888;margin-bottom:20px;">用户管理</div>
    	<div style="max-width:1000px;margin:0 auto;">
    	<table class="layui-table" lay-skin="line">
          <colgroup>
            <col width="150">
            <col width="200">
            <col>
          </colgroup>
          <thead>
            <tr>
              <th>用户名</th>
              <th>姓名</th>
              <th>邮箱</th>
              <th>电话</th>
              <th>注册时间</th>
              <th>操作</th>
            </tr> 
          </thead>
          <tbody>
          <c:forEach items="${page.rows}" var="account">
            <tr>
              <td>${account.username}</td>
              <td>${account.realname}</td>
              <td>${account.email}</td>
              <td>${account.tel}</td>
              <td><fmt:formatDate value="${account.createdTime}" timeZone="GTM+8" pattern="yyyy-MM-dd HH:mm:ss" /></td>
              <td><a href="javascript:modifyPassword(${account.id})">修改密码</a></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>

        
        <div id="pagination" style="text-align:right;margin-right:10px;"></div>
        
        </div>
        
        <script type="text/javascript">
		layui.use(['laypage', 'layer'], function(){
		  var laypage = layui.laypage,layer = layui.layer;
		  laypage({
			  cont: 'pagination',
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
			});
		
		function modifyPassword(id) {
			layer.open({
			      type: 2,
			      title: '修改密码',
			      skin: '#227dc5',
			      shade: 0.4,
			      shadeClose: true,
			      maxmin: false, //开启最大化最小化按钮
			      area: ['500px', '300px'],
			      content: '${ctx}/admin/account/modify-password/' + id
			    });
		}
        </script>
	<!-- InstanceEndEditable -->
</div>


	<script type="text/javascript">
	function logout() {
		layer.confirm('您确定要继续退出吗？', {
		skin: '#227dc5',
		  btn: ['让我走~','我再想想'] //按钮
		}, function(){
		  window.location = '${ctx}/admin/extend/logout';
		}, function(){
		  
		});
	}
    	layui.use('element', function(){
		  var element = layui.element();
		  
		  //一些事件监听
		  element.on('tab(demo)', function(data){
			console.log(data);
		  });
		});
    </script>
</body>
<!-- InstanceEnd --></html>
