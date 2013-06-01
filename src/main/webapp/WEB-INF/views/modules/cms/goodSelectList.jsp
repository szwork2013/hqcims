<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--
  ~ Copyright (c) 2013.
  ~ whatlookingfor@gmail.com
  --%>

<html>
<head>
	<title>选择产品</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(":radio").click(function(){
				var radioBox=$("input[name='radio']:checked");
				var id = radioBox.val(), title =radioBox.attr("title");
				if (top.mainFrame.cmsMainFrame){
					top.mainFrame.cmsMainFrame.goodSelect(id, title);
				}else{
					top.mainFrame.goodSelect(id, title);
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
	<form:form id="searchForm" modelAttribute="goods" action="${ctx}/cms/goods/selectList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
        &nbsp;
        <label>助记码 ：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead>
		<tr>
		<th style="text-align:center;">选择</th>
        <th>商品名称</th><th>规格型号</th><th>助记码</th><th>商品产地</th><th>单位</th><th>数量</th><th>进货价</th><th>销售价</th><th>备注</th>
        </tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="goods">
			<tr>
				<td style="text-align:center;"><input type="radio" name="radio" value="${goods.id}"  title="${goods.name}" /></td>
                <td>${goods.name}</td>
                <td>${goods.brand}</td>
                <td>${goods.code}</td>
                <td>${goods.origin}</td>
                <td>${goods.unit}</td>
                <td>${goods.num}</td>
                <td>${goods.purchase}</td>
                <td>${goods.sale}</td>
                <td>${goods.remarks}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>
