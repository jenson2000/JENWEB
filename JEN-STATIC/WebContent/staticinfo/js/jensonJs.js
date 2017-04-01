/**
 * 初始化日期范围选择控件
 */
function initdaterangepicker(daterangebtn,day){

	$(daterangebtn).daterangepicker({
		showDropdowns : true,
		format : "YYYY-MM-DD",
		dateLimit : {
			days : day
		},
		//autoUpdateInput: false,
		locale : {
			cancelLabel : "清空"
		},
		startDate : moment(),
		endDate : moment()
	});

	$(daterangebtn).on('cancel.daterangepicker', function(ev,picker){
		$(this).val("");
	});
}

/*****************************************************************
 jQuery Ajax封装通用类 (jenson.wang) 
 *****************************************************************/
$(function(){
	/**
	 * ajax封装,正常和错误信息返回通用model
	 * url 发送请求的地址
	 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
	 * async 空默认值: true。默认设置下，所有请求均为异步请求,要同步请将此选项设置为 false。
	 * 注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
	 * type 空认为 "post"
	 * dataType 预期服务器返回的数据类型，,默认json 
	 */
	jQuery.jenajax = function(url,data,async,type,dataType){
		async = (async == null || async == "" || typeof (async) == "undefined") ? "true"
				: async;
		type = (type == null || type == "" || typeof (type) == "undefined") ? "post"
				: type;
		dataType = (dataType == null || dataType == "" || typeof (dataType) == "undefined") ? "json"
				: dataType;
		data = (data == null || data == "" || typeof (data) == "undefined") ? {
			"date" : new Date().getTime()
		} : data;
		$.ajax({
			type : type,
			async : async,
			data : data,
			url : url,
			dataType : dataType,
			success : function(msg){
				top.bootbox.alert({
					title : "提示:",
					animate:false,
					message : msg.msg
				});
			},
			error : function(e){	
				top.bootbox.alert({
					title : "错误提示:",
					animate:false,
					message : e.responseText
				});
			}
		});
	};	
	
});

//绑定ajax内容到指定的Select控件
function BindSelect(ctrlName, url) {
    var control = $('#' + ctrlName);
    //设置Select2的处理
    control.select2({
        allowClear: true,
        formatResult: formatResult,
        formatSelection: formatSelection,
        escapeMarkup: function (m) {
            return m;
        }
    });

    //绑定Ajax的内容
    $.getJSON(url, function (data) {
        control.empty();//清空下拉框
        $.each(data, function (i, item) {
            control.append("<option value='" + item.Value + "'>&nbsp;" + item.Text + "</option>");
        });
    });
}

/**
 * 清空
 */

function clearbtn(daterangebtn){
	$(daterangebtn).value = "";
}

/**
 * 判断是否为空
 * 
 * @param value
 * @returns {Boolean}
 */
function isNullorEmpty(value){
	if (value == null || value == "" || typeof (value) == "undefined"
			|| value == "null"){
		return true;
	}
	return false;

}

/**
 * 包含某字符
 * @param str
 * @param substr
 * @returns {Boolean}
 */
function isContains(str,substr){
	return str.indexOf(substr) >= 0;
}

/**
 * 得到选中的复选框value的值，并以逗号相间隔
 * 
 * @author taoFangjin
 * @param checkName：
 *            一组复选框名称(name)
 * @returns {String ids}
 */
function getSelectedCheckList(checkName){
	var ids = "";
	$("input[name=" + checkName + "][type=checkbox]").each(function(){
		var $input = $(this);
		if ($input.prop("checked") == true){
			ids += (this.value + ",");
		}
	});

	if (ids != ""){
		ids = ids.substring(0, ids.length - 1);
	}
	return ids;
};

/**
 * 得到下拉框(select)的value的值，并以逗号相间隔
 * 
 * @author taoFangjin
 * @param checkName：
 *            一组复选框名称(name)
 * @returns {String ids}
 */
function getSelectedList(selectName){
  var ids = "";
  $(selectName+" :selected").each(function(){
          ids += (this.value + ",");
  });

  if (ids != ""){
      ids = ids.substring(0, ids.length - 1);
  }
  return ids;
};


//字符串去掉首尾空格
function lrTrim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
//字符串去掉所有空格
function Trim(str) {
	return str.replace(/\s/g,"");
}
//数组对象转换,以,分开对象某个属性的字符串,如:[{id:"1",name:"asd"},{id:"2",name:"sdf"}]
function ArryObtoStr(sNodes,node) {  
  var choose = ""; 
  for (var i=0;i<sNodes.length;i++) {
    var str=sNodes[i][node];
    if(!isNullorEmpty(str))  {          
      choose+= (i == (sNodes.length-1))?str:str+",";  
    }
  }  
  return choose;
}
//数组转换成以,分开的字符串 如["asd","sdf","dfg"]
function ArrytoStr(array){  
  return array.join();
}