<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>换货管理</title>
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
		<li class="active"><a href="${ctx}/cms/change/">换货列表</a></li>
		<li><a href="${ctx}/cms/change/form">换货添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="change" action="${ctx}/cms/change/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <div>
            <label>客户名称 ：</label><form:input path="consumer.name" htmlEscape="false" maxlength="50" class="input-medium"/>
            &nbsp;
            <label>助&nbsp;记&nbsp;码&nbsp; ：</label>&nbsp;<form:input path="consumer.code" id="code"  htmlEscape="false" maxlength="50" class="input-medium"/>
            &nbsp;
        </div>
        <div style="margin-top:8px;">
            <label>产品名称 ：</label><form:input path="goods.name" htmlEscape="false" maxlength="50" class="input-medium"/>
            &nbsp;
            <label>助&nbsp;记&nbsp;码&nbsp; ：</label>&nbsp;<form:input path="goods.code"   htmlEscape="false" maxlength="50" class="input-medium"/>
            &nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
        </div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr><th>客户名称</th><th>客户助记码</th><th>产品名称</th><th>产品助记码</th><th>数量</th><th>换货时间</th><th>操作人</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="change">
			<tr>
				<td>${change.consumer.name}</td>
                <td>${change.consumer.code}</td>
                <td>${change.goods.name}</td>
                <td>${change.goods.code}</td>
                <td>${change.num}</td>
                <td><fmt:formatDate value="${change.createDate}" type="both"/></td>
                <td>${change.user.name}</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
