<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日销售统计</title>
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
		function doPrint(){
			var url="${ctx}/cms/count/print?queryDate="+$("#queryDate").val();
			$("#doP").attr("href",url);
			$("#doP").click();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>日销售统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="count" action="${ctx}/cms/count/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>选择日期：</label><input id="queryDate" name="queryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
         value="${queryDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		<a id="doP" class="btn btn-primary" href="#" target="_blank" onclick="doPrint()">打印</a>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>营业员</th><th>单据号</th><th>客户名称</th><th>性质</th><th>销售金额</th><th>现金总额</th><th>代收货款</th><th>欠款总额</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="collecting">
			<tr>
			    <td>${collecting.receivable.order.user.name}</td>
				<td><fmt:formatDate value="${collecting.receivable.order.create_date}" type="date"/>—${collecting.receivable.order.id}</td>
                <td>${collecting.consumer.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${collecting.flag==0}">现金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:when>
                        <c:when test="${collecting.flag==2}">退货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:when>
                        <c:when test="${collecting.flag==3}">代收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:when>
                        <c:when test="${collecting.flag==4}">欠款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${collecting.flag==1}">0
                        <c:set var="amount1" value="${amount1+0}"></c:set>
                        </c:when>
                        <c:otherwise>${collecting.receivable.amount}
                        <c:set var="amount1" value="${collecting.receivable.amount+amount1}"></c:set>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                ${collecting.amount}
                <c:set var="amount2" value="${collecting.amount+amount2}"></c:set>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${collecting.flag==3}">
                        ${collecting.receivable.amount}
                        <c:set var="amount3" value="${collecting.receivable.amount+amount3}"></c:set>
                        </c:when>
                        <c:otherwise>0
                        <c:set var="amount3" value="${amount3+0}"></c:set>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${collecting.flag==4}">
                        ${collecting.receivable.amount}
                        <c:set var="amount4" value="${collecting.receivable.amount+amount4}"></c:set>
                        </c:when>
                        <c:otherwise>0
                        <c:set var="amount4" value="${amount4+0}"></c:set>
                        </c:otherwise>
                    </c:choose>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
