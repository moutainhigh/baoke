<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<link rel="stylesheet" type="text/css" media="screen" href="${cdn}/js/jqgrid/themes/redmond/jquery-ui-1.8.2.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="${cdn}/js/jqgrid/themes/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="${cdn}/css/jquery-ui-timepicker-addon.css" />

		<script type="text/javascript"  src="${cdn}/js/jquery-1.6.3.js"></script>
		<script type="text/javascript"  src="${cdn}/js/jqgrid/jquery-ui-1.8.2.custom.min.js"></script>
		<script type="text/javascript"  src="${cdn}/js/jqgrid/grid.locale-en.js"></script>
		<script type="text/javascript"  src="${cdn}/js/jqgrid/jquery.jqGrid.min.js"></script>
		<script type="text/javascript"  src="${cdn}/js/jqgrid/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript"  src="${cdn}/js/ajaxfileupload.js"></script>
		<script type="text/javascript"  src="${cdn}/js/util.js"></script>
<title>系统消息</title>
</head>
<body>
<script>

var global_datetime_parameter={
	closeText: "完成",
	currentText: "今天",
	showButtonPanel: true,
	prevText: "前一月",
	nextText: "下一月",
	changeMonth: true,
	changeYear: true,
	dateFormat: "yy-mm-dd",
	dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
	 dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"],
	monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
	monthNamesShort: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
	timeFormat: "HH:mm:ss",
	showSecond: true,
	timeText:"时间",
	hourText:"时",
	 minuteText:"分",
	secondText:"秒"};
	function imageFormat(cellvalue, options, row){
		return '</pre><img src="http://img.haihu.com/'+cellvalue+'@1c_1e_100w" alt="'+cellvalue+'"></img><pre>';
	}
	function imageUnFormat(cellvalue, options, cell){
		return $('img', cell).attr('alt');
	}
$(document).ready(function(){
	getAdminHttpPushLog();
});


var localJustify = false ;
function getAdminHttpPushLog(){
var myDate = new Date();
var v =myDate.toLocaleTimeString();

	$("#AdminHttpPushLogTable").jqGrid({
		url: "${pageContext.request.contextPath}/getAdminHttpPushLogByCondition?v="+v,
		datatype: "json",
		mtype: "GET",
		height: 500,
		width: 1200,
		shrinkToFit:true,
		//自定义表头colNames:['编号','商家编号','广告描述','展示位置','广告链接','开始时间', '结束时间','展示次数','用户访问次数','页面访问次数'] ,
		colModel: [
			{name:"id", index:"id", editable:true, width:100,editoptions:{readonly:true}},
			{name:"createTime", index:"createTime", editable:true, width:160,
				formatter:'date',formoptions:{elmsuffix:'(*)'},
					formatoptions:{srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'},
					editoptions:{size:50, dataInit:function(el){
						$(el).datetimepicker(global_datetime_parameter);
					},
				}/* ,
				searchoptions : {
					dataInit : function(el) {
						$(el).datetimepicker(global_datetime_parameter);
					}
				} */
			},
			{name:"bizType", index:"bizType", editable:true, width:200},
			{name:"bizId", index:"bizId", editable:true, width:200},
			{name:"content", index:"content", editable:true, width:500},
			
	],
	viewrecords: true,
	multiselect: false,
	rownumbers: true,
	rowNum: 50,
	rowList: [5,10,20,50,100],
	prmNames: {search: "search"},
	jsonReader: {
		root:"list",     	 //包含实际数据的数组
		page:"currentPage",  //当前页
		total:"totalPages",  //总页数
		records: "totalRows",//查询出的记录数
		repeatitems : false
	},
	pager:"AdminHttpPushLogPager",
	caption: "系统消息",
	hidegrid: false,
	gridComplete: function () {
		if(!localJustify){
			justifyHScroll('AdminHttpPushLogTable','AdminHttpPushLogPager',30);
			localJustify = true;
		}
	}
});

var addAdminHttpPushLog=function(){
	jQuery("#AdminHttpPushLogTable").jqGrid('editGridRow','new',{
		addCaption: "正在添加数据中",
		editCaption: "正在编辑选中行",
		bSubmit: "确认",
		bCancel: "取消",
		processData: "Processing...",
		closeAfterAdd:true,
		closeAfterEdit:true,
		closeOnEscape:true,
		reloadAfterSubmit:false,
		recreateForm:true,
		onInitializeForm:function(formid){
			$(formid).attr('method','POST');
			$(formid).attr('action','');
			$(formid).attr('enctype','multipart/form-data');
		},
		beforeSubmit : function(postdata, fromid){
			var data = fromid.serialize();
			$.ajaxFileUpload({
				url:"/saveAdminHttpPushLog?oper=add&v="+v+"&"+data,
				secureuri:false,
				fileElementId:[''],
				dataType: 'json',
				success : function (data, status){
					var rowId=data.id;
					var msg='增加记录失败';
					if(data.result){
						msg='增加记录成功';
						jQuery("#editmodAdminHttpPushLogTable").hide();
						jQuery("#AdminHttpPushLogTable").jqGrid().trigger('reloadGrid');
					}
					alert(msg);
				},
			error: function(data, status, e){
				 alert(e);
			}
		});
		},
	});
}

var editAdminHttpPushLog=function(){
var row = jQuery("#AdminHttpPushLogTable").jqGrid('getGridParam','selrow');
if( row != null ) jQuery("#AdminHttpPushLogTable").jqGrid('editGridRow',row,{
		addCaption: "正在添加数据中",
		editCaption: "正在编辑选中行",
		bSubmit: "确认",
		bCancel: "取消",
		processData: "Processing...",
		closeAfterAdd:true,
		closeAfterEdit:true,
		closeOnEscape:true,
		reloadAfterSubmit:true,
		width: 800,
		height: 'auto',
		recreateForm:true,
		onInitializeForm:function(formid){
			$(formid).attr('method','POST');
			$(formid).attr('action','');
			$(formid).attr('enctype','multipart/form-data');
		},
		beforeSubmit : function(postdata, fromid){
			var data = fromid.serialize();
			$.ajaxFileUpload({
				url:"/saveAdminHttpPushLog?oper=edit&v="+v+"&"+data,
				secureuri:false,
				fileElementId:[''],
				dataType: 'json',
				success : function (data, status){
					var rowId=data.id;
					var msg='修改记录失败';
					if(data.result){
						msg='修改记录成功';
						jQuery("#editmodAdminHttpPushLogTable").hide();
						jQuery("#AdminHttpPushLogTable").jqGrid().trigger('reloadGrid');
					}
					alert(msg);
				},
			error: function(data, status, e){
				 alert(e);
			}
		});
		},
	});
	else alert("Please Select Row");
}

	$("#AdminHttpPushLogTable").jqGrid('navGrid','#AdminHttpPushLogPager',
		{del:false,
		add:false,
		addtext:'新增',
		edit:false,
		edittext:'编辑',
		search:false,
		addfunc:addAdminHttpPushLog,
		editfunc:editAdminHttpPushLog,
		}); 
	$("#AdminHttpPushLogTable").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
/* 	$("#AdminHttpPushLogTable").jqGrid('navButtonAdd','#AdminHttpPushLogPager',{
		caption:"导出excel",
		onClickButton : function () {
			$("#AdminHttpPushLogTable").jqGrid('excelExport',{"url":"${pageContext.request.contextPath}//getAdminHttpPushLogByCondition?v="+v});
		}
	}); */
}
</script>

<table style='vertical-align:top'>
<tr>
	<td style='vertical-align:top'>
		<table id="AdminHttpPushLogTable"></table>
		<div id="AdminHttpPushLogPager"></div>
	</td>
</tr>
</table>
</body>
</html>
