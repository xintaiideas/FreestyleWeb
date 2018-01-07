<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>随变网</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/freestyle.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/album.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/commons/webuploader/webuploader.css" />
	
	<link rel="stylesheet" href="${staticPath}/commons/layer/layui/css/layui.css" media="all">
	
	<style type="text/css">
		.webuploader-pick {
			background: inherit;
		    padding: 0;
		    color: #2279BF;
	    }
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
	
	<script type="text/javascript" src="${staticPath}/commons/webuploader/webuploader.min.js"></script>
</head>
<body>
	<div class="body-main">
		<form class="layui-form" action="">
		
		<div class="layui-form-item">
			<label class="layui-form-label" style="padding-top:0px;">头像</label>
			<div class="layui-input-block" style="text-align:center;">
				<c:if test="${account.headImgId eq null}">
					<img src="${staticPath}/core/image/men_tiny.gif" id="headImage" width="80"/>
					<img src="${staticPath}/core/image/men_tiny.gif" id="headImageThumb" width="40"/>
				</c:if>
				<c:if test="${account.headImgId ne null}">
					<img src="${ctx}/head-image" id="headImage" width="80" height="80"/>
					<img src="${ctx}/head-image" id="headImageThumb" width="40" height="40"/>
				</c:if>
				<a id="btnUpload" style="margin-left:10px;color:#2279BF;cursor:pointer;">更换头像</a>
			</div>
		</div>
		
		  <div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		      <input type="text" disabled value="${account.username}" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">姓名</label>
		    <div class="layui-input-block">
		      <input type="text" name="name" placeholder="请输入姓名" value="${account.realname}" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">邮箱</label>
		    <div class="layui-input-block">
		      <input type="text" id="email" name="email" placeholder="请输入邮箱地址" value="${account.email}" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">电话</label>
		    <div class="layui-input-block">
		      <input type="text" id="tel" name="tel" placeholder="请输入电话号码" value="${account.tel}" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="text-align:right;">
		      <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
		  </div>
		</form>
 	</div>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
	  if(data.field.name.length < 2 || data.field.name.length > 10) {
		  parent.layer.msg('姓名长度在2~10个字符串之间！');
		  return false;
	  }
	  var reg = /\w+[@]{1}\w+[.]\w+/;
	  if(!reg.test($("#email").val())) {
		  parent.layer.msg('邮箱格式不正确！');
		return false;
	  }
	  
	  var reg = /^1[34578]\d{9}$/;
	  if(!reg.test($("#tel").val())) {
		  parent.layer.msg('手机格式不正确！');
		return false;
	  }
	  
	  $.post('${ctx}/modify-person-info',data.field,function(r) {
	    	if(r.code == 'OK') {
	    		parent.layer.msg('修改个人信息成功！');
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

WebUploader.Uploader.register({
    'before-send-file': function(file){
        var uploader = this.owner;
        var deferred = WebUploader.Deferred();
        uploader.md5File(file.source)
            .progress(function(percentage) {
                console.log('Percentage:', percentage);
            })
            .then(function(ret) {
                //偷懒，直接将文件的md5值赋值进file
                file.md5 = ret;
                //取得MD5值后，请求服务器查询对应文件是否已存在（秒传）
                $.ajax({
                   type: 'POST',
                   url: '${ctx}/photo/exist-photo',
                   data: {'md5':file.md5},
                   async:true,//是否使用异步
                   success: function(result) {
                        if (result == 'true')
                        {
//                          uploader.skipFile(file);
                            console.log('秒传：',file);
                        }
                        // 结束此promise, webuploader接着往下走。
                        deferred.resolve();
                   }
                });
            });
        // 返回的是 promise 对象
        return deferred.promise();
    }
    ,'before-send':function(block){
        var deferred = WebUploader.Deferred();
        if (block.file.ret)
        {//此处跳过秒传的文件
            deferred.reject();
        }
        else
        {
            deferred.resolve();
        }
        return deferred.promise();
    }
});

$(function() {
	var uploader = WebUploader.create({
        swf: '${staticPath}/commons/webuploader/Uploader.swf',
        chunked: false,
        pick:{
        	id: '#btnUpload'
        },
        auto:true,
        chunkSize: 512 * 1024,
        server: '${ctx}/upload-head-image',
        accept: {
             title: 'Images',
             extensions: 'gif,jpg,jpeg,bmp,png',
             mimeTypes: 'image/*'
        },
        fileNumLimit: 1,
        fileSizeLimit: 200 * 1024 * 1024,    // 200 M
        fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
    });
	
	uploader.on( 'beforeFileQueued', function( file ) {
		var files = uploader.getFiles();
		for(var index in files) {
			uploader.removeFile(files[index]);	
		}
		
	});
	uploader.on( 'uploadBeforeSend', function(object,data,headers) {
    	data.md5 = object.file.md5;
    });
	uploader.on( 'fileQueued', function( file ) {
	    var $headImageThumb = $('#headImageThumb');
	    var $headImage = $('#headImage');
	    uploader.makeThumb( file, function( error, ret ) {
	        if ( error ) {
	            $li.text('预览错误');
	        } else {
	        	$headImageThumb.attr('src',ret);
	            $headImage.attr('src',ret);
	        }
	    });
		
	});
});
</script>
</body>
</html>