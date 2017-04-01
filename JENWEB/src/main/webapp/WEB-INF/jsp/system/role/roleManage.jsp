<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="/WEB-INF/jsp/commons/includeCss.jsp"%>
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/ztree/zTreeStyle/zTreeStyle.css">
<title>角色管理</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<div class="box box-primary">
		<div class="box-body">
			<form class="form-horizontal">

				<div class="form-group">					
					<label for="qyroleName" class="col-sm-1 control-label">角色</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="qyroleName" name="qyroleName">
					</div>
					<label for="qystatus" class="col-sm-1 control-label">状态</label>
					<div class="col-sm-2">
					   <select class="form-control select2" id="qystatus" name="qystatus">
					   <option value="" selected="selected">全部</option>
					   <option value="1">有效</option>
					   <option value="0">无效</option>					   
					   </select>					
					</div>					
				</div>
				<div class="form-group">
					<div class="col-sm-6 btn-group">
					<button type="button" class="btn btn-primary" id="iptadd" ><i class="fa fa-plus"></i>&nbsp;新增</button>
					<button type="button" class="btn btn-primary" id="btn_delete" onclick="updateState();" name="btn_delete"><i class="fa fa-remove"></i>&nbsp;删除</button>
					</div>				
					<div class="col-sm-6">
					<button type="button" class="btn btn-primary" onclick="queryClick();"><i class="fa fa-search"></i>&nbsp;查询</button>
					</div>
				</div>
			</form>

			<table id="listtable" name="listtable" class="table table-bordered table-striped table-condensed table-responsive" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><input type="checkbox" id="chkall" name="chkall" value="1"></th>
						<th>序号</th>
						<th>角色名</th>
						<th>状态</th>						
						<th>创建时间</th>
						<th>操作</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>		
			
	<form class="form-horizontal" id="jenForm" name="jenForm"  style="display:none;">
				
				<div class="form-group">
					<label for="iptname" class="col-sm-2 control-label ">角色名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="iptname" name="iptname" minlength="2">
					</div>
				</div>
				
				
				<div class="form-group">
				<label for="iptstatus" class="col-sm-2 control-label ">状态</label>
					<div class="col-sm-10">
					 <select class="form-control select2" id="iptstatus" name="iptstatus">
					 <option value="1" selected="selected">有效</option>
					 <option value="0">无效</option>					   
					 </select>	
					</div>				
				</div>	
				<div class="form-group">
					<label for="iptremark" class="col-sm-2 control-label">备注</label>

					<div class="col-sm-10">
						<textarea class="form-control" rows="2" id="iptremark" name="iptremark"></textarea>
					</div>
				</div>	
				<div class="form-group">
				<label for="iptremark" class="col-sm-2 control-label">菜单</label>
				<div class="col-sm-10">
				 <ul id="menuTree" class="ztree"></ul>
				</div>
				</div>	
							
	</form>

<%@ include file="/WEB-INF/jsp/commons/includeJs.jsp"%>
<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/ztree/jquery.ztree.all.min.js"></script>
<script>
var DTtable;


$(function() {

  initTable();

  $('input[type="checkbox"].square, input[type="radio"].square').iCheck({
	 checkboxClass : 'icheckbox_square-blue',
	 radioClass : 'iradio_square-blue'
   });
  
   $('#chkall').on('click',function() {
     var rows = DTtable.rows().nodes();
     $('input[name="chklist"]',rows).prop('checked', this.checked);
   }); 
	  
   $('#iptadd').on('click', function() {	  
     location.href = "<webTagTools:webPath pathAddress=""/>system/role/roleEdit?id=-1";
	 });	  
});

function initTable() {
  DTtable = $('#listtable').DataTable({
  columns: [
    {"data": 'roleId',
      "render": function(data, type, row, meta) {
        return '<input type="checkbox" id="chklist" name="chklist" value="' + data + '">';
      }
    },
    {"data": null},
    {"data": "roleName","render": function(data, type, row, meta) {
      return "<a href='javaScript:showview("+row.roleId+")'>"+data+"</a>"
    }},


    {"data": "status","render": function(data, type, row, meta) {
      if(data==1){
      	return '<span style="color:green">有效</span>';
      }else{
      	return '<span style="color:red">无效</span>';
	 }}},
	 {"data": "createTime"},
	 {"data": 'roleId',"render": function(data, type, row, meta) {
       return '<button type="button" class="btn btn-warning btn-xs" onclick="update('+data+')">修 改</button>';
	  }
	 }],
	 "drawCallback": function() { //序号
	   var api = this.api();
	   var startIndex = api.context[0]._iDisplayStart; //获取到本页开始的条数
	   api.column(1).nodes().each(function(cell, i) {
	   cell.innerHTML = startIndex + i + 1;
	   });
	   },
	   ajax: {
	       url: "<webTagTools:webPath pathAddress=""/>system/role/findRole"
	     }});	  
	}

function updateState() {
  var Ids = getSelectedCheckList('chklist');
  if (isNullorEmpty(Ids)) {
    top.bootbox.alert({
      title: "提示:",
      animate:false,
      message: "没有选择记录!",
    });
    return false;
  } else {
    $.jenajax("<webTagTools:webPath pathAddress=""/>system/role/updatestate",{Ids:Ids},null,null,null);
    queryClick();
  }
}

function queryClick() {
  
  var qyroleName = $('#qyroleName').val();
  var qystatus = $('#qystatus').val();

  var param = {
    'qyroleName': qyroleName,
    'qystatus': qystatus
  };

  DTtable.settings()[0].ajax.data = param;
  DTtable.ajax.reload();
}

function update(para){
  location.href = "<webTagTools:webPath pathAddress=""/>system/role/roleEdit?id="+para;	
}

function showview(para){
  location.href = "<webTagTools:webPath pathAddress=""/>system/role/roleEdit?id="+para+"&rdonly=1";	 	
}


</script>
</body>
</html>