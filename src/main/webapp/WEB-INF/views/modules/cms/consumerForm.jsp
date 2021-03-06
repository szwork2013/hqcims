<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			
			//生成助记码
			$("#name").blur(function() { 
				var names=$("#name").val();
				 $.post("${ctx}/cms/tools/getCode", {name: names},
						function (data, textStatus){
						$("#code").attr("value",data);
						}, "text");
			}); 
			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/consumer/">客户列表</a></li>
		<li class="active"><a href="${ctx}/cms/consumer/form?id=${consumer.id}">客户${not empty consumer.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="consumer" action="${ctx}/cms/consumer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">助记码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false"  maxlength="45" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址:</label>
			<div class="controls">
				<form:textarea path="address" htmlEscape="false" rows="3" maxlength="255"  class="required"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">店铺名称:</label>
			<div class="controls">
				<form:input path="shop_name" htmlEscape="false" maxlength="200"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">店铺联系电话:</label>
			<div class="controls">
				<form:input path="shop_phone" htmlEscape="false" maxlength="20"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">客户联系方式:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="20"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">是否代收客户:</label>
			<div class="controls">
			    <form:radiobuttons path="is_consumer" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">是否供应商客户:</label>
			<div class="controls">
			    <form:radiobuttons path="is_provider" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<c:if test="${not empty consumer.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${consumer.create_date}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
