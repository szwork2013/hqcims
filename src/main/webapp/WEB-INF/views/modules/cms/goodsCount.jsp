<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#code").focus();
			
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
		<li class="active"><a>货物盘点</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="goods" action="${ctx}/cms/goods/count" method="post" enctype="multipart/form-data" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		&nbsp;<a href="${ctx}/cms/goods/import/count" class="btn btn-primary" >导出</a>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>编号</th><th>商品名称</th><th>规格型号</th><th>助记码</th><th>数量</th><th>进货价</th><th>总金额</th><th>备注</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="goods">
			<tr>
			    <td>${goods.id}</td>
				<td><a href="${ctx}/cms/goods/form?id=${goods.id}">${goods.name}</a></td>
                <td>${goods.brand}</td>
				<td>${goods.code}</td>
                <td>${goods.num}</td>
				<td>${goods.purchase}</td>
				<td>${fns:getFloat(goods.num*goods.purchase)}</td>
				<td>${goods.remarks}</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>
