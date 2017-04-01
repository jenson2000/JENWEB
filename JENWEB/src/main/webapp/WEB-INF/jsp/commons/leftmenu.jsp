
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/includeJs.jsp"%>
<link rel="stylesheet" href="<%=Constant.STATIC_RESOURCES_BASEPATH%>plugs/font-awesome/css/font-awesome.css">

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">	

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu" id="menu-tree">
			<li class="header center" style="color:#FFFFFF">菜单</li>			
		</ul>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->
</aside>
<script
		src="<%=Constant.STATIC_RESOURCES_BASEPATH%>js/app.min.js"></script>
<script type="text/javascript">
var emenu="";

$(function() {
$.ajax({	
	async : false,
	dataType : 'json',
	url : "<webTagTools:webPath pathAddress=''/>system/admin/menuTree",
	success : function(data){
	if(!isNullorEmpty(data.data)){
	  var dataObj=data.data;
	  $.each(dataObj,function(index,item){
	    if(dataObj[index].subMenus.length>0){
	    emenu+= '<li class="treeview"><a href="#"><i class="'+item.rightImg+'"></i>'
		  +'<span>'+item.rightName+'</span>'
		  +'<span class="pull-right-container">'
		  +'<i class="fa fa-angle-left pull-right"></i></span> </a>';
		  eachmenu(dataObj[index].subMenus);
		  emenu+="</li>";
	  }else{
	    emenu+='<li class=""><a href="<webTagTools:webPath pathAddress=""/>'
	      +item.rightUrl+'" target="mainframe"> <i class= "'
	      +item.rightImg+'"></i>'+item.rightName+'</a></li>'; 
	  }
	  });
	  	$("#menu-tree").append(emenu);
	}}
	});	
	
	$("li a").click(function() {   
	  
	  if($(this).attr("href")!="#"){
	   $(".sidebar-menu").find('li.active').removeClass('active')
	   $(this).parent("li").addClass("active");
	   console.log();
	   $("#jentitle").text($(this).text());	  
	  }	 
	});
	
	   
	
})


function eachmenu(data){  
  emenu+='<ul class="treeview-menu">'
  $.each(data,function(index1,item1){  
    var subdata=data[index1].subMenus;
    if(subdata.length>0){
      emenu+='<li class=""><a href="#"><i class="'+item1.rightImg+'"></i>'
	  +'<span>'+item1.rightName+'</span>'
	  +'<span class="pull-right-container">'
	  +'<i class="fa fa-angle-left pull-right"></i></span> </a>';
      eachmenu(data[index1].subMenus);
	  emenu+="</li>";	  
    }else{
      emenu+='<li class=""><a href="<webTagTools:webPath pathAddress=""/>'
      +item1.rightUrl+'" target="mainframe"> <i class= "'
      +item1.rightImg+'"></i>'+item1.rightName+'</a></li>'; 
    }    
  });
  emenu+="</ul>";
}
</script>