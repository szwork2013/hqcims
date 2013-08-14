<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
		function doSubmit1(){
			window.location="${ctx}/cms/goods/create";
		}
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/cms/goods/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/cms/goods/import/template">下载模板</a>
		</form>
	</div>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/goods/">商品列表</a></li>
		<li><a href="${ctx}/cms/goods/form">商品添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="goods" action="${ctx}/cms/goods/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
		<label>品牌：</label>
		<form:select path="origin" class="input-medium">
		   <form:options items="${fns:getDictList('origin_select')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
		&nbsp;<input id="btnCreate" class="btn btn-primary" type="button" value="生成助记码" onclick="doSubmit1()"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th>编号</th><th>商品名称</th><th>规格型号</th><th>助记码</th><th>商品产地</th><th>单位</th><th>数量</th><th>进货价</th><th>销售价</th><th>销售点数</th><th>备注</th><th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="goods">
			<tr>
			    <td>${goods.id}</td>
				<td><a href="${ctx}/cms/goods/form?id=${goods.id}">${goods.name}</a></td>
                <td>${goods.brand}</td>
				<td>${goods.code}</td>
                <td>${goods.origin}</td>
                <td>${goods.unit}</td>
                <td>${goods.num}</td>
				<td>${goods.purchase}</td>
				<td>${goods.sale}</td>
				<td>${goods.rate}</td>
				<td>${goods.remarks}</td>
				<td>
    				<a href="${ctx}/cms/goods/form?id=${goods.id}">修改</a>
					<a href="${ctx}/cms/goods/delete?id=${goods.id}" onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
