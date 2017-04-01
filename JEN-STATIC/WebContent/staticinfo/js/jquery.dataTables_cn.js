/**
 * datatables初始化列表参数
 */
$.extend(true, $.fn.dataTable.defaults, {
	"language" : {
		"sProcessing" : "处理中...",
		"sLengthMenu" : "每页 _MENU_ 项",
		"sZeroRecords" : "没有匹配结果",
		"sInfo" : "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
		"sInfoEmpty" : "当前显示第 0 至 0 项，共 0 项",
		"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
		"sInfoPostFix" : "",
		"sSearch" : "搜索:",
		"sUrl" : "",
		"sEmptyTable" : "表中数据为空",
		"sLoadingRecords" : "载入中...",
		"sInfoThousands" : ",",
		"oPaginate" : {
			"sFirst" : "首页",
			"sPrevious" : "上页",
			"sNext" : "下页",
			"sLast" : "末页",
			"sJump" : "跳转"
		},
		"oAria" : {
			"sSortAscending" : ": 以升序排列此列",
			"sSortDescending" : ": 以降序排列此列"
		}
	},
	//"order": [[ 1, 'asc' ]],
	"lengthChange" : false,
	"ordering" : false,
	"processing" : true,
	"serverSide" : true,
	"scrollX" : false,
	"searching" : false,
	"deferRender" : true,//数据大的时候延迟加载
	"lengthMenu" : 10,//每页10条
	"deferRender" : true,//是否渲染,ajax打开提升性能
	"pagingType" : "full_numbers"
});