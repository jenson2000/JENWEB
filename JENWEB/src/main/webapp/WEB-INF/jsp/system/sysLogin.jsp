<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>JENWEB | Log in</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/AdminLTE.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/iCheck/square/blue.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#">
				<b>JEN</b>
				WEB
			</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">登录</p>

			<form>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="账户" id="account" name="account">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="密码" id="password" name="password">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback row">
					<div class="col-xs-7">
						<input type="text" class="form-control" id="captcha" placeholder="验证码">
					</div>
					<div class="col-xs-5">
						<img alt="如果看不清楚，请单击图片刷新！" class="pointer" id="captchaImg" src='<webTagTools:webPath pathAddress=""/>system/captcha.jpg' onclick="refreshCode()">
					</div>
				</div>
				<div class="form-group has-feedback row">
					<div class="col-xs-12">
						<button type="button" class="btn btn-primary btn-block btn-flat" onclick="login()">登 录</button>
					</div>
				</div>
				<div class="alert alert-warning" style="display: none;" id="errormsg">
					<i class="icon fa fa-warning"></i>
					<span> </span>
				</div>
			</form>
			<a href="#">忘记密码</a>
			<br>
			<a href="#" class="text-center">注册新账户</a>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<script src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/iCheck/icheck.min.js"></script>
	<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/jensonJs.js"></script>
	<script>
    $(function(){
      $('input').iCheck({
        checkboxClass : 'icheckbox_square-blue',
        radioClass : 'iradio_square-blue',
        increaseArea : '20%' // optional
      });
    });
    function login(){
      $
        .ajax({
          type : 'post',
          async : false,
          data : {
            account : $("#account").val(),
            password : $("#password").val(),
            captcha : $("#captcha").val()
          },
          dataType : 'json',
          url : "<webTagTools:webPath pathAddress=""/>system/login",
          success : function(data){
            if (isNullorEmpty(data.msg)){
              window.location.href = "<webTagTools:webPath pathAddress=""/>system/main";
            } else{
              refreshCode();
              $("#errormsg span").text(data.msg);
              $("#errormsg").show();
            }
          }
        });
    }
    function refreshCode(){
      path = "<webTagTools:webPath pathAddress=""/>system/captcha.jpg?t="
        + $.now();
      $("#captchaImg").attr('src', path);
    }
  </script>
</body>
</html>
