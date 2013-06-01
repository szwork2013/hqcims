<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选择客户</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(":radio").click(function(){
				var radioBox=$("input[name='radio']:checked");
				var id = radioBox.val(), title =radioBox.attr("title");
				if (top.mainFrame.cmsMainFrame){
					top.mainFrame.cmsMainFrame.consumerSelect(id, title);
				}else{
					top.mainFrame.consumerSelect(id, title);
				}
			});
			
			//var hdflag=$("input[name='radio']:checked").val();
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
	<div style="margin:10px;">
	<form:form id="searchForm" modelAttribute="consumer" action="${ctx}/cms/consumer/selectList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="is_special" value="-1"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<input id="btnQuery" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead>
		<tr>
		<th style="text-align:center;">选择</th><th>名称</th><th>助记码</th><th>地址</th><th>店铺名称</th><th>店铺电话</th><th>客户联系电话</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="consumer">
			<tr>
				<td style="text-align:center;"><input type="radio" name="radio" value="${consumer.id}"  title="${consumer.name}" /></td>
				<td>${consumer.name}</td>
				<td>${consumer.code}</td>
				<td>${consumer.address}</td>
				<td>${consumer.shop_name}</td>
				<td>${consumer.shop_phone}</td>
				<td>${consumer.phone}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>
