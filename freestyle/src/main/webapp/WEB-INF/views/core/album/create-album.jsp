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
		body {
			padding-top:30px;
		}
		.layui-input,.layui-textarea {
			width:330px;
		}
		.layui-input-block {
			text-align:center;
			margin-left:0px;
		}
	</style>
	
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layui/layui.js"></script>
</head>
<body>
	<form class="layui-form" action="${ctx}/person-info" method="post">
	  <div class="layui-form-item">
	    <label class="layui-form-label">相册名称</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" required lay-verify="required" placeholder="请输入相册名称" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">相册描述</label>
	    <div class="layui-input-block">
	      <textarea name="description" placeholder="请输入描述" class="layui-textarea"></textarea>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
 
<script>
//Demo
layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    $.post('${ctx}/album/create-album',data.field,function(r) {
    	if(data.field.name.length > 5) {
    		parent.layer.msg('相册名称不能超过5个字符！');
    		return false;
    	}
    	if(data.field.description.length > 50) {
    		parent.layer.msg('相册名称不能超过50个字符！');
    		return false;
    	}
    	if(r.code == 'OK') {
    		parent.layer.msg('创建相册成功！');
    		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    		parent.layer.close(index);
    		parent.location.reload();
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