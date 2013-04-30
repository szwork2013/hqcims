<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
      html,body,table{background-color:#f5f5f5;width:100%;height:500px}.form-signin-heading{font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #theme{position:absolute;right:15px;bottom:10px;}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("请填写用户名和密码。");
				}
			});
		});
		// 如果在框架中，则跳转刷新上级页面
		if(self.frameElement && self.frameElement.tagName=="IFRAME"){
			parent.location.reload();
		}
	</script>
</head>
<body>
	<table><tr><td align="center" valign="middle">
      <h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
      <form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		<%String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);%>
		<div id="messageBox" class="alert alert-error <%=error==null?"hide":""%>"><button data-dismiss="alert" class="close">×</button>登录失败, 请重试.</div>
        <label class="input-label" for="username">登录名</label>
        <input type="text" id="username" name="username" class="input-block-level required" value="${username}">
        <label class="input-label" for="password">密码</label>
        <input type="password" id="password" name="password" class="input-block-level required">
        <input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;
        <label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我（公共场所慎用）</label>
      </form>
      Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a>hqcims</a> - Powered By <a>whalookingfor@gmail.com</a> ${fns:getConfig('version')}
	</td></tr></table>
</body>
</html>
