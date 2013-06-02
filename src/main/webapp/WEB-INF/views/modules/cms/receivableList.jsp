<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应收管理</title>
	<meta name="decorator" content="default"/>
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
		<li class="active"><a>应收列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="receivable" action="${ctx}/cms/receivable/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="consumer.name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="consumer.code" id="code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>订单编号 ：</label><form:input path="order.id" htmlEscape="false"  class="input-medium"/>
		&nbsp;

		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>客户名称</th><th>客户助记码</th><th>订单编号</th><th>总金额</th><th>是否代收客户</th>
		<th>应收时间</th><th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="receivable">
			<tr>
				<td>${receivable.consumer.name}</td>
				<td>${receivable.consumer.code}</td>
				<td><a href="${ctx}/cms/order/detailList?id=${receivable.order.id}">${receivable.order.id}</a></td>
				<td>${receivable.amount}</td>
				<td>${receivable.consumer.is_consumer eq 1?'否':'是'}</td>
				<td><fmt:formatDate value="${receivable.create_date}" type="both"/></td>
				<td>
				 <c:if test="${receivable.status==0}">
    				<a href="${ctx}/cms/balance/add?id=${receivable.id}" onclick="return confirmx('确认要纳入欠款吗？', this.href)">纳入欠款</a>
    				<a href="${ctx}/cms/balance/add?id=${receivable.id}" onclick="return confirmx('确认要纳入代收吗？', this.href)">纳入代收</a>
					<a href="${ctx}/cms/receivable/form?id=${receivable.id}" >现金实收</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
