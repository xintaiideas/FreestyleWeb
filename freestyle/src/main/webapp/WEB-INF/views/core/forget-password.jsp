<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>忘记密码 - 随变网</title>
    <!-- 设置页面编码，浏览器将以此编码解析此网页 -->
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${staticPath}/core/css/login.css" />
</head>
<body>
	<!--使用video标签加载pointbreak.mp4视频作为背景 样式 宽高百分百，设置了autoplay 自动播放 loop 循环 muted 静音 object-fit:fill; 视频铺满-->
	<video class="pointbreak" autoplay loop muted>
		<source src="${staticPath}/core/media/pointbreak.mp4" />
	</video>
    <!--透明遮罩层，防止video标签在浏览器中显示小窗口播放-->
    <div class="mask"></div>
	<div class="logo"></div>
    <!-- 登录窗口 -->
	<div class="login-body" style="height:250px;">
    	<!--将h1设为inline-block:将对象呈现为inline对象，但是对象的内容作为block对象呈现-->
		<h1 style="display:inline-block;">忘记密码</h1>
		<span style="margin-left:10px;">想起密码？<a href="${ctx}/login" class="btn btn-link">立即登录 →</a></span>
        <!--用户名和密码输入框，placeholder 属性提供可描述输入字段预期值的提示信息-->
        <p><input class="input-text" type="text" name="username" placeholder="请输入用户名" /></p>
        <p>
        	<input  class="input-text" type="text" name="checkcode" placeholder="请输入验证码" style="width:60%;"/>
        	<img src="" alt="点击刷新" />
        </p>
		<p>
			<button type="button" class="btn btn-blue">找回密码</button>
		</p>
    </div>
</body>
</html>