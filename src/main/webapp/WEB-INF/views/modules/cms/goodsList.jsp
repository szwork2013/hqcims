<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
		<li class="active"><a href="${ctx}/cms/goods/">商品列表</a></li>
		<li><a href="${ctx}/cms/goods/form">商品添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="goods" action="${ctx}/cms/goods/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>名称</th><th>助记码</th><th>进货价</th><th>销售价</th><th>数量</th><th>单位</th><th>产地</th><th>规格</th><th>备注</th><th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="goods">
			<tr>
				<td><a href="${ctx}/cms/goods/form?id=${goods.id}">${goods.name}</a></td>
				<td>${goods.code}</td>
				<td>${goods.purchase}</td>
				<td>${goods.sale}</td>
				<td>${goods.num}</td>
				<td>${goods.unit}</td>
				<td>${goods.origin}</td>
				<td>${goods.brand}</td>
				<td>${goods.remarks}</td>
				<td>
    				<a href="${ctx}/cms/goods/form?id=${goods.id}">修改</a>
					<a href="${ctx}/cms/goods/delete?id=${goods.id}" onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
