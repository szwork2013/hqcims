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


        function consumerSelect(id,title){
            $("#consumer_id").attr("value",id);
            $("#consumer_name").attr("value",title);
        }
        function doSubmit1(){
            window.location="${ctx}/cms/main/returnlist";
        }
        function doSubmit3(){
            var id=$("#id").val();
            window.location="${ctx}/cms/returns/deleteAll?id="+id;
        }
        function doSubmit2(){
            var mess="确认要提交吗？";
            var url="${ctx}/cms/returns/saveOrder?id="+$("#id").val();;
            top.$.jBox.confirm(mess,'系统提示',function(v,h,f){
                if(v=='ok'){
                    loading('正在操作，请稍等...');
                    window.location = url;
                }
            },{buttonsFocus:1});
            top.$('.jbox-body .jbox-icon').css('top','55px');
            return false;
        }

        function doSubmit(){
            var ids="";
            var sales="";
            var nums="";
            var status=0;
            var mess="";
            $('input:checkbox[name="goods_id"]:checked').each(function(i,val){

                var id=$(this).val();
                var sale=$("#sale"+val.value).val();
                var num=$("#num"+val.value).val();
                var purchase=$("#purchase"+val.value).val();
                if(!isNaN(sale)&&!isNaN(num)){
                    if(parseFloat(sale)<parseFloat(purchase)){
                        status=1;
                        mess="请确保销售价格不低于进货价格";
                        return false;
                    }else{
                        ids+=id+"@";
                        sales+=sale+"@";
                        nums+=num+"@";
                        status=3;
                    }
                }else{
                    status=2;
                    mess="请确保输入的数字正确";
                    return false;
                }

            });

            if(status==3||status==0){
                var url="${ctx}/cms/returns/saveList";
                //url+="?ids="+ids+"&sales="+sales+"&nums="+nums+"&consumer_id="+$("#consumer_id").val()+"&id="+$("#id").val();
                $.post(url,
                        {
                            ids:ids,
                            sales:sales,
                            nums:nums,
                            consumer_id:$("#consumer_id").val(),
                            id:$("#id").val()
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
    <li class="active"><a>退货车管理</a></li>
</ul>
<form:form id="searchForm" modelAttribute="returns" action="${ctx}/cms/returns/" method="post">
    <form:hidden path="id"/>
</form:form>
<div class="breadcrumb form-search">
    <input id="selectButton" class="btn btn-primary" type="button" value="更改客户"/>
    <label>客户名称：</label>
    <input id="consumer_name" name="consumer_name" type="text" value="${returns.consumer.name}"  readonly/>
    <input id="consumer_id" name="consumer_id" type="hidden" value="${returns.consumer.id}"/>
</div>
<tags:message content="${message}"/>

<table id="contentTable" class="table table-striped table-bordered ">
    <thead><tr>
        <th><input type="checkbox" id="chkAll"></th><th>商品名称</th><th>商品规格</th><th>商品助记码</th><th style="display:none;">进货价</th><th>本次销售价格</th><th>本次销售数量</th><th>操作</th>
    </tr></thead>
    <tbody>
    <c:forEach items="${list}" var="childList" varStatus="st" >
        <tr>
            <td><input type="checkbox" name="goods_id" value="${childList.id}"
                <c:if test="${st.index==0}"> checked</c:if>
                ></td>
            <td>${childList.goods.name}</td>
            <td>${childList.goods.brand}</td>
            <td>${childList.goods.code}</td>
            <td style="display:none;"><input type="text"   id="purchase${childList.id}"  value="${childList.goods.purchase}" /> </td>
            <td><input type="text"   id="sale${childList.id}"  value="${childList.sale}" class="input-medium1"/></td>
            <td><input type="text"   id="num${childList.id}"  value="${childList.num}" class="input-medium1" /></td>
            <td>
                <a href="${ctx}/cms/returns/deleteDetail?id=${childList.id}" onclick="return confirmx('确认要删除该条数据吗？', this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="form-actions">
    <div align="center">
        <input id="btnButton"  class="btn btn-primary" type="button" value="继续添加" onclick="doSubmit1()"/>
        <input id="btnButton"  class="btn btn-primary" type="button" value="保存修改" onclick="doSubmit()"/>
        <input id="btnButton"  class="btn btn-primary" type="button" value="确认退货" onclick="doSubmit2()"/>
        <input id="btnButton"  class="btn btn-primary" type="button" value="清空退货车" onclick="doSubmit3()"/>
    </div>
</div>

</body>
</html>
