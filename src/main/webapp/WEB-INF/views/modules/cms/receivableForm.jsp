<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应收管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#amount").focus();
			
			 // 验证值小数位数不能超过两位
			  jQuery.validator.addMethod("decimal", function(value, element) {
			   var decimal = /^-?\d+(\.\d{1,2})?$/;
			   return this.optional(element) || (decimal.test(value));
			  }, $.validator.format("小数位数不能超过两位!"));
			 
			$("#amount").blur(function() { 
				var rec=parseFloat($("#amount2").val())
				var col=parseFloat($("#amount").val());
				var result;
				if(!isNaN(rec)&&!isNaN(col)){
					result=rec-col;
					result=result.toFixed(2);
				}else{
					result=0;
				}
				$("#amount1").attr("value",result);
				 
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
		<li class="active"><a>进行实收</a></li>
	</ul><br/>
	<form action="${ctx}/cms/collecting/save" id="inputForm" method="post" class="form-horizontal">
		<input type="hidden" value="${receivable.id}"  name="id">

		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
			 <label class="lbl">${receivable.consumer.name}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户助记码:</label>
			<div class="controls">
				<label class="lbl">${receivable.consumer.code}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单编号:</label>
			<div class="controls">
				<label class="lbl">${receivable.order.id}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应收金额:</label>
			<div class="controls">
			<input type="hidden" value="${receivable.amount}"  id="amount2"/>
			<label class="lbl" id="recv">${receivable.amount}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实收金额:</label>
			<div class="controls">
				<input type="text" value="${receivable.amount}" name="amount" id="amount" class="required decimal"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">小额调整:</label>
			<div class="controls">
			<input type="text" value="0.00" name="amount1" id="amount1" readonly />
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否进行小额调整:</label>
			<div class="controls">
			<input type="radio" id="if_checked" value="1" name="if_checked" checked>是
			<input type="radio" id="if_checked" value="0" name="if_checked">否
			</div>
		</div>	
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
</body>
</html>
