<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<html>
<head>
	<title>购物管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#code").focus();
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
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/cms/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <div>
		<label>客户名称 ：</label><form:input path="consumer.name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助&nbsp;记&nbsp;码&nbsp; ：</label>&nbsp;<form:input path="consumer.code" id="code"  htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
        </div>
        <div style="margin-top:8px;">
        <label>订单编号 ：</label><form:input path="query" htmlEscape="false" maxlength="50" class="input-medium"/>
        &nbsp;
		<label>订单状态 ：</label>
		<form:select path="status" class="input-medium">
		   <form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			
		</form:select>
		&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
        </div>
	</form:form>
	<tags:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>订单编号</th><th>客户名称</th><th>客户助记码</th><th>地址</th><th>店铺电话</th><th>创建时间</th><th>操作人</th><th>总金额</th><th>订单类型</th><th>状态</th><th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="order" varStatus="st" >
			<tr>
				<td><a href="${ctx}/cms/order/detailList?id=${order.id}"><fmt:formatDate value="${order.create_date}" type="date"/>—${order.id}</a></td>
				<td>${order.consumer.name}</td>
				<td>${order.consumer.code}</td>
				<td>${order.consumer.address}</td>
				<td>${order.consumer.phone}</td>
				<td><fmt:formatDate value="${order.create_date}" type="both"/></td>
				<td>${order.user.name}</td>
				<td>${order.total}</td>
                <td>
                    <c:choose>
                        <c:when test="${order.type==0}">正常订单</c:when>
                        <c:otherwise>退货订单</c:otherwise>
                    </c:choose>
                </td>
				<td>
                    <c:choose>
                        <c:when test="${order.status==1}">无效订单</c:when>
                        <c:when test="${order.status==0}">已确认订单</c:when>
                        <c:otherwise>已纳入应收</c:otherwise>
                    </c:choose>
				</td>
				
				<td>
				<c:choose>
				<c:when test="${order.status==1}">
				<a href="${ctx}/cms/order/delete?id=${order.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				<a href="${ctx}/cms/order/setStatus?id=${order.id}" onclick="return confirmx('确认要将该订单设置为有效吗？', this.href)">置为有效</a>
				</c:when>
				<c:when test="${order.status==0}">
				<a href="${ctx}/cms/order/doReceivable?id=${order.id}" onclick="return confirmx('确认要纳入应收吗？', this.href)">应收</a>
                    <a href="${ctx}/cms/order/doCollecting?id=${order.id}" onclick="return confirmx('确认要进行现金实收吗？', this.href)">实收</a>
                    <a href="${ctx}/cms/order/doBCollecting?id=${order.id}" onclick="return confirmx('确认要进行代收款吗？', this.href)">代收</a>
                    <a href="${ctx}/cms/order/doBalance?id=${order.id}" onclick="return confirmx('确认要纳入欠款吗？', this.href)">欠款</a>
				<a href="${ctx}/cms/order/setStatus1?id=${order.id}" onclick="return confirmx('确认要将该订单设置为无效吗？', this.href)">置为无效</a>
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
