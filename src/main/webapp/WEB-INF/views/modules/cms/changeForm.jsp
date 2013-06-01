<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>换货管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

        function consumerSelect(id,title){
            $("#consumer_id").attr("value",id);
            $("#consumer_name").attr("value",title);
        }
        function goodSelect(id,title){
            $("#goods_id").attr("value",id);
            $("#goods_name").attr("value",title);
        }
		$(document).ready(function() {


            $("#selectButton").click(function(){
                top.$.jBox.open("iframe:${ctx}/cms/consumer/selectList?pageSize=8&is_special=-1", "选择用户",$(top.document).width()-220,$(top.document).height()-180,{
                    buttons:{"确定":true}, loaded:function(h){
                        $(".jbox-content", top.document).css("overflow-y","hidden");
                    }
                });
            });

            $("#selectButton1").click(function(){
                top.$.jBox.open("iframe:${ctx}/cms/goods/selectList?pageSize=8", "选择产品",$(top.document).width()-220,$(top.document).height()-180,{
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
		<li><a href="${ctx}/cms/change/">换货列表</a></li>
		<li class="active"><a href="${ctx}/cms/change/form">换货新增</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="change" action="${ctx}/cms/change/save" method="post" class="form-horizontal">
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
				<form:input path="consumer.name" htmlEscape="false" id="consumer_name" class="required"/><form:hidden path="consumer.id" id="consumer_id" class="required"/>
                <input id="selectButton" class="btn btn-primary" type="button" value="选择客户"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">产品名称:</label>
            <div class="controls">
                <form:input path="goods.name" id="goods_name" htmlEscape="false"  class="required"/><form:hidden path="goods.id" id="goods_id" class="required"/>
                <input id="selectButton1" class="btn btn-primary" type="button" value="选择产品"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">数量:</label>
            <div class="controls">
                <form:input path="num" htmlEscape="false" minvalue="0"  class="digits"/>
            </div>
        </div>


		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
                <form:textarea path="remarks" htmlEscape="false" rows="3" cols="200" maxlength="255" class="input-xlarge"/>

			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
