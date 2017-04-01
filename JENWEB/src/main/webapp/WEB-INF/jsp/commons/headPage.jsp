<%@page import="com.jen.sen.commons.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/jquery-2.2.3.min.js"></script>
<script	src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/app.js"></script>
<!-- Main Header -->
<header class="main-header">
	<a href="#" class="logo">
		<!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini">
			<b>Jen</b>
		</span>
		<!-- logo for regular state and mobile devices -->
		<span class="logo-lg">
			<b>Jenson</b>
			WEB
		</span>
	</a>

	<!-- Header Navbar -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-user fa-lg fa-fw"></i>
						&nbsp;
						<span class="hidden-xs" id="headname"></span>
					</a>				
				<li>
					<a href="<webTagTools:webPath pathAddress="" />system/logout">
						<i class="fa fa-sign-out"></i>
						&nbsp;退出
					</a>
				</li>

				</li>
			</ul>
		</div>
	</nav>
</header>
<script>
  $(function(){
    $.ajax({
      async : false,
      dataType : 'json',
      url : "<webTagTools:webPath pathAddress=""/>system/admin/headPage",
      success : function(data){
        $("#headname").text(data);
      }
    });
  });
</script>