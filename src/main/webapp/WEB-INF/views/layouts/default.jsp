<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
	<head>
		<title><sitemesh:title/> - Powered By JeeSite</title>
		<%@include file="/WEB-INF/views/include/head.jsp" %>
		
		<sitemesh:head/>

	</head>
	<body>
		<sitemesh:body/>
		<script type="text/javascript"> 
		$(document).ready(function() {
			KeyboardJS.on('ctrl + m', function() {
				//$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				if(!$($('.accordion-heading a i:eq(1)',window.top.frames["menuFrame"].document).attr('href')).hasClass('in')){
					
				}else{
					$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				}
				
				$(".accordion-body a i:eq(4)", window.top.frames["menuFrame"].document).click();
				//$('.accordion-body a i:eq(2) .accordion-toggle i',window.top.frames["menuFrame"].document).removeClass();
				//$('.accordion-body a i:eq(2) .accordion-toggle i',window.top.frames["menuFrame"].document).addClass('icon-chevron-right');
				//$('.accordion-body a i:eq(2) .accordion-toggle i',window.top.frames["menuFrame"].document).children('i').addClass('icon-chevron-down');
				//$(".accordion-body a i:eq(2)", window.top.frames["menuFrame"].document).click();

				//$("#main", window.top.document).click();
				//$('#content').content().find.('#menuFrame').contents().find(".accordion-heading a i:eq(1)").click();
				//$('#content').content().find.$('#menuFrame').contents().find(".accordion-body a i:eq(2)").click();
			});
			KeyboardJS.on('alt + `', function() {
				var class_name=$(".accordion-heading a i:eq(0)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(0)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(0)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 1', function() {
				var class_name=$(".accordion-heading a i:eq(0)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(0)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(1)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 2', function() {
				var class_name=$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(2)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 3', function() {
				var class_name=$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(3)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 4', function() {
				var class_name=$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(4)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 5', function() {
				var class_name=$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(5)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 6', function() {
				var class_name=$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(6)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 7', function() {
				var class_name=$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(1)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(7)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 8', function() {
				var class_name=$(".accordion-heading a i:eq(2)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(2)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(8)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 9', function() {
				var class_name=$(".accordion-heading a i:eq(2)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(2)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(9)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + 0', function() {
				var class_name=$(".accordion-heading a i:eq(2)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(2)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(10)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + q', function() {
				var class_name=$(".accordion-heading a i:eq(3)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(3)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(11)", window.top.frames["menuFrame"].document).click();
			});
			KeyboardJS.on('alt + a', function() {
				var class_name=$(".accordion-heading a i:eq(3)", window.top.frames["menuFrame"].document).attr("class");
				if(class_name=='icon-chevron-right'){
					$(".accordion-heading a i:eq(3)", window.top.frames["menuFrame"].document).click();
				}
				$(".accordion-body a i:eq(12)", window.top.frames["menuFrame"].document).click();
			});
			
		});
</script>	
	</body>
</html>