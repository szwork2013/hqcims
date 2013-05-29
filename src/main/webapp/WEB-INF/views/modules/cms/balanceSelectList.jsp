<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--
  ~ Copyright (c) 2013.
  ~ whatlookingfor@gmail.com
  --%>

<html>
<head>
	<title>选择客户</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $(this).click(function(){
                var radioBox=$("input[name='radio']:checked");
                var id = radioBox.val(), title =radioBox.attr("title");
                if (top.mainFrame.cmsMainFrame){
                    top.mainFrame.cmsMainFrame.consumerSelect(id, title);
                }else{
                    top.mainFrame.consumerSelect(id, title);
                }
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
    <div style="margin:10px;">
	<form:form id="searchForm" modelAttribute="balance1" action="${ctx}/cms/balance1/selectList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
            <th style="text-align:center;">选择</th><th>客户名称</th><th>助记码注</th><th>欠款总额</th><th>应收总额</th><th>实收总额</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="balance1">
			<tr>
                <td style="text-align:center;"><input type="radio" name="radio" value="${balance1.id}"  title="${balance1.name}欠款:${balance1.bamount}" /></td>
				<td>${balance1.name}</td>
				<td>${balance1.code}</td>
				<td>${balance1.bamount}</td>

				<td>${balance1.ramount}</td>

				<td>${balance1.camount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
    </div>
</body>
</html>
