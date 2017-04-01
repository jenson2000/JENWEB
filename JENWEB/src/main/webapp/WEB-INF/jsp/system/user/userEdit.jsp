<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户编辑</title>
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/AdminLTE.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/iCheck/square/blue.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/pace-theme-minimal.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/jenMain.css">
</head>
<body hold-transition skin-blue sidebar-mini>

	<div class="box box-primary">
		<div class="box-body">
			<form class="form-horizontal" id="userForm" method="post">
				<div class="form-group">
					<label for="inpaccount" class="col-sm-2 control-label">用户名*</label>
					<div class="col-sm-10">
						<input type="isloginname" class="form-control" id="iptaccount" name="iptaccount"  minlength="6" required maxlength="30" value="${userAccount}"}>
					</div>
				</div>
				<div class="form-group">
					<label for="inpname" class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="iptname" name="iptname" minlength="2">
					</div>
				</div>
				<div class="form-group">
					<label for="rsex" class="col-sm-2 control-label">性别</label>

					<div class="col-sm-10">
						<label for="rmale">
							男
							<input type="radio" class="square" id="rmale" name="rsex" checked>
						</label>
						<label for="rfemale">
							女
							<input type="radio" class="square" id="rfemale" name="rsex">
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="iptphone" class="col-sm-2 control-label">电话</label>
					<div class="col-sm-10">
						<input type="isPhone" class="form-control" id="iptphone" name="iptphone">
					</div>
				</div>
				<div class="form-group">
					<label for="iptborn" class="col-sm-2 control-label" >出生日期</label>

					<div class="col-sm-10">
						<input type="dateISO" class="form-control {date:true}" id="iptborn" name="iptborn" date>
					</div>
				</div>
				<div class="form-group">
					<label for="iptemail" class="col-sm-2 control-label ">Email</label>

					<div class="col-sm-10">
						<input type="email" class="form-control" id="iptemail" name="iptemail" >
					</div>
				</div>
				<div class="form-group">
					<label for="iptaddress" class="col-sm-2 control-label">地址</label>

					<div class="col-sm-10">
						<input type="text" class="form-control" id="iptaddress" name="iptaddress">
					</div>
				</div>
				<div class="form-group">
					<label for="iptremark" class="col-sm-2 control-label">备注</label>

					<div class="col-sm-10">
						<textarea class="form-control" rows="3" id="iptremark" name="iptremark"></textarea>
					</div>
				</div>
				<div class="box-footer">
					<button type="submit" class="btn btn-primary" onclick="backurl()">返回</button>
					<button type="submit" class="btn btn-primary" onclick="saveOrUpdate()">保存</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/commons/includeJs.jsp"%>
	<script type="text/javascript">
		$(function(){
			$('input[type="checkbox"].square, input[type="radio"].square').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue'
			});
			//$("#userForm").validate();

		});		
		
		function saveOrUpdate(){
		 if($("#userForm").valid()){    
			$.jenajax(
					"<webTagTools:webPath pathAddress=""/>system/user/saveOrUpdateUser",
					{
						iptaccount : $("#iptaccount").val(),
						iptname : $("#iptname").val(),
						rsex : $("#rsex").val(),
						iptphone : $("#iptphone").val(),
						iptborn : $("#iptborn").val(),
						iptemail : $("#iptemail").val(),
						iptaddress : $("#iptaddress").val(),
						iptremark : $("#iptremark").val()
					}, null, null, null);
		 }
		};
		
		function backurl(){
			location.href = "<webTagTools:webPath pathAddress=""/>system/user/userManage.do";
		}

		
	</script>

</body>
</html>