<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--
  ~ Copyright (c) 2013.
  ~ whatlookingfor@gmail.com
  --%>

<html>
<head>
	<title>实收列表</title>
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
		<li class="active"><a>实收列表</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="collecting" action="${ctx}/cms/collecting/blist" method="post" class="hidden">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <form:hidden path="consumer.id" />
	</form:form>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>客户名称</th><th>客户助记码</th><th>实收来源</th><th>实收金额</th><th>小额调整</th><th>实收时间</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="collecting">
			<tr>
			    <td>${collecting.consumer.name}</td>
				<td>${collecting.consumer.code}</td>
				<td>${collecting.flag eq 0?'订单实收':'客户还款'}</td>
				<td>${collecting.amount}</td>
				<td>${collecting.amount1}</td>
				<td><fmt:formatDate value="${collecting.create_date}" type="both"/></td>
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
