<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>管理后台登录 - 随变网</title>
    <!-- 设置页面编码，浏览器将以此编码解析此网页 -->
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${staticPath}/core/css/login.css" />
	
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/jquery-validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/jquery-validate/messages_zh.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
</head>
<body>
	<!--使用video标签加载pointbreak.mp4视频作为背景 样式 宽高百分百，设置了autoplay 自动播放 loop 循环 muted 静音 object-fit:fill; 视频铺满-->
	
    <!--透明遮罩层，防止video标签在浏览器中显示小窗口播放-->
    <div class="mask" style="background:#70B2E0;"></div>
	<div class="logo"></div>
    <!-- 登录窗口 -->
	<div class="login-body">
		<form id="loginForm" method="post" action="${ctx}/admin/extend/login">
	    	<!--将h1设为inline-block:将对象呈现为inline对象，但是对象的内容作为block对象呈现-->
			<h1 style="display:inline-block;">管理员登录</h1>
	        <!--用户名和密码输入框，placeholder 属性提供可描述输入字段预期值的提示信息-->
	        <p><input class="input-text" type="text" name="username" placeholder="请输入用户名" title="请输入用户名" required /></p>
			<p><input  class="input-text" type="password" name="password" placeholder="请输入密码" title="请输入密码" required /></p>
			<p>
	        	<!--记住密码复选框-->
				<div class="checkbox-group" style="display:inline-block;">
	                <label class="lbl-rem-pwd">
	                    <input name="rememberMe" class="ck-rem-pwd" type="checkbox" checked="checked" />
	                     <span style="margin-left:5px;">记住密码</span>
	                </label>
				</div>
				<%-- <a href="${ctx}/forget-password" style="margin-left:170px;" class="btn btn-link">忘记密码?</a> --%>
			</p>
			<p>
				<button type="button" id="btnLogin" class="btn btn-blue">登录</button>
			</p>
		</form>
    </div>
    
    <script type="text/javascript">
    $(function() {
    	$('#loginForm').validate({
    		onkeyup:false,
    		onfocusout:false,
    		onclick:false,
    		onsubmit:true,
    		//	发生错误的时候怎么提示
    		errorPlacement: function(error, element) {
    			layer.tips(error.html(), element,
    					{
    						tipsMore: true,
    						tips: [2, '#3595CC']
    					}
    			);
    	   	}
    	});
    	//	注册表单提交
    	$('#btnLogin').click(function() {
    		$('#loginForm').ajaxSubmit({
        		beforeSubmit:function() {
        			return $("#loginForm").valid();
        		},
    	        success: function (data) {
    	        	var obj = $.parseJSON(data);
    	        	if(obj.code == 'OK') {
						window.location = '${ctx}/admin/extend/main';
    	        	}else {
    	        		layer.msg('用户名或密码错误', {
    	        		  offset: 't',
    	        		  anim: 6
    	        		});
    	        	}
    	        }
    	    });
    	});
    	
    });
    </script>
</body>
</html>