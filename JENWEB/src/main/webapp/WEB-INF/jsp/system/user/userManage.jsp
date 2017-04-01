<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="/WEB-INF/jsp/commons/includeCss.jsp"%>

<title>用户管理</title>
   </head>
<body class="hold-transition skin-blue sidebar-mini">

	<div class="box box-primary">
		<div class="box-body">
			<form class="form-horizontal">

				<div class="form-group">
					<label for="qyuserAccount" class="col-sm-1 control-label">用户名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="qyuserAccount" name="qyuserAccount">
					</div>
					<label for="qyuserName" class="col-sm-1 control-label">真实姓名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="qyuserName" name="qyuserName">
					</div>
					<label for="qystatus" class="col-sm-1 control-label">状态</label>
					<div class="col-sm-2">
					   <select class="form-control select2" id="qystatus" name="qystatus">
					   <option value="" selected="selected">全部</option>
					   <option value="1">有效</option>
					   <option value="0">无效</option>					   
					   </select>					
					</div>
					<label for="qycreateTime" class="col-sm-1 control-label">创建时间</label>
					<div class="col-sm-2 ">
						<input type="text" class="form-control" id="qycreateTime" name="qycreateTime">
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
						<th>用户名</th>
						<th>真实姓名</th>
						<th>状态</th>
						<th>邮箱</th>
						<th>电话</th>
						<th>创建时间</th>
						<th>操作</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>		
			
	<form class="form-horizontal" id="userForm" name="userForm"  style="display:none;">
				<div class="form-group">
					<label for="inpaccount" class="col-sm-2 control-label">用户名<span style="color:red">*</span></label>
					<div class="col-sm-10">
						<input type="isloginname" class="form-control" id="iptaccount" name="iptaccount"  minlength="6" required maxlength="30">
					</div>
				</div>
				<div class="form-group">
					<label for="inpname" class="col-sm-2 control-label ">姓名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="iptname" name="iptname" minlength="2">
					</div>
				</div>
				<div class="form-group">
					<label for="rsex" class="col-sm-2 control-label ">性别</label>
					<div class="col-sm-10">
						<label>
							男
							<input type="radio" class="square checked" name="rsex" id="male" value="1">
						</label>
						<label>
							女
							<input type="radio" class="square" name="rsex" id="female" value="2">
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
						<input type="dateISO" class="form-control {date:true}" id="iptborn" name="iptborn" date placeholder="yyyy-mm-dd">
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
	</form>
	
	<form class="form-horizontal" id="roleForm" name="roleForm"  style="display:none;">
		<div class="form-group">
					<label for="selrole" class="col-sm-2 control-label">角色<span style="color:red">*</span></label>
					<div class="col-sm-10" id="select2Div">
					<select class="form-control select2" multiple="multiple" id="selrole" name="selrole" style="width: 100%;">
					</div>
		</div>
	</form>

<%@ include file="/WEB-INF/jsp/commons/includeJs.jsp"%>
<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/select2.js"></script>
<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/select2-zh-CN.js"></script>
<script>
var DTtable;
var $jenForm;
var $jenForm2;

$(function() {

  initdaterangepicker('#qycreateTime',360);
  initTable();
 // $.fn.modal.Constructor.prototype.enforceFocus = function() {};
  $jenForm=$("#userForm");
  $jenForm2=$("#roleForm");
  $('input[type="checkbox"].square, input[type="radio"].square').iCheck({
	 checkboxClass : 'icheckbox_square-blue',
	 radioClass : 'iradio_square-blue'
   });
  
   $('#chkall').on('click',function() {
     var rows = DTtable.rows().nodes();
     $('input[name="chklist"]',rows).prop('checked', this.checked);
   }); 
	  
   $('#iptadd').on('click', function() {	  
	 $("label.error").hide();
     $(".error").removeClass("error");
	 $jenForm[0].reset();
	 showform(-1); 
	 });	  
});

function initTable() {
  DTtable = $('#listtable').DataTable({
  columns: [
    {"data": 'userId',
      "render": function(data, type, row, meta) {
        return '<input type="checkbox" id="chklist" name="chklist" value="' + data + '">';
      }
    },
    {"data": null},
    {"data": "userAccount","render": function(data, type, row, meta) {
      return "<a href='javaScript:showview("+row.userId+")'>"+data+"</a>"
    }},
    {"data": "userName"},
    {"data": "status","render": function(data, type, row, meta) {
      if(data==1){
      	return '<span style="color:green">有效</span>';
      }else{
      	return '<span style="color:red">无效</span>';
	 }}},
	 {"data": "userEmail"},
	 {"data": "userIphone"},
	 {"data": "createTime"},
	 {"data": 'userId',"render": function(data, type, row, meta) {
       return '<button type="button" class="btn btn-warning btn-xs" onclick="queryByID('+data+')">修 改</button>'
       +'  <button type="button" class="btn btn-warning btn-xs" onclick="showRoleform('+data+')">角 色</button>';
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
	       url: "<webTagTools:webPath pathAddress=""/>system/user/findUser"
	     }});	  
	  $('#qycreateTime').val('');	  
	}

function updateState() {
  var userIds = getSelectedCheckList('chklist');
  if (isNullorEmpty(userIds)) {
    top.bootbox.alert({
      title: "提示:",
      animate:false,
      message: "没有选择记录!",
    });
    return false;
  } else {
    $.jenajax("<webTagTools:webPath pathAddress=""/>system/user/updatestate",{userIds:userIds},null,null,null);
    queryClick();
  }
}

function queryClick() {
  var qyuserAccount = $('#qyuserAccount').val();
  var qyuserName = $('#qyuserName').val();
  var qystatus = $('#qystatus').val();
  var screateTime;
  var ecreatesTime;
  if (isNullorEmpty($('#qycreateTime').val())) {
    screateTime = null;
    ecreatesTime = null;
  } else {
    screateTime = $('#qycreateTime').data('daterangepicker').startDate.format('YYYY-MM-DD');
    ecreatesTime = $('#qycreateTime').data('daterangepicker').endDate.format('YYYY-MM-DD');
  }
  var param = {
    'qyuserAccount': qyuserAccount,
    'qyuserName': qyuserName,
    'qystatus': qystatus,
    'screateTime': screateTime,
    'ecreatesTime': ecreatesTime,
  };

  DTtable.settings()[0].ajax.data = param;
  DTtable.ajax.reload();
}
 
function saveOrUpdate(para){
$.jenajax(
	"<webTagTools:webPath pathAddress=""/>system/user/saveOrUpdateUser",
	{	id:para,
		iptaccount : Trim($('#iptaccount').val()),
		iptname : Trim($('#iptname').val()),
		rsex : $("input[name='rsex']:checked").val(),
		iptphone : $('#iptphone').val(),
		iptborn : isNullorEmpty($('#iptborn').val())?"":moment($('#iptborn').val()).format('YYYY-MM-DD'),
		iptemail : $('#iptemail').val(),
		iptaddress : $('#iptaddress').val(),
		iptremark : $('#iptremark').val()
	}, null, null, null);
queryClick();
}
	
function queryByID(para){
$.ajax({
	type:'post',
	async : false,
	data : {id:para},
	dataType : 'json',
	url : "<webTagTools:webPath pathAddress=""/>system/user/queryUser",
	success : function(data){
	if(!isNullorEmpty(data)){
		var vo=data.voObject
		$("#iptaccount").val(vo.userAccount);
		$("#iptname").val(vo.userName);

		if(vo.userSex==1){
		  $("#male").iCheck('check');
		  }else{
		  $("#female").iCheck('check');
		  }
		$("#iptphone").val(vo.userIphone);
		$("#iptborn").val(isNullorEmpty(vo.userBirthday)?"":moment(vo.userBirthday).format('YYYY-MM-DD'));
		$("#iptemail").val(vo.userEmail);
		$("#iptaddress").val(vo.userAddress);
		$("#iptremark").val(vo.remark);
		showform(para);		
		}},
		error : function(e){	
			top.bootbox.alert({
				title : "错误提示:",
				animate:false,
				message : e.responseText
			});}		
	});	
}

function showform(para){
  var jentitle;
  if(para<0){
    jentitle="新增用户"
    }else{
      jentitle="编辑用户"
      }  
	$jenForm.show(); 	 	
 	var modal= top.bootbox.dialog({
 	  title: '<i class="fa fa-file"></i>  '+jentitle,
 	  message: $jenForm,
 	  show: true,
 	  animate: false,
 	  buttons: {
      cancel: {
         label: '<i class="fa fa-times"></i> 取消',
         className: "btn-default",
         callback: function () {
         }},
      save: {
         label: '<i class="fa fa-check"></i> 保存',
         className: "btn-primary",
         callback: function (e) {
         	if($jenForm.valid()){
         		this.modal('hide');
         		saveOrUpdate(para);	            		
          }	            	
         	return false;
         }}}
	});

 	modal.on('hide.bs.modal', function(e) { 
 	  $jenForm.hide().appendTo("body");    
       }); 	
}	

function showview(para){
  $.ajax({
	type:'post',
	async : false,
	data : {id:para},
	dataType : 'json',
	url : "<webTagTools:webPath pathAddress=""/>system/user/queryUser",
	success : function(data){
	if(!isNullorEmpty(data)){
		var vo=data.voObject
		$("#iptaccount").val(vo.userAccount).attr("disabled","true");
		$("#iptname").val(vo.userName).attr("disabled","true");
		$("#male,#female").iCheck('disable');
		if(vo.userSex==1){
		  $("#male").iCheck('check');
		  }else{
		    $("#female").iCheck('check');
		  }
		$("#iptphone").val(vo.userIphone).attr("disabled","true");
		$("#iptborn").val(isNullorEmpty(vo.userBirthday)?"":moment(vo.userBirthday).format('YYYY-MM-DD')).attr("disabled","true");
		$("#iptemail").val(vo.userEmail).attr("disabled","true");
		$("#iptaddress").val(vo.userAddress).attr("disabled","true");
		$("#iptremark").val(vo.remark).attr("disabled","true");
		//show modal
		$jenForm.show(); 	 	
 	  var modal= top.bootbox.dialog({
 	  title: '<i class="fa fa-file"></i>  查看用户',
 	  message: $jenForm,
 	  show: true,
 	  animate: false,
 	  buttons: {
      cancel: {
         label: '<i class="fa fa-times"></i> 关闭',
         className: "btn-default"
         }}
	});
 	  modal.on('hide.bs.modal', function(e) {
 	   $jenForm.hide().appendTo("body");   	   	
 	   $("#male").iCheck('enable');
 	   $("#female").iCheck('enable');
 	   $("#userForm :input").attr("disabled",false); 
       }); 
		}},
		error : function(e){	
			top.bootbox.alert({
				title : "错误提示:",
				animate:false,
				message : e.responseText
			});}		
	});
 	
}

function selectRole(para){ 
  var $control= $("#selrole");
  $control.empty();
  $control.select2({
     minimumResultsForSearch: Infinity,       
     dropdownParent: $("#select2Div")
  	}); 
  
  $.ajax({
    url: "<webTagTools:webPath pathAddress=""/>system/role/findSelectRole",
    data: {userid:para},
    dataType : 'json',
    success: function(data){  
     var selvar= [];//选中的id数组
      $.each(data.data, function (i, item) {//下拉的值
        $control.append("<option value='" + item.roleId + "'>&nbsp;" + item.roleName + "</option>");
    });
      $.each(data.dataSec, function (i, item) {//选中的值
        selvar.push(item.roleId);
    });
     //初始化选中的值
      $control.val(selvar).trigger("change");
      //alert(selvar);
    }}); 
}

function showRoleform(para){
    selectRole(para);
	$jenForm2.show(); 	 	
 	var modal= top.bootbox.dialog({
 	  title: '<i class="fa fa-file"></i>  配置角色',
 	  message: $jenForm2,
 	  show: false,
 	  animate: false,
 	  buttons: {
      cancel: {
         label: '<i class="fa fa-times"></i> 取消',
         className: "btn-default",
         callback: function () {
         }},
      save: {
         label: '<i class="fa fa-check"></i> 保存',
         className: "btn-primary",
         callback: function (e) {
         	if($jenForm2.valid()){
         		this.modal('hide');
         		saveOrUpdateRole(para);	            		
          }	            	
         	return false;
         }}}
	});
 	modal.on('hide.bs.modal', function(e) { 
 	  $jenForm2.hide().appendTo("body");    
       }); 	 	
 	modal.modal('show');
}	

function saveOrUpdateRole(para){
  var ids=getSelectedList("#selrole"); 
  $.jenajax(
	"<webTagTools:webPath pathAddress=""/>system/role/updateRoleByID",
	{	userId:para,
		ids : ids
	}, null, null, null);
}


</script>
</body>
</html>