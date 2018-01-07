<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<html>
<head>
    <title><sitemesh:write property='title' /> - ltcms</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link  type="text/css" rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css" />
	<link  type="text/css" rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap-theme.min.css" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <sitemesh:write property='head' />

	<style type="text/css">
	.admin_menu {
	    list-style-type: none;
	}
	
	.admin_menu li {
	    padding-top:5px;
	    padding-bottom:5px;
	}
	</style>
</head>
<body>
    <div style="background:#D6715A;height:80px;position:absolute;top:0px;left:0px;right:0px;">
        <div style="width:200px;height:60px;position:absolute;top:10px;left:20px;">
            LOGO
        </div>
        <div style="width:200px;height:60px;position:absolute;top:10px;right:20px;">
            User Bar
        </div>
    </div>
    <div style="background:#f4f4f4;border-right:1px #CCC solid;width:200px;position:absolute;top:80px;left:0px;bottom:0px;">
        <ul class="admin_menu">
            <li><a><i></i><span>菜单项一</span></a></li>
            <li><a><i></i><span>菜单项一</span></a></li>
            <li><a><i></i><span>菜单项一</span></a>
                <ul class="admin_menu">
                    <li><a><i></i><span>菜单项一</span></a></li>
		            <li><a><i></i><span>菜单项一</span></a></li>
		            <li><a><i></i><span>菜单项一</span></a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div style="position:absolute;top:80px;left:200px;bottom:0px;right:0px;">
        <!-- 面包屑导航 -->
        <ol class="breadcrumb" style="border-bottom:#eee 1px solid;box-shadow: 5px 2px 10px #eee;">
            <li><a href="#">Home</a></li>
            <li><a href="#">2013</a></li>
            <li class="active">十一月</li>
        </ol>
        <div style="position:absolute;top:36px;left:0px;bottom:0px;right:0px;overflow-y: scroll;">
	       <sitemesh:write property='body' />
	    </div>
	</div>
</body>
</html>