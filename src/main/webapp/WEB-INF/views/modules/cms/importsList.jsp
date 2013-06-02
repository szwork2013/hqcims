<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>进货管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#code").focus();
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/cms/imports/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/cms/imports/import/template">下载模板</a>
		</form>
	</div>
	
	<ul class="nav nav-tabs">
		<li class="active"><a>进货管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="imports" action="${ctx}/cms/imports/" method="post" class="breadcrumb form-search">
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
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
        </div>
		
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>进货客户</th><th>客户助记码</th><th>商品名称</th><th>助记码</th><th>总数量</th><th>进货数量</th><th>当前进货价</th><th>当前销售价</th><th>进货时间</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="import">
			<tr>
				<td>${import.consumer.name}</td>
				<td>${import.consumer.code}</td>
				<td>${import.goods.name}</td>
				<td>${import.goods.code}</td>
                <td>${import.goods.num}</td>
                <td>${import.num}</td>
				<td>${import.goods.purchase}</td>
				<td>${import.goods.sale}</td>
				<td><fmt:formatDate value="${import.create_date}" type="both"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
