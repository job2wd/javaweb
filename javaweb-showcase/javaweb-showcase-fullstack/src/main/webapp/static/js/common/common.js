if(typeof String.prototype.trim !== 'function') 
{ 
  String.prototype.trim = function() 
  { 
   return this.replace(/^\s+|\s+$/g, ''); 
  };
}
// 查看页面 禁用文本框等选项
function disabledPage() {
	$("input[type=text]").attr("readonly", "readonly");
	$("textarea").attr("readonly", "readonly");
	$("input[type=checkbox]").attr("disabled", "disabled");
	$("select").attr("disabled", "disabled");
}
/**************************************************************
封装好的简化调用方法
 *************************************************************/
function popup_show(title, url, height, width, top, left) {
	height = height == undefined ? "400px" : height;
	width = width == undefined ? "600px" : width;
	top = width == undefined ? "100px" : top;
	left = left == undefined ? "" : left;
	
	$.layer({
		type : 2,
		title : [title , true],
		iframe : {src : url},
		area : [width , height],//控制层宽高。'255px'：宽，'auto'：高
		offset : [top , left]//控制层坐标。'100px'：纵坐标，'50%'：横坐标
	});
}
//关闭 layer
function closeLayer() {
	var index = parent.layer.getFrameIndex();
	parent.layer.close(index);
}
/**
 * chooseOne()函式，参数为触发改函数的元素本身 
 * 判断obj集合中的i元素是否为cb，若否 则表示未被点选
 * 若是 但原先未被勾选则变成勾选；反之 则变为未勾选   else  obj[i].checked = cb.checked; 
 * 若要至少勾选一个的话，else obj[i].checked = true; 
 */
function chooseOne(cb, name) {
	var obj = document.getElementsByName(name);
	for (i = 0; i < obj.length; i++) {
		if (obj[i] != cb)
			obj[i].checked = false;
		else
			obj[i].checked = cb.checked;
	}
}
/**
 * 至少勾选一个
 */
function chooseOne1(cb, name) {
	var obj = document.getElementsByName(name);
	for (i = 0; i < obj.length; i++) {
		if (obj[i] != cb)
			obj[i].checked = false;
		else
			obj[i].checked = true; 
	}
}
//jquery获取复选框值
function getCheckedValue(name){
	var chk_value =[];
	$('input[name="'+name+'"]:checked').each(function(){
		chk_value.push($(this).val());
	});
	return chk_value.length==0 ?'':chk_value;
}

//列表复选项全选功能
function cBox(e,id) {
	$("#"+id+" input:checkbox").each(function() {$(this).attr("checked",undefined == $(e).attr("checked") ? false : $(e).attr("checked"));});
}
// 去除日期中- ： 空格
function formatDate(date,type){
	return date.replaceAll("-", "").replaceAll(":","").replaceAll(" ","");
}
String.prototype.replaceAll = function (AFindText,ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp,ARepText);
};
function gotoPage(url){
	window.location.href=url;
}

/**************************************************************
列表checkbox 单选多选检查
 *************************************************************/
function validBatch(name) {
	name = (name == undefined ? "ids" : name);
	var count = $('input[name="'+name+'"]:checked').length;
	if (count==0) {
		alert('请选择要操作的数据行！');
		return false;
	}
	return true;
}
function validUnique(name) {
	name = (name == undefined ? "ids" : name);
	var count = $('input[name="'+name+'"]:checked').length;
	if (count>1) {
		alert('请选择一条记录！');
		return false;
	}
	return true;
}
/** 列表checkbox 单选多选检查 end */

/**
 * 显示图像
 */
function showChart(id, obj, queryParam, width, height) {
	height = height == undefined ? "" : height;
	width = width == undefined ? "" : width;
	var param = "";
	if (height != undefined && height != null) {
		param += "&height=" + height;
	}
	if (width != undefined && width != null) {
		param += "&width=" + width;
	}
	var queryParam = encodeURIComponent(queryParam);
	jQuery.ajax({
		type : "post",
		contentType : 'application/text',
		url : ctxPath+"/manager/getChartCont.ac?groupId=" + id + param
				+ "&andParam=" + queryParam + "&date=" + new Date().getTime(),
		data : "dataTime=1",
		async : true,
		dataType : 'json',
		success : function(data) {
			obj.html(data);
		},
		error : function(XMLResponse) {
			//layer.alert("执行过程异常!");
		}
	});
	return false;
}

/**
 * 全选，全不选
 */
function checkAll() {
	if ($("#checkall").attr("checked")) {
		$("input[name='ids']").each(function() {
			$(this).attr("checked", true);
		});
	} else {
		$("input[name='ids']").each(function() {
			$(this).attr("checked", false);
		});
	}
}

/**
 * 表单提交
 * 
 * @returns {Boolean}
 */
function submitForm() {
	if (!pojo.form.validate($('#form1'))) {
		return false;
	}else{
		$("#form1").submit();
	}
} 


/**
 * 删除
 * 
 * @param url
 */
function dele(url){
	var ids = getCheckIds();
	if(confirm('确定删除吗 ？')){
		window.location.href=url+'?ids='+ids;
	}
}

/**
 * 得到已经选择的 id
 * 
 * @returns
 */
function getCheckIds(){
    var ids = '';
    var idsObj = document.getElementsByName('ids');
    for (var i = 0; i < idsObj.length; i++) {
        if (idsObj[i].checked == true) {
            ids = idsObj[i].value + ',' + ids;
        }
    }
    ids = ids.substring(0, ids.length - 1);
    if (ids == '') {
        alert('请选择要操作的数据行！');
        return false;
    }
    return ids;
} 
