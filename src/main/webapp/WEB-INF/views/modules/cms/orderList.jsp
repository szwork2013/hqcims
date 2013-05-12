<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<html>
<head>
	<title>购物管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#chkAll").bind("click", function () {
		                $("[name = goods_id]:checkbox").attr("checked", this.checked);
		         });
			 
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
		<li class="active"><a>订单管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/cms/order/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="consumer.name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="consumer.code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>订单状态 ：</label>
		<form:select path="status" class="input-medium">
			<form:option value="-1" label="全部"/>
			<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>商品名称</th><th>商品助记码</th><th>总金额</th><th>状态</th><th>创建时间</th><th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="order" varStatus="st" >
			<tr>
				<td>${order.consumer.name}</td>
				<td>${order.consumer.code}</td>
				<td>${order.total}</td>
				<td>
				<c:choose>
				<c:when test="${order.status==1}">无效订单</c:when>
				<c:when test="${order.status==0}">已确认订单</c:when>
				<c:otherwise>已进行应收</c:otherwise>
				</c:choose>
				</td>
				<td><fmt:formatDate value="${order.create_date}" type="both"/></td>
				<td>
				<a href="${ctx}/cms/order/detailList?id=${order.id}">查看</a>
				<c:choose>
				<c:when test="${order.status==1}">
				<a href="${ctx}/cms/order/delete?id=${order.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				<a href="${ctx}/cms/order/setStatus?id=${order.id}" onclick="return confirmx('确认要将该订单设置为有效吗？', this.href)">置为有效</a>
				</c:when>
				<c:when test="${order.status==0}">
				<a href="${ctx}/cms/order/doReceivable?id=${order.id}" onclick="return confirmx('确认要将进行应收吗？', this.href)">进行应收</a>
				<!--  
				<a href="${ctx}/cms/order/doPrint?id=${order.id}" onclick="return confirmx('确认要将进行应收吗？', this.href)">打印订单</a>
				-->
				</c:when>
				<c:otherwise></c:otherwise>
				</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>
