<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>注册 - 随变网</title>
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
	<video class="pointbreak" autoplay loop muted>
		<source src="${staticPath}/core/media/pointbreak.mp4" />
	</video>
	
    <!--透明遮罩层，防止video标签在浏览器中显示小窗口播放-->
    <div class="mask"></div>
	<div class="logo"></div>
	
    <!-- 登录窗口 -->
	<div class="login-body" style="height:390px;margin-top:-200px;">
	
		<form id="registerForm" method="post">
		
	    	<!--将h1设为inline-block:将对象呈现为inline对象，但是对象的内容作为block对象呈现-->
			<h1 style="display:inline-block;">注册</h1>
			<span style="margin-left:10px;">已有账号？<a href="${ctx}/login" class="btn btn-link">立即登录 →</a></span>
			
	        <!--用户名和密码输入框，placeholder 属性提供可描述输入字段预期值的提示信息-->
	        <p><input class="input-text" type="text" id="username" name="username" title="请输入用户名 长度为3~10个字符" placeholder="请输入用户名" /></p>
			<p><input  class="input-text" type="password" id="password" name="password" title="请输入密码 长度为6~16个字符" placeholder="请输入密码" /></p>
	        <p><input  class="input-text" type="password" id="repwd" name="repwd" title="请再次输入密码" placeholder="请再次输入密码" /></p>
	        <p>
	        	<img id="captchca" src="${ctx}/kaptcha.jpg" title="点击刷新" alt="点击刷新" style="height:46px;width:45%;vertical-align:middle;cursor:pointer;" />
	        	<input  class="input-text" type="text" name="captchca" id="checkcode" title="请输入验证码" placeholder="请输入验证码" style="width:40%;vertical-align:middle;" />
	        </p>
			<p>
				<button id="btnRegister" type="button" class="btn btn-blue">注册</button>
			</p>
			
		</form>
    </div>
    <script type="text/javascript">
    $(function() {
    	//	刷新验证码
		$('#captchca').click(function() {
			$(this).attr('src','${ctx}/kaptcha.jpg?' + Math.floor(Math.random() * 100));
		});
    	//	
    	$('#registerForm').validate({
    		onkeyup:false,
    		onfocusout:false,
    		onclick:false,
    		onsubmit:true,
    		rules: {
    		      username: {
    		        required: true,
    		        minlength: 3,
    		        maxlength: 10,
    		        remote : {
  		    		  url : '${ctx}/check-username',
  		    		  type : 'post'
  		    	  	}
    		      },
    		      password: {
      		        required: true,
      		        minlength: 6,
      		      	maxlength: 16
      		      },
      		      repwd: {
    		        required: true,
    		        equalTo : '#password'
    		      },
    		      captchca : {
    		    	  required: true,
    		    	  minlength:5,
    		    	  maxlength: 5,
    		    	  remote : {
    		    		  url : '${ctx}/check-captchca',
    		    		  type : 'post'
    		    	  }
    		      }
    		},
    		messages : {
    			repwd : {
    				equalTo : '两次密码输入不一致'
    			},
    			username : {
    				remote : '用户名已存在'
    			},
    			captchca : {
    				minlength : '验证码输入错误',
    				maxlength : '验证码输入错误',
    				remote : '验证码输入错误'
    			}
    		},
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
    	$('#btnRegister').click(function() {
    		$('#registerForm').ajaxSubmit({
        		beforeSubmit:function() {
        			var r =  $("#registerForm").valid();
        			if(!r) {
        				return false;
        			}
        			var reg =/^[a-zA-Z][a-zA-Z0-9_]{5}$/;
        			var v = $('#username').val();
        			if(!reg.test(v)){
        				layer.msg('用户名格式不正确', {
        	        		  offset: 't',
        	        		  anim: 6
        	        		});
        				return false;
        			}
        			return true;
        		},
    	        success: function (data) {
    	        	var obj = $.parseJSON(data);
    	        	if(obj.code == 'OK') {
    	        		layer.alert('注册成功!立即登录',{skin: 'layui-layer-lan'}, function(){
							window.location = '${ctx}/login';
    	        		});
    	        	}else {
    	        		layer.msg('注册失败', {
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