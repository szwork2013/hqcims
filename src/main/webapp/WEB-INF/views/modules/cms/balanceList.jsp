<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欠款管理</title>
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
		<li class="active"><a>欠款列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="balance1" action="${ctx}/cms/balance1/" method="post" class="breadcrumb form-search">
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
		<th>客户名称</th><th>助记码注</th><th>欠款总额</th><th>应收总额</th><th>实收总额</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="balance1">
			<tr>
				
				<td>${balance1.name}</td>
				<td>${balance1.code}</td>
				<td>${balance1.bamount}</td>
				<c:choose>
				<c:when test="${balance1.ramount>0}">
				<td><a href="${ctx}/cms/receivable/list?consumer.id=${balance1.id}">${balance1.ramount}</a></td>
				</c:when>
				<c:otherwise>
				<td>${balance1.ramount}</td>
				</c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${balance1.camount>0}">
				<td>${balance1.camount}</td>
				</c:when>
				<c:otherwise>
				<td>${balance1.camount}</td>
				</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
