<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<html>
<head>
	<title>业绩明细打印</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	table#container{
	width:800px;
	<!--color: #336699; -->
	}
	td#left { height:80px; width:550px;}
	table#right {height:80px; width:240px;}
	</style>
</head>
<body>
<table><tr><td width="20px"></td><td>
<table id="container"  cellspacing="0">

<tr><td>

<h3>营业员业绩明细表</h3>

</td></tr>
	
	
	<tr><td>
		<table  width="100%" border="2" cellspacing="0" style="font-size:14px;">
		<tr align="center">
		<th>营业员</th><th>单据号</th><th>客户名称</th><th>性质&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th>销售金额</th><th>现金总额</th><th>代收货款</th><th>欠款总额</th>
		</tr>
		<c:set var="amount1" value="0" scope="page"></c:set>
		<c:set var="amount2" value="0" scope="page"></c:set>
		<c:set var="amount3" value="0" scope="page"></c:set>
		<c:set var="amount4" value="0" scope="page"></c:set>
		<c:forEach items="${list}" var="collecting">
			<tr align="center">
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
		<tr align="center">
		<td colspan="4">合计:共<c:out value="${fn:length(list)}"></c:out>笔</td>
		<td>${amount1}</td>
		<td>${amount2}</td>
		<td>${amount3}</td>
		<td>${amount4}</td>
		</tr>
		
		</table>
	</td></tr>
	

		
</table>
		
</td>
</tr>
</table>
</body>
</html>
