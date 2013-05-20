<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--
  ~ Copyright (c) 2013.
  ~ whatlookingfor@gmail.com
  --%>

<html>
<head>
	<title>应收列表</title>
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
		<li class="active"><a>应收列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="receivable" action="${ctx}/cms/receivable/blist" method="post" class="hidden">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <form:hidden path="consumer.id" />
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>客户名称</th><th>客户助记码</th><th>是否代收客户</th><th>总金额</th><th>订单编号</th>
		<th>应收时间</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="receivable">
			<tr>
				<td>${receivable.consumer.name}</td>
				<td>${receivable.consumer.code}</td>
				<td>${receivable.consumer.is_consumer eq 1?'否':'是'}</td>
				<td>${receivable.amount}</td>
				<td>
				<a href="${ctx}/cms/order/detailList?id=${receivable.order.id}">${receivable.order.id}</a></td>
				<td><fmt:formatDate value="${receivable.create_date}" type="both"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
    <div class="form-actions">
        <div align="center">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>&nbsp;&nbsp;
        </div>
    </div>

</body>
</html>
