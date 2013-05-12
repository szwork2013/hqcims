<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#header {margin:10px 20px 15px 0;} #header h1 {font-size:30px;color:#0663A2;}
		#header h1 small {color:#0088CC;} #header li {list-style:none;}
		#footer {margin:15px 0 0 0;padding:15px 0 0 0;font-size:95%;text-align:center;border-top:2px solid #0663A2;}
		#footer a {color:#999;} .nav li {margin-top:8px;} .nav li.title{margin-top:0;} .nav li.menu,.nav li.dropdown {margin:8px 3px 0 3px}
		.nav li.menu a {padding:5px 6px;*padding:4px 5px 3px 5px;}.nav li.dropdown a {padding:5px 6px;*padding:1px 5px 3px 5px;}
		.nav li a {font-size:14px;padding:6px 8px;*padding:3px 8px;} .nav-info {float:left;padding:0 10px;border-right:1px solid #ddd}
		.nav-info-last {float:left;padding:0 20px 0 10px;border-right:0}
	</style>
	<script type="text/javascript"> 
		$(document).ready(function() {
			$("#menu a.menu").click(function(){
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
			});
		});
	</script>
</head>
<body>
	<div id="main" class="container-fluid">
	
	    <div id="header" class="row-fluid">
			<div id="title">
				 <span class="pull-right hide">您好, 
			    	<shiro:user><a href="${ctx}/sys/user/info" target="mainFrame"><shiro:principal property="name"/></a></shiro:user><shiro:guest>请<a href="${ctx}/login">登录</a>!</shiro:guest>
					<shiro:user> | <a href="${ctx}/logout">退出</a></shiro:user>
					&nbsp;&nbsp;&nbsp;
				</span>
				<ul class="pull-right">
				  	<shiro:user>
						<li class="nav-info dropdown">
						    <a class="dropdown-toggle" data-toggle="dropdown" href="#">您好, <shiro:principal property="name"/><b class="caret"></b></a>
						    <ul class="dropdown-menu">
						      <li><a href="${ctx}/sys/user/info" target="mainFrame">个人信息</a></li>
						      <li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame">修改密码</a></li>
						    </ul>
					  	</li><li class="nav-info"><a href="${ctx}/logout">退出</a></li>
				  	</shiro:user>
				  	<shiro:guest>
				  		<li class="nav-info">请<a href="${ctx}/login">登录</a>!</li>
				  	</shiro:guest>
				  	
			  	</ul>
				
			  	
		    	<ul class="nav nav-pills" style="margin:0;" id="menu">
		    	  <li class="title"><h1>${fns:getConfig('productName')}<small></small></h1></li>
				</ul>
				
			</div>
		</div>
		
		<div id="content" class="row-fluid">
			<div id="left">
				<iframe id="menuFrame" name="menuFrame" src="${ctx}/sys/menu/tree?parentId=${firstMenuId}" style="overflow:visible;"
					scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
			</div>
			<div id="openClose" class="close">&nbsp;</div>
			<div id="right">
				<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;"
					scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
			</div>
		</div>
	    <div id="footer" class="row-fluid">
            Copyright &copy; 2012- hqcims - Powered By <a>whatlookingfor@gmail.com</a> 
		</div>
	</div>
	<script type="text/javascript"> 
		var lw = "14.89%", rw = "82.97%"; // 左侧窗口展开大小
		var lwClose = "0%", rwClose = "97.58%"; // 左侧窗口折叠大小
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs=getWindowSize().toString().split(",");
			$("#menuFrame, #mainFrame, #openClose").height((strs[0]<minHeight?minHeight:strs[0])-$("#header").height()-$("#footer").height()-80);
			$("#openClose").height($("#openClose").height()-6);
			if(strs[1]<minWidth){
				$("#main").css("width",minWidth-10);
				$("html").css("overflow-x","auto");
			}else{
				$("#main").css("width","auto");
				$("html").css("overflow-x","hidden");
			}
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>
