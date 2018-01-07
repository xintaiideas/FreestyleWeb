<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="SysUrl" /> - ${appName}</title>
    </head>
    <body>
		<!-- 组合条件查询表单 -->
		<div style="margin-top:20px;">
			<form class="form-inline" role="form" method="get" action="" style="margin-left:20px;">
			   <input type="text" class="form-control" name="url" value="${url}" style="height:30px;" placeholder="输入URL进行查询">
		       <button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>
		<!-- 数据列表 -->
        <table class="table table-striped">
           <thead>
              <tr>
                 <th>序号</th>
                 <th>URL</th>
                 <th>备注</th>
                 <th>创建时间</th>
                 <th>更新时间</th>
              </tr>
           </thead>
           <tbody>
                <c:forEach items="${page.rows}" varStatus="status" var="item">
              <tr>
                 <td>${status.index + 1}</td>
                 <td>${item.url}</td>
                 <td>${item.remark}</td>
                 <td><fmt:formatDate value="${item.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                 <td><fmt:formatDate value="${item.updatedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              </tr>
              </c:forEach>
           </tbody>
        </table>
        <!-- 分页器 -->
        <div id="pagination" style="text-align: right;margin-right:20px;">
          <ul class="pagination">
            <c:if test="${page.pageIndex eq 1}">
                <li class="disabled"><a href="#">&laquo;</a></li>
            </c:if>
            <c:if test="${page.pageIndex ne 1}">
                <li><a href="${ctx}/admin/sys-url?pageIndex=${page.pageIndex-1}&rows=10">&laquo;</a></li>
            </c:if>
            
            <c:forEach var="item" varStatus="status" begin="1" end="${page.totalPageNum}">
                <c:if test="${page.pageIndex eq status.index}">
                    <li class="active"><a href="#">${status.index}</a></li>
                </c:if>
                <c:if test="${page.pageIndex ne status.index}">
                    <li><a href="${ctx}/admin/sys-url?pageIndex=${status.index}&rows=10">${status.index}</a></li>
                </c:if>
            </c:forEach>
            
            <c:if test="${page.pageIndex eq page.totalPageNum}">
                <li class="disabled"><a href="#">&raquo;</a></li>
            </c:if>
            <c:if test="${page.pageIndex ne page.totalPageNum}">
                <li><a href="${ctx}/admin/sys-url?pageIndex=${page.pageIndex+1}&rows=10">&raquo;</a></li>
            </c:if>
          </ul>
        </div>
    </body>
</html>
