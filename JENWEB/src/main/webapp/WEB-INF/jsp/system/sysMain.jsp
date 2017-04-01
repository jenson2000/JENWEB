<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>jenson首页</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet"
	href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/skin-blue.min.css">
<link rel="stylesheet"
	href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/AdminLTE.min.css">

<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/pace-theme-minimal.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/iCheck/square/blue.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/jenMain.css">

<style type="text/css">
html {
  overflow: hidden;
  height: 100%;
}
body {
    padding-right:0px !important;
    margin-right:0px !important;
    overflow: auto;
    height: 100%;
}
.modal-open {
    overflow: auto;
}
</style>

</head>

<body class="hold-transition skin-blue sidebar-mini" style="padding-right: 0px;">
	<div class="wrapper">

		<%@include file="/WEB-INF/jsp/commons/headPage.jsp"%>

	  <%@include file="/WEB-INF/jsp/commons/leftmenu.jsp"%> 
	

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h5>
					<i class="fa fa-th">&nbsp;&nbsp;<strong><span id="jentitle">用户管理</span></strong></i>
				</h5>

			</section>

			<section class="content">			
				<iframe
					src="<webTagTools:webPath pathAddress=""/>system/user/userManage"
					name="mainframe" id="mainframe" width="100%" height="100%" min-height="600px"
					frameborder="0" border="0" framespacing="0" noresize
					marginheight="0" marginwidth="0" scrolling="no"></iframe>				
					
			</section>
		</div>
	</div>

	<script
		src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/app.js"></script>
	<script data-pace-options='{ "ajax": true }' type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/pace.min.js"></script>
	<script
		src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/iframeResizer.min.js"></script>
	<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/bootbox.min.js"></script>
	<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/jensonJs.js"></script>
	<script type="text/javascript" language="javascript">

	$(function(){
		$("iframe").iFrameResize();			
	});	

	</script>
</body>
</html>
