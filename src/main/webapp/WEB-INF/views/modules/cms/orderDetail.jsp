<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<html>
<head>
	<title>购物管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#chkAll").bind("click", function () {
		                $("[name = goods_id]:checkbox").attr("checked", this.checked);
		         });
			 
				 $("#selectButton").click(function(){
						top.$.jBox.open("iframe:${ctx}/cms/consumer/selectList?pageSize=8&is_special=-1", "选择用户",$(top.document).width()-220,$(top.document).height()-180,{
							buttons:{"确定":true}, loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
							}
						});
				 });
			 
			 
				
			 
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function consumerSelect(id,title){
			$("#consumer_id").attr("value",id);
			$("#consumer_name").attr("value",title);
			$("#searchForm").submit();
		}
		function doSubmit1(){
			window.location="${ctx}/cms/cart/";
		}
		
		function doSubmit(){
 			var ids="";
 			var sales="";
 			var nums="";
			 $('input:checkbox[name="goods_id"]:checked').each(function(i,val){  
			 			var goods_id=$(this).val();
			 			var sale=$("#sale"+val.value).val();
			 			var num=$("#num"+val.value).val();
			 			if(!isNaN(sale)&&!isNaN(num)){
			 				ids+=goods_id+"@";
			 				sales+=sale+"@";
			 				nums+=num+"@";
			 			}else{
						      top.$.jBox.tip("请确保输入的数字正确", 'info');
						      return false;
			 		   }
					        
			 });  
			if(ids.length>0){
				var url="${ctx}/cms/cart/save";
				//url+="?goods_ids="+ids+"&sales="+sales+"&nums="+nums+"&consumer_id="+$("#consumer_id").val();
				$.post(url, 
						{
					    goods_ids:ids,
					    sales:sales,
					    nums:nums,
					    consumer_id:$("#consumer_id").val()
						},
						function (data, textStatus){
							if(data=="success"){
								top.$.jBox.tip("保存成功", 'info');
								$("#searchForm").submit();
							}else{
								top.$.jBox.tip("保存失败", 'info');
							}
						}, "text");
			}else{
				top.$.jBox.tip("请选择物品", 'info');
			      return false;
			}
			
		}
	</script>
	<style type="text/css">
	.input-medium1 { width: 60px;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>购物列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderDetail" action="${ctx}/cms/orderDetail/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<form:hidden path="consumer_id"/>
		
		<label>名称 ：</label><form:input path="goods_name" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<label>助记码 ：</label><form:input path="goods_code" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;
		<input id="btnQuery" class="btn btn-primary" type="submit" value="查询"/>
		<input id="selectButton" class="btn btn-primary" type="button" value="选择客户"/>
		<label>客户名称：</label><form:input path="consumer_name" htmlEscape="false"  class="input-medium"/>
	</form:form>
	<tags:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<th><input type="checkbox" id="chkAll"></th><th>商品名称</th><th>商品助记码</th><th>进货价</th><th>销售价</th><th>上次销售价</th><th>客户名称</th><th>客户助记码</th><th>本次销售价格</th><th>本次销售数量</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderDetail" varStatus="st" >
			<tr>
			     <td><input type="checkbox" name="goods_id" value="${orderDetail.goods_id}" 
			     <c:if test="${st.index==0}"> checked</c:if>
			     ></td>
				<td>${orderDetail.goods_name}</td>
				<td>${orderDetail.goods_code}</td>
				<td>${orderDetail.purchase}</td>
				<td>${orderDetail.sale}</td>
				<td>${orderDetail.last_sale}</td>
				<td>${orderDetail.consumer_name}</td>
				<td>${orderDetail.consumer_code}</td>
				<form:form id="form${orderDetail.goods_id}" >
				<td><input type="text"   id="sale${orderDetail.goods_id}"  value="${orderDetail.sale}" class="input-medium1"/></td>
				<td><input type="text"   id="num${orderDetail.goods_id}"  value="1" class="input-medium1" /></td>
				</form:form>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div class="form-actions">
		<div align="center">
		<input id="btnButton"  class="btn btn-primary" type="button" value="保 存" onclick="doSubmit()"/>
		<input id="btnButton"  class="btn btn-primary" type="button" value="查看购物车" onclick="doSubmit1()"/>
		</div>
	</div>

</body>
</html>
