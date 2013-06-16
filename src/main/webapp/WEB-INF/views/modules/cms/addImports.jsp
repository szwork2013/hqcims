<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/dialog.jsp"%>
<html>
<head>
    <title>购物管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#goods_code").focus();
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
        }
        function doSubmit1(){
            window.location="${ctx}/cms/importCart/";
        }

        function doSubmit2(){
            var id=$("#cart_id").val();
            window.location="${ctx}/cms/importCart/deleteAll?id="+id;
        }

        function doSubmit(){
            var ids="";
            var sales="";
            var purchases="";
            var rates="";
            var nums="";
            var status=0;
            var mess="";
            $('input:checkbox[name="goods_id"]:checked').each(function(i,val){
                var goods_id=$(this).val();
                var sale=$("#sale"+val.value).val();
                var num=$("#num"+val.value).val();
                var purchase=$("#purchase"+val.value).val();
				var rate=$("#rate"+val.value).val();
                if(!isNaN(sale)&&!isNaN(num)&&!isNaN(purchase)&&!isNaN(rate)){
                    if(parseFloat(sale)<parseFloat(purchase)){
                        status=1;
                        mess="请确保销售价格不低于进货价格";
                        return false;
                    }else{
                        ids+=goods_id+"@";
                        sales+=sale+"@";
                        nums+=num+"@";
                        purchases+=purchase+"@";
                        rates+=rate+"@";
                        status=3;
                    }
                }else{
                    status=2;
                    mess="请确保输入的数字正确";
                    return false;
                }

            });
            if(status==0){
                top.$.jBox.tip("请选择物品", 'info');
                return false;
            }else if(status==3){
                var url="${ctx}/cms/importCart/save";
                $.post(url,
                        {
                            goods_ids:ids,
                            sales:sales,
                            nums:nums,
                            purchases:purchases,
                            rates:rates,
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
                top.$.jBox.tip(mess, 'info');
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
		<li><a href="${ctx}/cms/imports/">进货管理</a></li>
		<li class="active"><a href="${ctx}/cms/goods/addImport">进货导入</a></li>
	</ul>
<form:form id="searchForm" modelAttribute="querys" action="${ctx}/cms/main/importlist" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>



    <label>名称 ：</label><form:input path="goods_name" htmlEscape="false" maxlength="50" class="input-medium"/>
    &nbsp;
    <label>助记码 ：</label><form:input path="goods_code" htmlEscape="false" maxlength="50" class="input-medium"/>
    &nbsp;
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
    <form:hidden path="consumer_id"/>
    <input id="selectButton" class="btn btn-primary" type="button" value="选择客户"/>
    <label>客户名称：</label><form:input path="consumer_name" htmlEscape="false"  class="input-medium" readonly="true"/>
</form:form>
<tags:message content="${message}"/>

<input type="hidden" value="${cart_id}" id="cart_id" />
<c:choose>
    <c:when test="${cart_num>0&&cart_id>0}">
        <a href="javascript:doSubmit1()">当前进货车数量：${cart_num}</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:doSubmit2()">清空进货车</a>
    </c:when>
    <c:otherwise>当前进货车数量：0</c:otherwise>
</c:choose>

<table id="contentTable" class="table table-striped table-bordered ">
    <thead><tr>
        <th><input type="checkbox" id="chkAll"></th><th>商品名称</th><th>商品规格</th><th>商品助记码</th><th>当前进货价</th><th>当前销售价</th><th>当前销售点数</th><th>本次进货价</th><th>本次销售价</th><th>本次销售点数</th><th>本次进货数量</th>
    </tr></thead>
    <tbody>
    <c:forEach items="${page.list}" var="goods" varStatus="st" >
        <tr>
            <td><input type="checkbox" name="goods_id" value="${goods.id}"
                <c:if test="${st.index==0}"> checked</c:if>
                ></td>
            <td>${goods.name}</td>
            <td>${goods.brand}</td>
            <td>${goods.code}</td>
            <td>${goods.purchase}</td>
            <td>${goods.sale}</td>
            <td>${goods.rate}</td>
            <td><input type="text"   id="purchase${goods.id}"  value="${goods.purchase}" class="input-medium1" /> </td>
             <td><input type="text"   id="sale${goods.id}"  value="${goods.sale}" class="input-medium1"/></td>
             <td><input type="text"   id="rate${goods.id}"  value="${goods.rate}" class="input-medium1"/></td>
             <td><input type="text"   id="num${goods.id}"  value="1" class="input-medium1" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
<div class="form-actions">
    <div align="center">
        <input  class="btn btn-primary" type="button" value="加入进货车" onclick="doSubmit()"/>
        <c:if test="${cart_num>0&&cart_id>0}">
        <input  class="btn btn-primary" type="button" value="进入进货车" onclick="doSubmit1()"/>
        <input  class="btn btn-primary" type="button" value="清空进货车" onclick="doSubmit2()"/>
    	</c:if>
    </div>
</div>

</body>
</html>
