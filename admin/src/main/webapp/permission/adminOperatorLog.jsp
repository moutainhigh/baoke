<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/jqgrid/ui.jqgrid.css" />
<link rel="stylesheet" href="../css/jqgrid/ace.min.css" />
<link rel="stylesheet" href="../css/jqgrid/font-awesome.min.css" />

<script type="text/javascript"  src="/js/jquery-1.6.3.js"></script>
<script type="text/javascript"  src="/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript"  src="/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"  src="/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/jslib/underscore/underscore-min.js"></script>

<script type="text/javascript"  src="/jslib/jquery-extend.js"></script>
<script type="text/javascript"  src="/js/common/templateJs.js"></script>

<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<title>后台操作日志</title>
</head>
<body>
<script>
$(document).ready(function(){
	var v =(new Date()).toLocaleTimeString();
	
	var gridView = commonTemplate.jqgrid.gridView({
		url: "/permission/getAdminOperatorLogByCondition?v="+v,
		colNames:['编号','用户名称','角色名称','操作url','操作描述','创建时间', '更新时间'] ,
		colModel: [
			{name:"id", index:"id", editable:true, width:100,editoptions:{readonly:true,size:50}},
			{name:"userName", index:"userName", editable:false, width:100},
			{name:"roleId", index:"roleId", editable:true, width:100, stype:"select", edittype:'select',formatter:'select', 
				editoptions:{
					value:":全部;"+"${roleValue}"
				},
			},
			{name:"url", index:"url", editable:true, width:100,editoptions:{size:50}},
			{name:"description", index:"description", editable:true, width:100,editoptions:{size:50}},
			{name:"gmtCreate", index:"gmtCreate", editable:true, width:100,
				editoptions:{
					size:24, 
					dataInit:function(element){
		            	jQuery(element).bind('focus',function(){
		            		WdatePicker({
		            			isShowClear:true,
		            			dateFmt:'yyyy-MM-dd HH:mm:ss'
		            			});
		            	});
		            }
				},
				searchoptions : {
					sopt:["eq","ne",'lt','le','gt','ge'],
		            dataInit:function(element){
		            	jQuery(element).bind('focus',function(){
		            		WdatePicker({
		            			isShowClear:true,
		            			dateFmt:'yyyy-MM-dd HH:mm:ss'
		            			});
		            	});
		            }
				}
			},
			{name:"gmtModified", index:"gmtModified", editable:true, width:100,
				editoptions:{
					size:24, 
					dataInit:function(element){
		            	jQuery(element).bind('focus',function(){
		            		WdatePicker({
		            			isShowClear:true,
		            			dateFmt:'yyyy-MM-dd HH:mm:ss'
		            			});
		            	});
		            }
				},
				searchoptions : {
					sopt:["eq","ne",'lt','le','gt','ge'],
		            dataInit:function(element){
		            	jQuery(element).bind('focus',function(){
		            		WdatePicker({
		            			isShowClear:true,
		            			dateFmt:'yyyy-MM-dd HH:mm:ss'
		            			});
		            	});
		            }
				}
			}
		],
		table:"jqgridTable_adminOperatorLog",
		pager:"jqgridPager_adminOperatorLog",
		caption: "后台操作日志"
	});
	
	//工具栏
	var navGrid = commonTemplate.jqgrid.navGrid({
		table:"jqgridTable_adminOperatorLog",
		pager:"jqgridPager_adminOperatorLog",
	});
	
	//自定义工具栏按钮
	var navButtonAdd = commonTemplate.jqgrid.navButtonAdd({
		table:"jqgridTable_adminOperatorLog",
		pager:"jqgridPager_adminOperatorLog",
		caption:"导出excel",//按钮名称
		onClickButton : function () {
			$(this).jqGrid('excelExport',{"url":"/permission/getAdminOperatorLogByCondition?v="+v});
		},
		buttonicon: 'ace-icon fa fa-floppy-o blue'//按钮的图标，string类型，必须为UI theme图标 
		
	});
})
</script>
</body>
</html>
