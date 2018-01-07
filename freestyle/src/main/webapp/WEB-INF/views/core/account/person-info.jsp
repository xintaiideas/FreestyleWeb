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
		    <label class="layui-form-label">照片名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="title" required lay-verify="required" placeholder="请输入照片名称" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">上传至相册</label>
		    <div class="layui-input-block">
		      <select name="interest" lay-filter="aihao">
		      	<c:forEach items="${list}" var="item">
		        	<option value="${item.id}">${item.name}</option>
		        </c:forEach>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">照片描述</label>
		    <div class="layui-input-block">
		      <textarea name="desc" placeholder="请输入描述" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		</form>
 	</div>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
</script>
</body>
</html>