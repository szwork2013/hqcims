<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>还款管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        function consumerSelect(id,title){
            var values=title.split("欠款:");
            $("#consumer_id").attr("value",id);
            $("#consumer_name").attr("value",values[0]);
            $("#amount").attr("value",values[1]);
        }


		$(document).ready(function() {
			$("#amount").focus();
            // 验证值小数位数不能超过两位
            jQuery.validator.addMethod("decimal", function(value, element) {
                var decimal = /^-?\d+(\.\d{1,2})?$/;
                return this.optional(element) || (decimal.test(value));
            }, $.validator.format("小数位数不能超过两位!"));

            $("#selectButton").click(function(){
                top.$.jBox.open("iframe:${ctx}/cms/balance1/selectList?pageSize=8", "选择用户",$(top.document).width()-220,$(top.document).height()-180,{
                    buttons:{"确定":true}, loaded:function(h){
                        $(".jbox-content", top.document).css("overflow-y","hidden");
                    }
                });
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
    <li><a href="${ctx}/cms/balance1/">欠款列表</a></li>
    <li class="active"><a href="${ctx}/cms/collecting/form">客户还款</a></li>
</ul><br/>
	<form id="inputForm" action="${ctx}/cms/collecting/save1" method="post" class="form-horizontal">
        <input type="hidden" id="consumer_id" name="consumer_id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
                <input type="text" id="consumer_name" name="consumer_name"  htmlEscape="false" maxlength="50" class="input-medium required" readonly="true"/>
                <input id="selectButton" class="btn btn-primary" type="button" value="选择客户"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">还款金额:</label>
            <div class="controls">
                <input type="text" id="amount" name="amount" htmlEscape="false" minvalue="0" class="input-medium decimal required" />
            </div>
        </div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
</body>
</html>
