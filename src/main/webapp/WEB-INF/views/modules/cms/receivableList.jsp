<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/receivable/">应收列表</a></li>
		<li><a href="${ctx}/cms/receivable/form">应收添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="receivable" action="${ctx}/cms/receivable/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr><th>名称</th><th>备注</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="receivable">
			<tr>
				<td><a href="${ctx}/cms/receivable/form?id=${receivable.id}">${receivable.name}</a></td>
				<td>${receivable.remarks}</td>
				<td>
    				<a href="${ctx}/cms/receivable/form?id=${receivable.id}">修改</a>
					<a href="${ctx}/cms/receivable/delete?id=${receivable.id}" onclick="return confirmx('确认要删除该应收吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
