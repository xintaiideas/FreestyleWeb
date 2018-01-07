<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>随变网</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" style="text/css" href="${staticPath}/core/css/upload.css" />
	<link rel="stylesheet" style="text/css" href="${staticPath}/commons/webuploader/webuploader.css" />
	
	<script type="text/javascript">
		var _ctx = '${ctx}';
		var _staticPath = '${staticPath}';
		var _albumId = '${albumId}';
	</script>
	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/layer/layer.js"></script>
	<script type="text/javascript" src="${staticPath}/commons/webuploader/webuploader.min.js"></script>
	<script type="text/javascript" src="${staticPath}/core/js/upload.js"></script>
</head>
<body>
	
<div id="wrapper">
    <div id="container">
        <!--头部，相册选择和格式选择-->

        <div id="uploader">
            <div class="queueList">
                <div id="dndArea" class="placeholder">
                    <div id="filePicker"></div>
                    <p>或将照片拖到这里，单次最多可选300张</p>
                </div>
            </div>
            <div class="statusBar" style="display:none;">
                <div class="progress">
                    <span class="text">0%</span>
                    <span class="percentage"></span>
                </div><div class="info"></div>
                <div class="btns">
                    <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>