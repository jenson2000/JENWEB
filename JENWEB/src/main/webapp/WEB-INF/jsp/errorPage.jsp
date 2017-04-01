<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
  <title>错误页面</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/AdminLTE.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/skin-blue.min.css">
	
</head>
<body class="skin-blue sidebar-mini">

    <section class="content">

      <div class="error-page">
        <h2 class="headline text-red">出错了!</h2>

        <div class="error-content">
          <h3><i class="fa fa-warning text-red"></i> ${message.code}.</h3>

          <p>有问题请联系jenson2000@sina.com</p>

          
        </div>
      </div>
      <!-- /.error-page -->

    </section>
	
    
    


<script type="text/javascript" src="//cdn.bootcss.com/jquery/2.2.3/jquery.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/iframeResizer.contentWindow.min.js"></script>

</body>
</html>
