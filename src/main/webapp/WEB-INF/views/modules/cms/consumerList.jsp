<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户维护管理</title>
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
		<li class="active"><a href="${ctx}/cms/consumer/">客户列表</a></li>
		<li><a href="${ctx}/cms/consumer/form">客户添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="consumer" action="${ctx}/cms/consumer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead>
		<tr>
		<th>名称</th><th>助记码</th><th>地址</th><th>是否代收客户</th><th>是否供应商客户</th><th>店铺名称</th><th>店铺电话</th><th>客户联系电话</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="consumer">
			<tr>
				<td><a href="${ctx}/cms/consumer/form?id=${consumer.id}">${consumer.name}</a></td>
				<td>${consumer.code}</td>
				<td>${consumer.address}</td>
				<td>${consumer.is_consumer eq 1?'否':'是'}</td>
				<td>${consumer.is_provider eq 1?'否':'是'}</td>
				<td>${consumer.shop_name}</td>
				<td>${consumer.shop_phone}</td>
				<td>${consumer.phone}</td>
				<td>
    				<a href="${ctx}/cms/consumer/form?id=${consumer.id}">修改</a>
					<a href="${ctx}/cms/consumer/delete?id=${consumer.id}" onclick="return confirmx('确认要删除该客户维护吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
