
var commonTemplate = {
	tpl_jqgrid : ""	//jqgrid模板
};

commonTemplate.jqgrid = {
	gridView : function(_ops) {
		var defaults = {
			url: "",
			datatype: "json",
			mtype: "GET",
			height: $(window).height() - 228,
			width: $(window).width() - 15,
			shrinkToFit:true,
			colNames:[] ,
			colModel: [],
			viewrecords: true,
			multiselect: false,
			ondblClickRow: function() {},
			loadtext: "正在加载...",
			rownumbers: true,
			rowNum: 20,
			rowList: [5,10,20,50,100],
			prmNames: {search: "search"},
			jsonReader: {
				root:"list",     	 //包含实际数据的数组
				page:"currentPage",  //当前页
				total:"totalPages",  //总页数
				records: "totalRows",//查询出的记录数
				repeatitems : false
			},
			gridBox : "body",//jqgrid最终append 位置，默认在body里
			table:"",
			pager:"",
			caption: "",
			hidegrid: true,
			loadComplete : function() {}
		};//以该处的数据模板，在其他页面进行调用
		var obj = {
			ops : $.extend({}, defaults, _ops),
			init : function() {
				var _this = this;
				this.ops.loadComplete = function() {
					setTimeout(function(){
						_this.style_search_form();
						_this.updatePagerIcons();
					}, 0);
				}
				
				if(commonTemplate.tpl_jqgrid == "") {
					commonTemplate.tpl_jqgrid = $.getText("/commonTemplate/gridResult.html");
				}
				var html = commonTemplate.tpl_jqgrid;
				var options = {
					jqgridTable : this.ops.table,
					jqgridPager : this.ops.pager
				};
				$(this.ops.gridBox).append(_.template(html, options));
				
				$("#" + this.ops.table).jqGrid(this.ops);
				
				//构造jqGrid的查询界面,搜索输入框在header层下方
				if(this.ops.prmNames.search == "search"){
					$("#" + this.ops.table).jqGrid('filterToolbar',{
						stringResult: true,
						searchOnEnter : true
					});
				}
				$(window).resize(function(){
					$("#" + _this.ops.table).setGridWidth(_this.ops.width);
				});
			},
			style_search_form : function() {
				$(".ui-search-toolbar").css("height","40px");
				$(".ui-search-toolbar input").css("height","30px");
				$(".ui-search-toolbar select").css("height","30px");
			},
			style_edit_form : function (form) {
				//update buttons classes
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
				buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')
				
				buttons = form.next().find('.navButton a');
				buttons.find('.ui-icon').hide();
				buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
				buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');		
			},
			updatePagerIcons : function() {
				var replacement = 
				{
					'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
					'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
					'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
					'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				})
			}
		};
		obj.init();
	},
	
	addGridRow : function(add_ops) {
		var defaults = {
			table:"",
			pager:"",
			addCaption: "正在添加数据中",
			closeAfterAdd:true,
			width: 600,
			height: 'auto',
			bSubmit: "确认",
			bCancel: "取消",
			processData: "Processing...",
			closeOnEscape: true,
			reloadAfterSubmit: false,
			recreateForm: true,
			beforeShowForm : function(e) {},//加载按钮样式
			onInitializeForm: function(formid){},//触发一次，为编辑或者添加模式创建数据时触发
			beforeSubmit : function(){}
		};
		var obj = {
			ops : $.extend({}, defaults, add_ops),
			init : function() {
				var _this = this;
				this.ops.beforeShowForm = function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />')
					_this.style_edit_form(form);
				}
				
				this.ops.onInitializeForm = function(formid) {
					$(formid).attr('method','POST');
					$(formid).attr('action','');
					$(formid).attr('enctype','multipart/form-data');
				}
				
				$("#" + this.ops.table).jqGrid('editGridRow','new',this.ops);
				$(".jqmOverlay").hide();
			},
			style_edit_form : function (form) {
				//update buttons classes
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
				buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')
				
				buttons = form.next().find('.navButton a');
				buttons.find('.ui-icon').hide();
				buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
				buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');		
			}
		};
		obj.init();
	},
	
	editGridRow : function(edit_ops) {
		var defaults = {
			table:"",
			pager:"",
			editCaption: "正在编辑选中行",
			closeAfterEdit:true,
			width: 600,
			height: 'auto',
			bSubmit: "确认",
			bCancel: "取消",
			processData: "Processing...",
			closeOnEscape:true,
			reloadAfterSubmit:true,
			recreateForm:true,
			add_beforeShowForm : function(e) {},//自定义方法
			beforeShowForm : function(e) {},//加载按钮样式
			onInitializeForm: function(formid){},//触发一次，为编辑或者添加模式创建数据时触发
			beforeSubmit : function(){}
		};
		var obj = {
			ops : $.extend({}, defaults, edit_ops),
			init : function() {
				var _this = this;
				this.ops.beforeShowForm = function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />')
					_this.style_edit_form(form);
					_this.ops.add_beforeShowForm(e);
				}
				
				this.ops.onInitializeForm = function(formid) {
					$(formid).attr('method','POST');
					$(formid).attr('action','');
					$(formid).attr('enctype','multipart/form-data');
				}
				var row = $("#" + this.ops.table).jqGrid('getGridParam','selrow');
				if( row != null ){
					$("#" + this.ops.table).jqGrid('editGridRow',row,this.ops);
				}else{
					alert("请选择一行");
				}
				$(".jqmOverlay").hide();
			},
			style_edit_form : function (form) {
				//update buttons classes
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
				buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')
				
				buttons = form.next().find('.navButton a');
				buttons.find('.ui-icon').hide();
				buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
				buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');		
			}
		};
		obj.init();
	},
	
	navGrid : function(nav_ops) {
		var defaults = {
			table: "",
			pager: "",
			alertcap: "提示",
			alerttext: '<div style="margin-bottom: 20px;margin-top: 20px;text-align: center;">请选择一行记录</div>',//当edit,delete or view一行记录时的文本提示
			//position: left,//定义按钮位置，可选值left, center and right.默认left
				
			add: false,//是否启用新增功能，当点击按钮时会触发editGridRow事件
			addtext:'新增',//新增按钮上的文字
			addtitle: '新增一行',//当鼠标移到新增按钮上时显示的提示
			addfunc: function() {},//如果定义，则用定义的函数替代原有的add函数，这个函数将不接受任何参数；
			addicon: 'ace-icon fa fa-plus-circle purple',//给新增功能设置图标，只有UI theme里的图标才可以使用
			
			edit: false,//是否启用可编辑功能，当编辑时会触发事件editGridRow
			edittext:'编辑',
			edittitle: "编辑所选择的行",
			editfunc: function() {},//如果定义，则用定义的函数替代原有的edit函数，编辑的行的id作为参数传入这个函数；
			editicon: 'ace-icon fa fa-pencil blue',
			
			del: false,//是否启用删除功能，启用时会触发事件delGridRow
			deltext: "删除",
			deltitle: "删除所选择的行",
			//delfunc:deleteAdminOperatorLog,//如果定义，则用定义的函数替代原有的del函数，编辑的行的id作为参数传入这个函数；
			delicon: 'ace-icon fa fa-trash-o red',
			
			search: false,//是否启用搜索按钮，会触发searchGrid 事件
			searchtext:'搜索',
			searchtitle: '搜索',
			searchicon: 'ace-icon fa fa-search orange',
			
			refresh: true,//是否启用刷新按钮，当点击刷新按钮时会触发trigger(“reloadGrid”)事件，而且会清空搜索条件值
			refreshtext: "刷新",
			refreshtitle: "重新加载",
			//refreshstate:指明表格如何刷新。firstpage：从第一页开始刷新；current：只刷新当前页内容;默认，firstpage
			//afterRefresh:当点击刷新按钮之后触发此事件
			refreshicon: 'ace-icon fa fa-refresh green',
			
			view: false,//是否启用查看按钮，会触发事件viewGridRow
			viewtext: "查看",
			viewtitle: "查看所选记录",
			viewicon: 'ace-icon fa fa-search-plus grey'
		};
		var obj = {
			ops : $.extend({}, defaults, nav_ops),
			init : function() {
				$("#" + this.ops.table).jqGrid('navGrid',"#" + this.ops.pager,this.ops);
			}
		};
		obj.init();
	},
	
	navButtonAdd : function(add_nav_ops) {
		var defaults = {
				table: "",
				pager: "",
				caption:"",//按钮名称
				onClickButton : function () {},
				buttonicon: ''//按钮的图标，string类型，必须为UI theme图标 
				//position：first或者last，按钮位置 
				//title：string类型，按钮的提示信息 
				//cursor：string类型，光标类型，默认为pointer 
				//id：string类型，按钮id
		};
		var obj = {
				ops : $.extend({}, defaults, add_nav_ops),
				init : function() {
					$("#" + this.ops.table).jqGrid('navButtonAdd',"#" + this.ops.pager,this.ops);
				}
		};
		obj.init();
	},
	
};
