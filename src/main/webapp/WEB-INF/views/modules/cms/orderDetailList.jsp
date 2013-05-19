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
			
		});
		function setStatus(){
			var id=$("#id").val();
			window.location="${ctx}/cms/order/setStatus?id="+id;
		}
		function setStatus1(){
			var id=$("#id").val();
			window.location="${ctx}/cms/order/setStatus1?id="+id;
		}
		function doReceivable(){
			var id=$("#id").val();
			window.location="${ctx}/cms/order/doReceivable?id="+id;
		}
		function doPrint(){
			var id=$("#id").val();
			alert(id)
		}
		
		function doSave(){
 			var ids="";
 			var sales="";
 			var nums="";
			 $('input:checkbox[name="goods_id"]:checked').each(function(i,val){  
				
			 			var id=$(this).val();
			 			var sale=$("#sale"+val.value).val();
			 			var num=$("#num"+val.value).val();
			 			if(!isNaN(sale)&&!isNaN(num)){
			 				ids+=id+"@";
			 				sales+=sale+"@";
			 				nums+=num+"@";
			 			}else{
						      top.$.jBox.tip("请确保输入的数字正确", 'info');
						      return false;
			 		   }
					        
			 });  
			
				var url="${ctx}/cms/order/saveList";
				//url+="?ids="+ids+"&sales="+sales+"&nums="+nums+"&consumer_id="+$("#consumer_id").val()+"&id="+$("#id").val();
				$.post(url, 
						{
					    ids:ids,
					    sales:sales,
					    nums:nums,
					    order_id:$("#id").val()
						},
						function (data, textStatus){
							if(data=="success"){
								top.$.jBox.tip("保存成功", 'info');
								$("#searchForm").submit();
							}else{
								top.$.jBox.tip("保存失败", 'info');
							}
						}, "text");
				
				
			
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>订单明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/cms/order/detailList" method="post">
		<input id="id" name="id" type="hidden" value="${order.id}"/>
	</form:form>
	<div class="breadcrumb form-search">	
		<label>客户名称：${order.consumer.name}</label>&nbsp;&nbsp;
		<label>客户助记码：${order.consumer.code}</label>&nbsp;&nbsp;
		<label>本次订单总金额：${order.total}</label>&nbsp;&nbsp;
	</div>
	<tags:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered ">
		<thead><tr>
		<!-- 
		<c:if test="${order.status==0}">
		<th><input type="checkbox" id="chkAll"></th>
		</c:if>
		 -->
		
		<th>商品名称</th><th>商品助记码</th><th>进货价</th><th>销售价</th><th>本次销售价格</th><th>本次销售数量</th><th>销售总金额</th>
		<!-- 
		<c:choose>
		<c:when test="${order.status==0}">
		<th>操作</th>
		</c:when>
		<c:otherwise></c:otherwise>
		</c:choose>
		 -->
		</tr></thead>
		<tbody>
		<c:forEach items="${list}" var="childList" varStatus="st" >
			<tr>
				<!--  
				 <c:if test="${order.status==0}">
			     <td>
			     
			     <input type="checkbox" name="goods_id" value="${childList.id}"  
			     <c:if test="${st.index==0}"> checked</c:if>
			     >
			     </td>
			     </c:if>
			     -->
				<td>${childList.goods.name}</td>
				<td>${childList.goods.code}</td>
				<td>${childList.goods.purchase}</td>
				<td>${childList.goods.sale}</td>
				<td>${childList.sale}</td>
				<td>${childList.num}</td>
				<td>${fns:getFloat(childList.num*childList.sale)}</td>
				<!-- 
				<c:choose>
				<c:when test="${order.status==0}">
				<td><input type="text"   id="sale${childList.id}"  value="${childList.sale}" class="input-medium1"/></td>
				<td><input type="text"   id="num${childList.id}"  value="${childList.num}" class="input-medium1" /></td>
				<td>
				<a href="${ctx}/cms/order/deleteDetail?id=${childList.id}&order_id=${order.id}" onclick="return confirmx('确认要删除该条数据吗？', this.href)">删除</a>
				</td>
				</c:when>
				<c:otherwise>
				<td>${childList.sale}</td>
				<td>${childList.num}</td>
				</c:otherwise>
				</c:choose>
				 -->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="form-actions">
		<div align="center">
		
		<c:if test="${order.status==1}">
			<input id="btnButton"  class="btn btn-primary" type="button" value="置为有效" onclick="setStatus()"/>&nbsp;&nbsp;
		</c:if>
		<c:if test="${order.status==0}">
		<!--  
			<input id="btnButton"  class="btn btn-primary" type="button" value="保存修改" onclick="doSave()"/>&nbsp;&nbsp;
		-->
			<input id="btnButton"  class="btn btn-primary" type="button" value="纳入应收" onclick="doReceivable()"/>&nbsp;&nbsp;
			<input id="btnButton"  class="btn btn-primary" type="button" value="置为无效" onclick="setStatus1()"/>&nbsp;&nbsp;
			<!--  
			<input id="btnButton"  class="btn btn-primary" type="button" value="打印订单" onclick="doPrint()"/>&nbsp;&nbsp;
			-->
		</c:if>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>&nbsp;&nbsp;
		</div>
	</div>

</body>
</html>
