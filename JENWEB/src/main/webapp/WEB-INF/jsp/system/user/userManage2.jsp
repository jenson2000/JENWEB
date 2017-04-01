<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>css/AdminLTE.min.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/iCheck/all.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/font-awesome/css/font-awesome.min.css">

<title>用户管理</title>
</head>
<body skin-blue sidebar-mini>

	<div class="box box-primary">
		<div class="box-body">
			<form class="form-horizontal">

				<div class="form-group">
					<label for="Email" class="col-sm-1 control-label">用户名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="userAccount" name="userAccount">
					</div>
					<label for="userName" class="col-sm-1 control-label">真实姓名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="userName" name="userName">
					</div>
					<label for="userSex" class="col-sm-1 control-label">性别</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="userSex" name="userSex">
					</div>
					<label for="createTime" class="col-sm-1 control-label">创建时间</label>
					<div class="col-sm-2 ">
						<input type="text" class="form-control" id="createTime" name="createTime">
					</div>
				</div>
				<div class="form-group text-center">
					<button type="button" class="btn btn-primary" onclick="queryClick();">查询</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" onclick="add();">新增</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" id="btn_delete" onclick="updateState();" name="btn_delete">删除</button>
				</div>
			</form>

			<table id="listtable" name="listtable" class="table table-bordered table-striped table-condensed table-responsive" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><input type="checkbox" id="chkall" name="chkall" value="1"></th>
						<th>序号</th>
						<th>用户名</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>电话</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>	
	<%@ include file="/WEB-INF/jsp/commons/includeJs.jsp"%>

	<script>
	var DTtable;

	$(function() {	  
	  initdaterangepicker("#createTime",360);
	  initTable();

	  $('#chkall').on('click',
	  function() {
	    var rows = DTtable.rows({
	      'search': 'applied'
	    }).nodes();
	    $('input[type="checkbox"]', rows).prop('checked', this.checked);
	  });
	});

	function initTable() {
	  DTtable = $('#listtable').DataTable({
	    columns: [{
	      "data": "userId",
	      "render": function(data, type, row, meta) {
	        return '<input type="checkbox" id="chklist" name="chklist" value="' + data + '">';
	      },
	      "className": "dt-body-center"
	    },
	    {
	      "data": null
	    },
	    {
	      "data": "userAccount"
	    },
	    {
	      "data": "userName"
	    },
	    {
	      "data": "userSex"
	    },
	    {
	      "data": "userEmail"
	    },
	    {
	      "data": "userIphone"
	    },
	    {
	      "data": "createTime"
	    }],
	    "drawCallback": function() { //序号
	      var api = this.api();
	      var startIndex = api.context[0]._iDisplayStart; //获取到本页开始的条数
	      api.column(1).nodes().each(function(cell, i) {
	        cell.innerHTML = startIndex + i + 1;
	      });
	    },

	     ajax: {
	       url: "<webTagTools:webPath pathAddress=""/>system/user/findUser"
	     }    

	  });
	  $('#createTime').val("");
	  
	  
	}

	function updateState() {
	  var userIds = getSelectedCheckList('chklist');

	  if (isNullorEmpty(userIds)) {
	    top.bootbox.alert({
	      title: "提示:",
	      message: "没有选择记录!",
	    });
	    return false;
	  } else {
	    $.jenajax("<webTagTools:webPath pathAddress=""/>system/user/updatestate",{userIds:userIds},null,null,null);
	  }
	}

	function queryClick() {
	  var userAccount = $("#userAccount").val();
	  var userName = $("#userName").val();
	  var userSex = $("#userSex").val();

	  var screateTime;
	  var ecreatesTime;

	  if (isNullorEmpty($("#createTime").val())) {
	    screateTime = null;
	    ecreatesTime = null;
	  } else {
	    screateTime = $('#createTime').data('daterangepicker').startDate.format('YYYY-MM-DD');
	    ecreatesTime = $('#createTime').data('daterangepicker').endDate.format('YYYY-MM-DD');
	  }

	  var param = {
	    "userAccount": userAccount,
	    "userName": userName,
	    "userSex": userSex,
	    "screateTime": screateTime,
	    "ecreatesTime": ecreatesTime,
	  };

	  DTtable.settings()[0].ajax.data = param;
	  DTtable.ajax.reload();
	}
	
	function add(){
		location.href = "<webTagTools:webPath pathAddress=""/>system/user/addUser";
	}
	</script>

</body>
</html>