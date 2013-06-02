<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<html>
<head>
	<title>订单打印</title>
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
		<table>
			<tr>
			<td id="left">
					<br>
					<h3>西安诚业(华清路店)  免费电话：4000297002</h3>
					<h5><b>客户：${collecting.consumer.name} &nbsp;&nbsp;&nbsp;  ${collecting.consumer.address}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址车号： </b></h5>
			</td>
			
			<td id="right">
				<table border="2" id="right" cellspacing="0">
				<tr><td>单据号：<fmt:formatDate value="${collecting.receivable.order.create_date}" type="date"/>—${collecting.receivable.order.id}
				<br>
				业务员：${collecting.receivable.order.user.name}  &nbsp;&nbsp;&nbsp;<fmt:formatDate value="${collecting.receivable.order.create_date}" dateStyle="long"/>
				<br>
				总件数：<c:out value="${fn:length(list)}"></c:out> </td></tr>
				</table>
			</td>
			</tr>
		</table>
	</td></tr>
	
	
	<tr><td>
		<table  width="100%" border="2" cellspacing="0">
		<tr align="center">
			<th width="4%"></th>
			<th width="26%">商品名</th>
			<th width="10%">规格</th>
			<th width="21%" colspan="2">数量</th>
			<th width="10%">单价</th>
			<th width="15%">金额</th>
			<th width="10%">库号</th>
		</tr>
		<c:forEach items="${list}" var="order" varStatus="st" >
		<tr align="center">
			<td width="4%">${st.count}</td>
			<td width="26%">${order.goods.name}</td>
			<td width="10%">${order.goods.brand}</td>
			<td width="18%">${order.num}</td>
			<td width="8%">${order.goods.unit}</td>
			<td width="10%">${order.sale}</td>
			<td width="15%">${fns:getFloat(order.num*order.sale)}</td>
			<td width="10%">华清路店</td>
		</tr>
		</c:forEach>
		<tr><td colspan="8">
		总计金额：${collecting.receivable.amount} &nbsp;&nbsp;&nbsp;
		折让金额：${collecting.amount1} &nbsp;&nbsp;&nbsp;
		结算金额：${fns:getFloat(collecting.receivable.amount-collecting.amount1)} &nbsp;&nbsp;&nbsp;
		</td></tr>
		
		</table>
	</td></tr>
	
	<tr><td>
	${fns:getDictLabel('0', 'bank', '')}
	<br>
	${fns:getDictLabel('0', 'address', '')}
	<br>
	${fns:getDictLabel('0', 'others', '')}
	</td></tr>
		
</table>
		
</td>
</tr>
</table>
</body>
</html>
