<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实收管理</title>
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
		<li class="active"><a>实收列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="collecting" action="${ctx}/cms/collecting/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="consumer.name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="consumer.code" id="code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>客户名称</th><th>客户助记码</th><th>实收来源</th><th>实收金额</th><th>小额调整</th><th>实收时间</th><th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="collecting">
			<tr>
			    <td>${collecting.consumer.name}</td>
				<td>${collecting.consumer.code}</td>
				<td>
                    <c:choose>
                        <c:when test="${collecting.flag==0}">订单实收</c:when>
                        <c:when test="${collecting.flag==1}">客户还款</c:when>
                        <c:otherwise>退货</c:otherwise>
                    </c:choose>
                </td>
				<td>${collecting.amount}</td>
				<td>${collecting.amount1}</td>
				<td><fmt:formatDate value="${collecting.create_date}" type="both"/></td>
                <td>
                    <c:if test="${collecting.flag==0}">
                        <a href="#">打印订单</a>
                    </c:if>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
