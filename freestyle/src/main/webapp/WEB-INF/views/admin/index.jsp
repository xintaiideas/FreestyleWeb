<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html><!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- InstanceBeginEditable name="doctitle" -->
	<title>管理后台 - 随变网</title>
	<!-- InstanceEndEditable -->
    <link rel="stylesheet" href="${staticPath}/commons/layer/layui/css/layui.css" media="all">
	<link href="${staticPath}/admin/css/index.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${staticPath}/commons/layer/layui/layui.js"></script>
	<!-- InstanceBeginEditable name="head" -->
    <link rel="stylesheet" type="text/css" href="${staticPath}/admin/css/animation.css" />
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
    	<div class="a-ring" style="width:610px;margin:0 auto;color:#999;margin-top:200px;">
        	<p>
                <span style="font-size:30px;">随变网 —— 一个聚焦大学生群体的社交网站</span>
            </p>
            <div style="width:450px;margin:10px auto;font-size:18px;">开发团队：连嘉阳、黄志兵、陈嘉敏、牛东苒、阮荧</div>
        </div>
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
	
    	layui.use(['element','layer'], function(){
		  var element = layui.element();
		  
		  //一些事件监听
		  element.on('tab(demo)', function(data){
			console.log(data);
		  });
		});
    </script>
</body>
<!-- InstanceEnd --></html>
