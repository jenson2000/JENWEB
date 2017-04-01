<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色详情</title>
<%@ include file="/WEB-INF/jsp/commons/includeCss.jsp"%>
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/iCheck/square/blue.css">
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/ztree/zTreeStyle/zTreeStyle.css">
</head>
<body hold-transition skin-blue sidebar-mini>

	<div class="box box-primary">
		<div class="box-body">
		  <form class="form-horizontal" id="jenForm" name="jenForm">
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
				<div class="box-footer">
					<button type="button"  class="btn btn-primary" onclick="backurl()">返回</button>
					<button type="button" id="btnsave" class="btn btn-primary" onclick="saveOrUpdate()">保存</button>
				</div>
							
	</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/commons/includeJs.jsp"%>
	<script type="text/javascript" src="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/ztree/jquery.ztree.all.min.js"></script>
	<script type="text/javascript">
	//var $jenForm;
	var roleId,readonly;
	roleId="${roleId}";
	readonly="${rdonly}";
	
		$(function(){
          editTree(roleId);
          
		  if(roleId>0){
		    if(!isNullorEmpty(readonly)){
		      formview(roleId);//预览
		      $("#btnsave").hide();

		    }else{
			   formInit(roleId);//编辑
		    }
			 }
		});	
		
		function formInit(para){
		  $.ajax({
			type:'post',
			async : false,
			data : {id:para},
			dataType : 'json',
			url : "<webTagTools:webPath pathAddress=""/>system/role/queryRole",
			success : function(data){
			if(!isNullorEmpty(data)){
				var vo=data.voObject
				$("#iptname").val(vo.roleName);
				if(vo.status==0){
				  $("#iptstatus").get(0).selectedIndex = 1; 
				  }else{
				    $("#iptstatus").get(0).selectedIndex = 0; 
				  }
				$("#iptremark").val(vo.remark);		
				}},
				error : function(e){
					top.bootbox.alert({
						title : "错误提示:",
						animate:false,
						message : e.responseText
					});}		
			});
		}
		
		function saveOrUpdate(){
		  var treeObj = $.fn.zTree.getZTreeObj("menuTree");
		  var sNodes = treeObj.getCheckedNodes(true);
		  var choose= ArryObtoStr(sNodes,"right_id");
		  
		  $.jenajax(
		  	"<webTagTools:webPath pathAddress=""/>system/role/saveOrUpdateRole",
		  	{	id:roleId,	  		
		  		iptname : Trim($('#iptname').val()),
		  		iptstatus : $('#iptstatus').val(),
		  		iptremark : $('#iptremark').val(),
		  		rightIDS:choose
		  	}, null, null, null);		  
		  }
		
		function formview(para){
		  $.ajax({
			type:'post',
			async : false,
			data : {id:para},
			dataType : 'json',
			url : "<webTagTools:webPath pathAddress=""/>system/role/queryRole",
			success : function(data){
			if(!isNullorEmpty(data)){
				var vo=data.voObject
				$("#iptname").val(vo.roleName).attr("disabled","true");
				if(vo.status==0){
				  $("#iptstatus").get(0).selectedIndex = 1; 
				  }else{
				    $("#iptstatus").get(0).selectedIndex = 0; 
				  }
				$("#iptstatus").attr("disabled","true");
				$("#iptremark").val(vo.remark).attr("disabled","true");
				}},
				error : function(e){	
					top.bootbox.alert({
						title : "错误提示:",
						animate:false,
						message : e.responseText
					});}		
			});
		}
		
		function editTree(para){
		  var treeArr;		  
		  $.ajax({
			type:'post',
			async : false,
			data : {roleId:para},
			dataType : 'json',
			url : "<webTagTools:webPath pathAddress=""/>system/role/treeData",
			success : function(data){
			  treeArr=data.map;	 
			},
			error : function(e){	
			top.bootbox.alert({
				title : "错误提示:",
				animate:false,
				message : e.responseText
			});}	
			});
		 
		  var setting = {
			data: {
				simpleData: {
					enable: true,
					idKey: "right_id",
					pIdKey: "right_parent_id",
					rootPId: null
				},
				key: {
					url:"url",
					name :"right_name"
				}
			},
			check:{
				enable:true,
				nocheckInherit:true
			},
			treeNode:{
			  checked:true
			}
		}; 
		  
		  $.fn.zTree.init($("#menuTree"), setting, treeArr);   
		}

		function backurl(){
		  location.href = "<webTagTools:webPath pathAddress=""/>system/role/roleManage";
		}
		
	</script>

</body>
</html>