/*******************************************************************************
 * 自定义JavaScript常用类 author：jiafangyao version：0.1
 ******************************************************************************/

function HashMap() {
	var size = 0; // 大小
	var entry = new Object(); // 保存对象

	this.put = function(key, value) {
		if (!this.containsKey(key)) {
			size++;
		}
		entry[key] = value;
	}; // 存

	this.get = function(key) {
		return this.containsKey(key) ? entry[key] : null;
	}; // 取

	this.remove = function(key) {
		if (this.containsKey(key) && (delete entry[key])) {
			size--;
		}
	}; // 删除

	this.containsKey = function(key) {
		return (key in entry);
	}; // 是否包含 Key

	this.containsValue = function(value) {
		for ( var prop in entry) {
			if (entry[prop] == value) {
				return true;
			}
		}
		return false;
	}; // 是否包含 Value

	this.values = function() {
		var values = new Array();
		for ( var prop in entry) {
			values.push(entry[prop]);
		}
		return values;
	}; // 所有 Value

	this.keys = function() {
		var keys = new Array();
		for ( var prop in entry) {
			keys.push(prop);
		}
		return keys;
	}; // 所有 Key

	this.size = function() {
		return size;
	}; // size

	this.clear = function() {
		size = 0;
		entry = new Object();
	}; // 清空
}// HashMap
/**
 * map转换为参数字符串"val=value&&val2=value2"
 * @param map
 * @returns {String}
 * @author liuguocheng
 * 2013-01-21
 */
function getParam(map) {
	var paramValues = "";
	if (Trim(varType(map)) == 'HashMap') {
		var keys = map.keys();
		for ( var i = 0; i < keys.length; i++) {
			var key = keys[i];
			var value = map.get(key);
			paramValues += key + "=" + encodeURIComponent(value) + "&";
		}
	}
	paramValues = paramValues.substr(0, paramValues.lastIndexOf("&"));
	return paramValues;
}
// 自定义类型判断
function varType(v) {
	if (typeof v === "object") {
		if (v === null)
			return 'null';
		if (v.constructor)
			return (v.constructor.toString()).match(/(?: )[\w\$]+/)[0];
		if (typeof typeof2 === 'undefined' && window.execScript) {
			window
					.execScript(
							'Function vbsTypeName(o):vbsTypeName=TypeName(o):End Function',
							'vbscript');
			window.execScript('function typeof2(o){return vbsTypeName(o)}',
					'jscript');
		}
		if (typeof typeof2 !== 'undefined') {
			return typeof2(v);
		}
		return "object";
	}
	return typeof v;
}

/**
 * 取消全选/以及全部选选择时勾选全选按钮
 * 
 * @author jiafangyao
 * @param allBtn
 *            全选按钮id
 * @param checkName
 *            需选择的按钮名称
 */
function cancellAllCheck(allBtn, checkName) {
	var isAllcheck = true;
	$.each($("input[name='" + checkName + "']"), function(i, cbo) {
		if (!cbo.checked) {
			$("#" + allBtn).attr("checked", false);
			isAllcheck = false;
		}
		if(isAllcheck && i >= $("input[name='" + checkName + "']").length -1){
			$("#" + allBtn).attr("checked", true);
		}
	});
}

/**
 * 全选/取消全选
 * 
 * @author jiafangyao
 * @param allBtn
 *            全选按钮id
 * @param checkName
 *            需选择的按钮名称
 */
function allChecked(allBtn, checkName) {
	if ($('#' + allBtn).attr('checked')) {
		$("input[name='" + checkName + "']").each(function() {
			if($(this).attr("disabled")==false){
				this.checked = true;
			}
		});
	} else {
		$("input[name='" + checkName + "']").each(function() {
			this.checked = false;
		});
	}
}

/**
 * 检查是否选择相应复选框
 * 
 * @author jiafangyao
 * @param checkName
 *            复选框名称(name)
 * @returns {Boolean}
 */
function isCheck(checkName) {
	var cb = false;
	$.each($("input[name='" + checkName + "']"), function(i, cbo) {
		if (cbo.checked) {
			cb = true;
			return cb;
		}
	});
	return cb;
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
	$("input[name="+checkName+"][type=checkbox]").each(function(){
		if($(this).attr("checked")==true){
			ids  += (this.value+",");
		}
	});

	if(ids!=""){
		ids = ids.substring(0, ids.length-1);
	}
	return ids;
}

// 字符串去掉首尾空格
function Trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
// 字符串去掉左空格
function Ltrim(str) {
	return str.replace(/(^\s*)/g, "");
}
// 字符串去掉右空格
function Rtrim(str) {
	return str.replace(/(\s*$)/g, "");
}
// 获取传递参数转换成key=value&key=value形式
function getUrlParam(map) {
	var paramValues = "";
	if (map == null || map == 'undefined') {
		return;
	}
	if (Trim(varType(map)) == 'HashMap') {
		var keys = map.keys();
		for ( var i = 0; i < keys.length; i++) {
			var key = keys[i];
			var value = map.get(key);
			if(!isNullorEmpty(value)){
			paramValues += key + "=" + encodeURIComponent(value) + "&";
			}
		}
	}
	paramValues = paramValues.substr(0, paramValues.lastIndexOf("&"));
	return paramValues;
}


/**
 * 验证是否为数字,是则返回true,否则返回false
 * 
 * @author jiafangyao
 * @param obj
 *            对象(比如一个input对象)
 * @returns {Boolean}
 */
function f_check_number(obj) {
	if (/^\d+$/.test(obj.value)) {
		return true;
	} else {
		f_alert(obj, "请输入数字");
		return false;
	}
}

/**
 * 验证是否为自然数，是则返回true,否则返回false
 * 
 * @author jiafangyao
 * @param node_id
 *            控件id
 * @returns {Boolean}
 */

function f_check_naturalnumber(node_id) {
	var s = $('#' + node_id).val();
	if (/^[0-9]+$/.test(s) && (s > 0)) {
		removeCheckText(node_id);
		return true;
	} else {
		addCheckText(node_id, '请输入正确的数字');
		return false;
	}
}

/**
 * 验证是否为整数，是则返回true,否则返回false
 * 
 * @author jiafangyao
 * @param node_id
 *            控件id
 * @returns {Boolean}
 */
function f_check_integer(node_id) {
	if (/^(\+|-)?\d+$/.test($('#' + node_id).val())) {
		$('#' + node_id + "span").remove();
		return true;
	} else {
		if ($('#' + node_id + "span")) {
			$('#' + node_id + "span").remove();
		}
		$('#' + node_id).parent().append(
				"<span id='" + node_id
						+ "span' class='red_k'>请输入正确的整型数字</span>");
		$('#' + node_id).focus();
		return false;
	}
}

/**
 * 验证是否为实数，是则返回true,否则返回false
 * 
 * @author jiafangyao
 * @param node_id
 *            控件id
 * @returns {Boolean}
 */
function f_check_float(node_id) {
	if (/^(\+|-)?\d+($|\.\d+$)/.test($('#' + node_id).value)) {
		return true;
	} else {
		// f_alert(obj, "请输入实数");
		return false;
	}
}

/**
 * 检查电子邮件地址合法性
 * @author jiafangyao
 * @param obj
 * @returns {Boolean}
 */
function f_check_email(obj) {
	var myReg = /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if (myReg.test(obj.value))
		return true;
	f_alert(obj, "请输入合法的电子邮件地址");
	return false;
}

/*
 * 用途：
 * 
 * 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */
/**
 * 检查输入手机号码是否正确，是则返回true,否则返回false
 * <p>
 * 要求：一、移动电话号码为11或12位，如果为12位,那么第一位为0
 * </p>
 * <p>
 * 二、11位移动电话号码的第一位和第二位为"13"
 * </p>
 * <p>
 * 三、12位移动电话号码的第二位和第三位为"13"
 * </p>
 * 
 * @author jiafangyao
 * @param node_id
 *            控件id
 * @returns {Boolean}
 */
function f_check_mobile(obj) {
	var regu = /(^[1][3][0-9]{9}$)|(^0[1][3][0-9]{9}$)/;
	var re = new RegExp(regu);
	if (re.test(obj.value)) {
		return true;
	}
	f_alert(obj, "请输入正确的手机号码");
	return false;
}

/**
 * 校验ip地址的格式，是则返回true,否则返回false
 * 
 * @author jiafangyao
 * @param node_id
 *            控件id
 * @returns {Boolean}
 */
function f_check_IP(obj) {
	var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/; // 匹配IP地址的正则表达式
	if (re.test(obj.value)) {
		if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256
				&& RegExp.$4 < 256)
			return true;
	}
	f_alert(obj, "请输入合法的计算机IP地址");
	return false;
}

/**
 * 日期格式化 格式 :
 * <p>
 * YYYY/yyyy/YY/yy 表示年份
 * </p>
 * <p>
 * MM/M 月份
 * </p>
 * <p>
 * W/w 星期
 * </p>
 * <p>
 * dd/DD/d/D 日期
 * </p>
 * <p>
 * hh/HH/h/H 时间
 * </p>
 * <p>
 * mm/m 分钟
 * </p>
 * <p>
 * ss/SS/s/S 秒
 * </p>
 * 
 * @param formatStr
 * @author jiafangyao
 * @returns
 */
Date.prototype.Format = function(formatStr) {
	var str = formatStr;
	var Week = [ '日', '一', '二', '三', '四', '五', '六' ];

	str = str.replace(/yyyy|YYYY/, this.getFullYear());
	str = str.replace(/yy|YY/,
			(this.getYear() % 100) > 9 ? (this.getYear() % 100).toString()
					: '0' + (this.getYear() % 100));

	str = str.replace(/MM/, this.getMonth() > 9 ? this.getMonth().toString()
			: '0' + this.getMonth());
	str = str.replace(/M/g, this.getMonth());

	str = str.replace(/w|W/g, Week[this.getDay()]);

	str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString()
			: '0' + this.getDate());
	str = str.replace(/d|D/g, this.getDate());

	str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString()
			: '0' + this.getHours());
	str = str.replace(/h|H/g, this.getHours());
	str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes()
			.toString() : '0' + this.getMinutes());
	str = str.replace(/m/g, this.getMinutes());

	str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds()
			.toString() : '0' + this.getSeconds());
	str = str.replace(/s|S/g, this.getSeconds());
	return str;
};
//
/**
 * 比较两个日期的大小 格式：yyyy-MM-dd
 * 
 * @param date1
 * @param date2
 * @returns {Boolean} date1大于date2时为true否则为false
 */
function compareDay(date1, date2) {// a , b 格式為 yyyy-MM-dd
	if (typeof (date1) != 'undefined' && date1 != ""
			&& typeof (date2) != 'undefined' && date2 != "") {
		var a1 = date1.split("-");
		var b1 = date2.split("-");
		var d1 = new Date(a1[0], a1[1], a1[2]);
		var d2 = new Date(b1[0], b1[1], b1[2]);
		if (Date.parse(d1) - Date.parse(d2) > 0) {// a<b
			return true;
		}
	}
	return false;
}

/**
 * 比较两个字符型日期时间的大小 格式：yyyy-MM-dd HH:mm:ss
 * 
 * @param date1
 * @param date1
 * @returns {Boolean} date1大于等于date2是为true,否则为false
 */
function compareDateTime(date1, date2) {
	var beginTimes = date1.substring(0, 10).split('-');
	var endTimes = date2.substring(0, 10).split('-');
	var beginTime = beginTimes[0] + '/' + beginTimes[1] + '/' + beginTimes[2]
			+ ' ' + date1.substring(10, 19);
	var endTime = endTimes[0] + '/' + endTimes[1] + '/' + endTimes[2] + ' '
			+ date2.substring(10, 19);

	var a = (Date.parse(endTime) - Date.parse(beginTime)) / 3600 / 1000;
	if (a <= 0) {
		return true;
	} else if (a > 0) {
		return false;
	}
}


/**
 * 给对象添加事件函数
 * 
 * @param oTarget
 *            对象
 * @param sEventType
 *            事文化
 * @param fnHandler
 *            函数
 */
function addCustomEvent(oTarget, sEventType, fnHandler) {
	if (oTarget.addEventListener)
	// for dom
	{
		oTarget.addEventListener(sEventType, fnHandler, false);
	} else if (oTarget.attachEvent)
	// for ie
	{
		oTarget.attachEvent("on" + sEventType, fnHandler);
	}
}

/**
 * 比较两个日期时间的大小 格式：HH:mm:ss
 * 
 * @param time1
 * @param time2
 * @returns {Boolean} date1大于等date2是为true,否则为false
 */
function compareTime(time1, time2) {
	var curDate = new Date();
	var year = curDate.getFullYear();
	var month = curDate.getMonth();
	var day = curDate.getDate();

	var beginTime = year + '/' + month + '/' + day + ' ' + time1;
	var endTime = year + '/' + month + '/' + day + ' ' + time2;

	var a = (Date.parse(endTime) - Date.parse(beginTime)) / 3600 / 1000;
	if (a <= 0) {
		return true;
	} else if (a > 0) {
		return false;
	}
}

/**
 * 判断是否为空
 * 
 * @param value
 * @returns {Boolean}
 */
function isNullorEmpty(value) {
	if (value == null || value == "" || typeof (value) == "undefined"||value == "null") {
		return true;
	}
	return false;

}

/**
 * 将空值转换成空的字符串
 * @param value
 * @returns {String}
 */
function isNullorEmptyToBlank(value) {
	if (value == null || value == "" || typeof (value) == "undefined") {
		return "";
	}
	else{
		return value;
	}
}

/**
 * 将空值转换成0
 * @param value
 * @returns 
 */
function isNullorEmptyToZero(value) {
	if (value == null || value == ""||value=="null" || typeof (value) == "undefined") {
		return 0;
	}
	else{
		return value;
	}
}

/**
 * 将日期转换成 yyyy-MM-dd 的字符串形式
 * @param date
 * @returns {String}
 */
function getDateStr(date) {
	var year = date.getFullYear();
	var month = (date.getMonth() + 1) + "";
	var day = date.getDate() + "";
	month = getLenStr(month, 2);
	day = getLenStr(day, 2);
	return year + "-" + month + "-" + day;
}

/**
 * 格式化指定长度的日符不足在前补0
 * @param v 字符
 * @param len 长度
 * @returns {String}
 */
function getLenStr(v, len) {
	while (v.length < len) {
		v = "0" + v;
	}
	return v;
}


/**
 * 将日期类型字符串转换成日期
 * 格式为：yyyy-MM-dd  yyyy-MM-dd HH:mm:ss
 * @param v_dateStr 日期字符
 * @returns
 */
function dateStrChangeDate(v_dateStr){
	var v_date = null;
	v_datStr = v_dateStr.subString(0,10);
	var a1=v_dateStr.split("-");
	v_date = new Date(a1[0],a1[1],a1[2]);
	v_date.setMonth(v_date.getMonth()-1);
	return v_date;
}

/**
 * 将日期时间类型字符串转换为日期时间类型
 * 格式化：yyyy-MM-dd HH:mm:ss
 * @param v_dateStr 日期字符
 */
function dateAllStrChangeDate(v_dateStr){
	var v_date = null;
	v_dateStr = v_dateStr.substring(0,19);
	var a1= v_dateStr.split(" ");
	var a11 = a1[0].split("-");
	var a21 = a1[1].split(":");
	v_date = new Date(a11[0],a11[1],a11[2]);
	v_date.setHours(a21[0],a21[1],a21[2],0);
	v_date.setMonth(v_date.getMonth()-1);
	return v_date;
}


/**根据ajax返回时间毫秒数转换时间字符串"YY-MM-dd HH:mm:ss"
 * 
 * @param milliseconds
 * @returns {String}
 * @author liuguocheng
 * 2013-01-21
 */
function conventMsToStrDate(milliseconds)
{
var now = new Date(milliseconds);
var year = now.getFullYear(); 
var month = now.getMonth() + 1;
var day = now.getDate(); 
var hh = now.getHours(); 
var mm = now.getMinutes(); 
var ss=now.getSeconds();
var clock = year +"-";
if(month < 10)
	clock+= "0";
clock += month +"-";
if(day < 10)
	clock+= "0";
clock += day +" ";
if(hh < 10)
	clock+= "0";
clock += hh +":";
if (mm < 10) 
	clock+= '0';
clock +=mm+":";
if (ss < 10) 
	clock+= '0';
clock +=ss;
return(clock);

}

/**
 * 清除指定的select
 * 
 * @param node_id
 *            selectId
 */
function clearSelect(node_id) {
	$('#' + node_id).get(0).options.length = 0;
	$('#' + node_id).get(0).options.add(new Option('请选择...', ''));
}

/**除法函数，用来得到精确的除法结果
*说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
*调用：accDiv(arg1,arg2)
*返回值：arg1除以arg2的精确结果
*/
function accDiv(arg1,arg2){
	var t1=0,t2=0,r1,r2;
	try{t1=arg1.toString().split(".")[1].length;}catch(e){}
	try{t2=arg2.toString().split(".")[1].length;}catch(e){}
	with(Math){
	r1=Number(arg1.toString().replace(".",""));
	r2=Number(arg2.toString().replace(".",""));
	return (r1/r2)*pow(10,t2-t1);
	}
}

/**给Number类型增加一个div方法，调用起来更加 方便*/
Number.prototype.div = function (arg){
	return accDiv(this, arg);
};

/**乘法函数，用来得到精确的乘法结果
*说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
*调用：accMul(arg1,arg2)
*返回值：arg1乘以 arg2的精确结果
*/
function accMul(arg1,arg2)
{
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length;}catch(e){}
	try{m+=s2.split(".")[1].length;}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}

/**给Number类型增加一个mul方法，调用起来更加方便。*/
Number.prototype.mul = function (arg){
	return accMul(arg, this);
};
/**保留小数 四舍五入  
 * number 需要处理的数据
 * scale 保留几位小数
 * **/	
function round(number,scale){
	    var vv = Math.pow(10,scale);
	    return accDiv(Math.round(accMul(number,vv)),vv);
}
/***等额本息还款 算出每期应还金额**/
function principalAndInterest( principal, periods, interestYear){
	//每期还本付息金额 = (贷款额 *期利率 *(1+期利率)^贷款期数 ) / ((1+期利率)^还款期数 - 1))
	if(isNaN(principal)||isNaN(periods)||isNaN(interestYear)){
		 throw "NaN";
	}
	//本金(贷款额)
	//var principalN = new Number(principal); 
	//期数
	// periods = new Number(periods); 
	//年利息
	// interestYear = new Number(interestYear); 
	//月利率  年利率 / 12*100
	 var interestMonth = (round(accDiv(interestYear,1200),8));
	//(1+期利率)^贷款期数 )
	 var principalSquare =(Math.pow((1+interestMonth),periods));
	 //被除数  (贷款额 *期利率 *(1+期利率)^贷款期数 )
	 var dividend = accMul(accMul(principal,interestMonth),principalSquare);
	 //整除数 ((1+期利率)^还款期数 - 1))
	 var divisor = principalSquare-1;
	 return round(accDiv(dividend,divisor),2);
}
/** 等额本息还款 
 * 	- param 金额p、期数n 
 * 	- return 每月应还金额monthly、总金额summoney、所得利息interest*/
function getMonthlyPaymentsByPN(principal, periods,interestYear){
	if(isNaN(principal)||isNaN(periods)||isNaN(interestYear)){
		 throw "NaN";
	}
//	var interestYear = 16;
//	if(periods == 1 || periods == 2){
//		interestYear = 12;
//	}else if(periods == 3 || periods == 4){
//		interestYear = 14;
//	}else if(periods == 5 || periods == 6){
//		interestYear = 16;
//	}
//	interestYear = 20 ;
	//月利率  年利率 / 12*100
	 var interestMonth = (round(accDiv(interestYear,1200),8));
	//(1+期利率)^贷款期数 )
	 var principalSquare =(Math.pow((1+interestMonth),periods));
	 //被除数  (贷款额 *期利率 *(1+期利率)^贷款期数 )
	 var dividend = accMul(accMul(principal,interestMonth),principalSquare);
	 //整除数 ((1+期利率)^还款期数 - 1))
	 var divisor = principalSquare-1;
	// 每月(应还/应收)金额
	var monthly = round(accDiv(dividend,divisor),2);
	// 总金额
	var summoney = round(accMul(monthly, periods),2);
	// 所得利息
	var interest = round((summoney - principal),2);
	return [monthly,summoney,interest];
}
//到期还款
function getMonthlyPaymentsByPN1(principal,periods,interest){
	//还款=总借款*（1+当月利率*月数）
	var interestMonth = (round(accDiv(interest,1200),8));

	var repayMonth = round(accMul(principal,(1+accMul(interestMonth,periods))),2);
	var repayAmount = repayMonth;
	return [repayMonth,repayAmount,(repayAmount-principal).toFixed(2)];
}

/** 等额本息还款 
 * 	- param 金额p、期数n 
 * 	- return 每月应还金额monthly、总金额summoney、所得利息interest*/
function getMonthlyPayments(principal, periods,interestYear){
	if(isNaN(principal)||isNaN(periods)){
		 throw "NaN";
	}	
	//月利率  年利率 / 12*100
	 var interestMonth = (round(accDiv(interestYear,1200),8));
	//(1+期利率)^贷款期数 )
	 var principalSquare =(Math.pow((1+interestMonth),periods));
	 //被除数  (贷款额 *期利率 *(1+期利率)^贷款期数 )
	 var dividend = accMul(accMul(principal,interestMonth),principalSquare);
	 //整除数 ((1+期利率)^还款期数 - 1))
	 var divisor = principalSquare-1;
	// 每月(应还/应收)金额
	var monthly = round(accDiv(dividend,divisor),2);
	// 总金额
	var summoney = round(accMul(monthly, periods),2);
	// 所得利息
	var interest = round((summoney - principal),2);
	return [monthly,summoney,interest];
}
/** 根据还款月份计算手续费 */
function getFactorageByMonth(month){
	if(isNaN(month)){
		throw "NaN";
	}
	var factorage = 0.00; // 手续费率
	if (month == 1 || month == 2) {// 还款期数等于1或2个月的,手续费率为0.02
		factorage = 0.02;
	} else if (month == 3 || month == 4) {// 还款期数等于3或4个月的,手续费率为0.03
		factorage = 0.03;
	} else if (month == 5 || month == 6) {// 还款期数等于5或6个月的,手续费率为0.04
		factorage = 0.04;
	}
	return factorage;
}

//会话无效重定向到指定页面
function sessionInvaildRedirect(data) {
	var sessionInvaildData = eval(data);
	var login_invalid = sessionInvaildData.login_invalid;
	if (login_invalid == '1') {
		$("body").css("display","none");
		alert("登陆过期，请重新登陆");
		window.location.href = sessionInvaildData.login_invalid_redirect;
		return false;
	}
}
/**
 * 禁用字符输入
 * @param id
 */
function disableNaN(id){
		$("#"+id).keydown(function(e) {
			// 注意此处不要用keypress方法，否则不能禁用　Ctrl+V 与　Ctrl+V,具体原因请自行查找keyPress与keyDown区分，十分重要，请细查
			if ($.browser.msie) { // 判断浏览器
				if (((event.keyCode > 47) && (event.keyCode < 58))
						|| (event.keyCode == 8)||(event.keyCode == 110)||((e.which > 95) && (e.which < 106))) {// 判断键值  
					return true;
				} else {
					return false;
				}
			} else {
				if (((e.which > 47) && (e.which < 58))
						|| (e.which == 8)
						|| (e.which == 17)||(e.which == 110)||((e.which > 95) && (e.which < 106))) {
					return true;
				} else {
					return false;
				}
			}
		}).focus(function() {
	this.style.imeMode = 'disabled';
	});// 禁用输入法,禁止输入中文字符
}
/**
 * 设置金额保留俩位小数
 * @param id
 */
function setMoneyInput(id){
		var obj =  $("#"+id);
		var  money = $("#"+id).val();
		var pa = /[。，、]+/;
		if((pa).test(money)){
			money =  money.replace(pa,"");
			obj.attr("value",money);
			return;
		}
		var moneys = money.split(".");
		if(moneys.length>2){
				var rmoney = parseFloat(moneys[0]+"."+moneys[1].substring(0,moneys[1].length));
				obj.attr("value",rmoney);
		}else	if(moneys.length==2){
			if(moneys[1].length>2){
				var rmoney = parseFloat(moneys[0]+"."+moneys[1].substring(0,2));
				obj.attr("value",rmoney);
			}
		}
}

/**
 * 
 * @param bitPosition 二进制 位数
 * @param ueinAuthentication  五项认证
 * @returns {Boolean}
 */
function authentication(bitPosition,ueinAuthentication){
	/** 用户电话认证通过 */
	var  USER_CHECKED_TEL_POSITION = 1;
	var USER_CHECKED_TEL = 1;
	/** 用户邮箱认证通过 */
	var USER_CHECKED_EMIAL_POSITION = 2;
	var USER_CHECKED_EMIAL = 2;
	/** 用户视频认证通过 */
	var USER_CHECKED_VIDEO_POSITION = 3;
	var USER_CHECKED_VIDEO = 4;
	/** 用户企业资质认证通过 */
	var USER_CHECKED_ENT_QUA_POSITION = 4;
	var USER_CHECKED_ENT_QUA = 8;
	/** 用户税单认证通过 */
	var USER_CHECKED_TAX_LIST_POSITION = 5;
	var USER_CHECKED_TAX_LIST = 16;
	/** 用户信用卡认证通过 */
	var USER_CHECKED_AEC_POSITION = 6;
	var USER_CHECKED_AEC = 32;
	/** 用户身份证认证通过 */
	var USER_CHECKED_ID_CARD_POSITION = 7;
	var USER_CHECKED_ID_CARD = 64;
	/** 用户学历认证通过 */
	var USER_CHECKED_DEGREE_POSITION = 8;
	var USER_CHECKED_DEGREE = 128;
	var flag = false;
	switch (bitPosition) {
		case USER_CHECKED_TEL_POSITION:
			// 用户电话认证检测
			flag = (ueinAuthentication & USER_CHECKED_TEL) > 0;
			break;
		case USER_CHECKED_EMIAL_POSITION:
			// 用户邮箱认证检测
			flag = (ueinAuthentication & USER_CHECKED_EMIAL) > 0;
			break;
		case USER_CHECKED_VIDEO_POSITION:
			// 用户视频认证检测
			flag = (ueinAuthentication & USER_CHECKED_VIDEO) > 0;
			break;
		case USER_CHECKED_ENT_QUA_POSITION:
			// 用户企业资质认证检测
			flag = (ueinAuthentication & USER_CHECKED_ENT_QUA) > 0;
			break;
		case USER_CHECKED_TAX_LIST_POSITION:
			// 用户税单认证检测
			flag = (ueinAuthentication & USER_CHECKED_TAX_LIST) > 0;
			break;
		case USER_CHECKED_AEC_POSITION:
			// 用户信用卡认证检测
			flag = (ueinAuthentication & USER_CHECKED_AEC) > 0;
			break;
		case USER_CHECKED_ID_CARD_POSITION:
			// 用户身份证认证检测
			flag = (ueinAuthentication & USER_CHECKED_ID_CARD) > 0;
			break;
		case USER_CHECKED_DEGREE_POSITION:
			// 用户学历认证检测
			flag = (ueinAuthentication & USER_CHECKED_DEGREE) > 0;
			break;
		default:
			flag = false;
			break;
	}
	return flag;
}
/**
 * 检查上传文件后缀
 */
function checkUploadImgSuffix(img){
	var suffixArray =["jpg","png","gif","bmp"];
	var suffix = img.substr(img.lastIndexOf(".")+1).toLowerCase();
	for(var i=0;i<suffixArray.length;i++){
		if(suffixArray[i]==suffix){
			return true;	
		}
	}
	return false;
}


/**
 * 选项卡
 * */
$.zqTab = function(_tab,_box,_hover,_shijian) {
	var _index;//索引值
	$(_tab).eq(0).addClass(_hover);//第一个导航高亮显示 
	$(_tab).bind(_shijian,function(){
		_index=$(_tab).index(this);//获取当前点击的索引值
		$(this).addClass(_hover).siblings().removeClass(_hover);//当前点击高亮显示
		$(_box).eq(_index).show().siblings().hide();//通过索引值让对应的选项内容区显示
	}).eq(0).bind(_shijian);
};


//除法
function division(arg1,arg2){ 
	var t1=0,t2=0,r1,r2; 
	try{t1=arg1.toString().split(".")[1].length;}catch(e){} 
	try{t2=arg2.toString().split(".")[1].length;}catch(e){} 
	with(Math){ 
	r1=Number(arg1.toString().replace(".","")); 
	r2=Number(arg2.toString().replace(".",""));
	return accMul((r1/r2),pow(10,t2-t1)); 
	} 
} 
//乘法
function multiplication(arg1,arg2) 
{ 
	var m=0,s1=arg1.toString(),s2=arg2.toString(); 
	try{m+=s1.split(".")[1].length;}catch(e){} 
	try{m+=s2.split(".")[1].length;}catch(e){} 
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m) 
} 
//加法 
function addition(arg1,arg2){ 
	var r1,r2,m; 
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;} 
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;} 
	m=Math.pow(10,Math.max(r1,r2)); 
	return (arg1*m+arg2*m)/m; 
} 
//减法 
function subtraction(arg1,arg2){
	var r1,r2,m,n;
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
	m=Math.pow(10,Math.max(r1,r2));
	n=(r1>=r2)?r1:r2;
	return ((arg1*m-arg2*m)/m).toFixed(n);
}

function setTwoDecimal(str){
	return Math.round(parseFloat(str)*100)/100;
}