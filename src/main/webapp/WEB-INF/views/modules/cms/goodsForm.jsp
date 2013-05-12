<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			
			 // 验证值小数位数不能超过两位
			  jQuery.validator.addMethod("decimal", function(value, element) {
			   var decimal = /^-?\d+(\.\d{1,2})?$/;
			   return this.optional(element) || (decimal.test(value));
			  }, $.validator.format("小数位数不能超过两位!"));
			 
			//生成助记码
			$("#name").blur(function() { 
				var names=$("#name").val();
				 $.post("${ctx}/cms/tools/getCode", {name: names},
						function (data, textStatus){
						$("#code").attr("value",data);
						}, "text");
			}); 
		
			//计算销售价格
			$("#purchase").blur(function() { 
				var purchase=parseFloat($("#purchase").val())
				var rate=parseFloat($("#rate").val());
				var result;
				if(!isNaN(purchase)&&!isNaN(rate)){
					result=purchase*(1+rate);		
					result=result.toFixed(2);
				}else{
					result=0;
				}
				$("#sale").attr("value",result);
				 
			}); 
			
			$("#rate").blur(function() { 
				var purchase=parseFloat($("#purchase").val())
				var rate=parseFloat($("#rate").val());
				var result;
				if(!isNaN(purchase)&&!isNaN(rate)){
					result=purchase*(1+rate);		
					result=result.toFixed(2);
				}else{
					result=0;
				}
				$("#sale").attr("value",result);
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
		<li><a href="${ctx}/cms/goods/">商品列表</a></li>
		<li class="active"><a href="${ctx}/cms/goods/form?id=${goods.id}">商品${not empty goods.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="goods" action="${ctx}/cms/goods/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<table>
			<tr>
			<td>
			<label class="control-label">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="required"/>
			</div>
			</td>
			<td>
			<label class="control-label">助记码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="45" class="required"/>
			</div>
			</td>
			</tr>
			</table>
		</div>
		<div class="control-group">
			<table>
			<tr>
				<td>
					<label class="control-label">进货价格:</label>
					<div class="controls">
						<form:input path="purchase" htmlEscape="false" minvalue="0"  class="decimal required"/>
					</div>
				</td>
				<td>
					<label class="control-label">销售点数:</label>
					<div class="controls">
						<form:input path="rate" htmlEscape="false" minvalue="0"  class="decimal required"/>
					</div>
				</td>
			</tr>
			</table>
		</div>
		<div class="control-group">
			<label class="control-label">销售价格:</label>
			<div class="controls">
				<form:input path="sale" htmlEscape="false" minvalue="0"  class="decimal" readonly="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<table>
			<tr>
				<td>
					<label class="control-label">数量:</label>
					<div class="controls">
						<form:input path="num" htmlEscape="false" minvalue="0"  class="digits"/>
					</div>
				</td>
				<td>
					<label class="control-label">产品单位:</label>
					<div class="controls">
					<form:input path="unit" htmlEscape="false"  maxlength="45"  class="required"/>
					<!--  
						<form:select path="unit" class="input-mini">
					    <form:options  items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
						</form:select>
						-->
						</div>
				</td>
			</tr>
			</table>
		</div>
				
	
			<div class="control-group">
			<table>
			<tr>
				<td>
					<label class="control-label">商品产地:</label>
					<div class="controls">
						<form:input path="origin" htmlEscape="false" maxlength="200" />
					</div>
				</td>
				<td>
					<label class="control-label">规格:</label>
					<div class="controls">
						<form:input path="brand" htmlEscape="false" maxlength="200" />
					</div>
				</td>
			</tr>
			</table>
		</div>
			

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="3" cols="200" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<c:if test="${not empty goods.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${goods.create_date}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">最后更新时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${goods.update_date}" type="both" dateStyle="full"/></label>
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
