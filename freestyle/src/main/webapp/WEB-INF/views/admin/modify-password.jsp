<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>随变网</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/freestyle.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/album.css" />
	
	<link rel="stylesheet" href="${staticPath}/commons/layer/layui/css/layui.css" media="all">
	
	<style type="text/css">
		.body-main {
			padding-top:30px;
			padding-left:60px;
			padding-right:60px;
		}
		/* .layui-input,.layui-textarea,.aihao {
			width:330px;
		}
		.layui-input-block {
			text-align:center;
			margin-left:0px;
		} */
	</style>
	
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layui/layui.js"></script>
</head>
<body>
	<div class="body-main">
		<form class="layui-form" action="">
		  <div class="layui-form-item">
		      <input type="password" name="newPassword" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
		  </div>
		  <div class="layui-form-item">
		      <input type="password" name="newPwdAgain" required lay-verify="required" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
		  </div>
		  <div class="layui-form-item" style="text-align:center;">
		      <button class="layui-btn" lay-submit lay-filter="formDemo">立即修改</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		  </div>
		</form>
 	</div>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
	  if(data.field.newPassword != data.field.newPwdAgain) {
		  parent.layer.msg('两次密码输入不一样');
		  return false;
	  }
	  data.field.accountId = '${accountId}';
	$.post('${ctx}/admin/account/modify-password',data.field,function(r) {
	    	if(r.code == 'OK') {
	    		parent.layer.msg('修改密码成功！');
	    		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	    		parent.layer.close(index);
	    	}else {
	    		parent.layer.msg(r.message);
	    	}
	    },'json');
	    return false;
  });
});
</script>
</body>
</html>