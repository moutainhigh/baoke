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
<title>用户管理</title>
</head>
<body>
<script>
$(document).ready(function(){
	var v =(new Date()).toLocaleTimeString();
	
	var gridView = commonTemplate.jqgrid.gridView({
		url: "/permission/getAdminUserByCondition?v="+v,
		colNames:['编号','用户名称','角色名称', '状态','创建人','更新人','密码','最后登录ip','最后登录时间','创建时间','更新时间'] ,
		colModel: [
			{name:"id", index:"id", hidden:true,editable:true, width:100,editoptions:{readonly:true}},
			{name:"userName", index:"userName", editable:true, width:100},
			{name:"roleId", index:"roleId", editable:true, width:100,stype:"select",edittype:"select", formatter:'select',editoptions:{
				value:":全部;" + "${roleValue}"
            }},
			{name:"status", index:"status", editable:true, width:100,stype:"select",edittype:"select", formatter:'select',editoptions:{
			    value:":全部;0:正常;1:冻结"
            }},
			{name:"createdBy", index:"createdBy", editable:true, width:100},
			{name:"updatedBy", index:"updatedBy", editable:true, width:100},
			{name:"password", index:"password", editable:true, width:100},
			{name:"lastLoginIp", index:"lastLoginIp", editable:false, width:100},
			{name:"lastLoginTime", index:"lastLoginTime", editable:false,
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
			{name:"gmtCreate", index:"gmtCreate", editable:false,
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
			{name:"gmtModified", index:"gmtModified", editable:false,
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
		ondblClickRow: function() { editGridRow();},
		table:"jqgridTable_adminUser",
		pager:"jqgridPager_adminUser",
		caption: "后台用户表"
	});
	
	//新增
	var addGridRow = function(){
		commonTemplate.jqgrid.addGridRow({
			table:"jqgridTable_adminUser",
			pager:"jqgridPager_adminUser",
			beforeSubmit : function(postdata, fromid){
				var data = fromid.serialize();
				var _this = this;
				$.ajax({
					url:"/permission/saveAdminUser?oper=add&v="+v+"&"+data,
					dataType: 'json',
					success : function (data){
						var rowId=data.id;
						var msg='增加记录失败';
						if(data.result){
							msg='增加记录成功';
							$("#editmod" + _this.table).hide();
							$("#" + _this.table).jqGrid().trigger('reloadGrid');s
						}
						alert(msg);
					}
				});
			}
		});
	}
	
	//编辑
	var editGridRow = function(){
		commonTemplate.jqgrid.editGridRow({
			table:"jqgridTable_adminUser",
			pager:"jqgridPager_adminUser",
			beforeSubmit : function(postdata, fromid){
				var data = fromid.serialize();
				var _this = this;
				$.ajax({
					url:"/permission/saveAdminUser?oper=edit&v="+v+"&"+data,
					dataType: 'json',
					success : function (data, status){
						var rowId=data.id;
						var msg='修改记录失败';
						if(data.result){
							msg='修改记录成功';
							$("#editmod" + _this.table).hide();
							$("#" + _this.table).jqGrid().trigger('reloadGrid');
						}
						alert(msg);
					},
					error: function(data, status, e){
						 alert(e);
					}
				});
			}
		});
	}
		
	
	//工具栏
	var navGrid = commonTemplate.jqgrid.navGrid({
		table:"jqgridTable_adminUser",
		pager:"jqgridPager_adminUser",
		add: true,//是否启用新增功能，当点击按钮时会触发editGridRow事件
		addfunc: addGridRow,//如果定义，则用定义的函数替代原有的add函数，这个函数将不接受任何参数；
		
		edit: true,//是否启用可编辑功能，当编辑时会触发事件editGridRow
		editfunc: editGridRow//如果定义，则用定义的函数替代原有的edit函数，编辑的行的id作为参数传入这个函数；
		
	});
	
	//自定义工具栏按钮
	var navButtonAdd = commonTemplate.jqgrid.navButtonAdd({
		table:"jqgridTable_adminUser",
		pager:"jqgridPager_adminUser",
		caption:"导出excel",//按钮名称
		onClickButton : function () {
			$(this).jqGrid('excelExport',{"url":"/permission/getAdminUserByCondition?v="+v});
		},
		buttonicon: 'ace-icon fa fa-floppy-o blue'//按钮的图标，string类型，必须为UI theme图标 
		
	});
})
</script>
</body>
</html>
