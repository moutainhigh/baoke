/**
 * mongo请求查询管理页面
 */
var requestStatistSearchTab , requestStatistSearchFrom;

Ext.require([
     'Ext.data.*',
     'Ext.grid.*',
     'Ext.tree.*'
]);

Ext.onReady(function() {
	
	 Ext.define('requestManagerThread', {
        extend: 'Ext.data.Model',
        fields: [
            'id','method','userId', 'startTime', 'requestBaseParams', 'responseBaseParams','cookie'
        ],
        idProperty: '_id'
    });
	
	// create the Data Store
    var store = Ext.create('Ext.data.Store', {
        pageSize: 17,
        model: 'requestManagerThread',
        remoteSort: true,
        proxy: {
            type: 'ajax',
            url: '/log/queryRequestStatistRecord',
            reader: {
            	type: 'json',
                root: 'list',
                totalProperty: 'totalRows'
            },
            simpleSortMode: true
        }
    });
	
    requestStatistSearchTab = function(){
		var searchManagerTab = Ext.getCmp("request_manager_tab");
		if(searchManagerTab == null){
			Ext.getCmp("body_panel").add({
				id:'request_manager_tab',
				width: 700,
		        height: '100%',
				title:'日志查询',
		        closable: true,
	            buttonAlign:'center',
                items: [{
                	xtype:'panel',
                	width:'100%',
                	layout:'hbox',
                	title:'请填写查询条件',
                	collapsible: true,
                	buttonAlign:'center',
                	items:[{
                        xtype: 'container',
                        flex: 1,
                        border:false,
                        layout: 'anchor',
                        defaultType: 'textfield',
                        items: [
	                        {
	                        	xtype: 'fieldcontainer',
	                            combineErrors: true,
	                            msgTarget : 'side',
	                            layout: 'hbox',
	                            anchor:'95%',
	                            defaults: {
	                                flex: 1,
	                                labelWidth: 50,
	                                hideLabel: false
	                            },
	                            items: [
			    					{
			    						xtype: 'textfield',
			                            fieldLabel: '用户ID',
			                            name: 'r_user_id',
			                            id:'r_user_id',
			                            anchor:'20%',
			                            margin: '10 10 10 10',
									    allowBlank:true
			                        },{
		                        	 	xtype: 'textfield',
			                            fieldLabel: '操作',
			                            name: 'r_method',
			                            id:'r_method',
			                            anchor:'20%',
			                            margin: '10 10 10 10',
									    allowBlank:true
			                	    },{
									    xtype: 'textfield',
									    name: 'r_cookie',
									    id: 'r_cookie',
									    fieldLabel: 'COOKIE',
									    anchor:'20%',
			                            margin: '10 10 10 10',
									    allowBlank:true
									},{
									    xtype: 'datefield',
									    name: 'r_time',
									    id: 'r_time',
									    fieldLabel: '日期',
									    anchor:'30%',
			                            margin: '10 10 10 10',
									    allowBlank:true
									}
	    						]
	                        }
                       ]
                    }],
                    buttons:[{text:'检索',handler:function(e){
                    	var userId		= Ext.getCmp("r_user_id").getValue();
                		var method		= Ext.getCmp("r_method").getValue();
                		var cookie		= Ext.getCmp("r_cookie").getValue();
                		var time	= Ext.util.Format.date(Ext.getCmp("r_time").getValue(),"Y-m-d");
                		requestStatistSearchFrom = "searchFromEngine";
            			store.load({params:{"searchFrom":requestStatistSearchFrom,"userId":userId,"time":time,"cookie":cookie,"queryDate":time,"method":method,"limit":150,"page":0}});
                    }}]
                },{
    		    	xtype : 'grid',
    		        id : 'request_grid',
    		        store: store,
    		        stripeRows:true,
    		        loadMask: true,
    		        height: 450,
                    viewConfig:{
                        enableTextSelection:true
                    },
    		        // grid columns
    		        columns:[{
    		            text: "用户ID",
    		            dataIndex: 'userId',
    		            width: 100,
    		            sortable: false,
    		            menuDisabled:true,
    		            align:"center",
    		            fixed:true
    		        },{
    		            text: "操作",
    		            dataIndex: 'method',
    		            width: 250,
    		            sortable: false,
    		            menuDisabled:true,
    		            align:"left",
    		            fixed:true
    		        },{
    		            text: "时间",
    		            dataIndex: 'startTime',
    		            width: 160,
    		            sortable: false,
    		            menuDisabled:true,
    		            align:"center",
    		            fixed:true
    		        },{
    		            text: "Cookie",
    		            dataIndex: 'cookie',
    		            width: 300,
    		            sortable: false,
    		            align:"center"
    		        },{
    		            text: "请求",
    		            dataIndex: 'requestBaseParams',
    		            width: 80,
    		            align:"center",
    		            menuDisabled:true,
    		            sortable: false,
    		            renderer : function(value, cellmeta, record){
    		            	var requestBaseParams = record.data["requestBaseParams"];
    		        		var id = record.data["id"]+"_request";
    		            	return '<a type="button" onclick="queryInfo(\''+id+'\')" style="cursor:pointer;color:red">查看</a><span id="'+id+'" style="display:none"><xmp>'+requestBaseParams+'</xmp></span>';
    		            }
    		        },{
    		            text: "响应",
    		            dataIndex: 'responseBaseParams',
    		            width: 80,
    		            align:"center",
    		            fixed:true,
    		            menuDisabled:true,
    		            sortable: false,
    		            renderer : function(value, cellmeta, record){
    		        		var responseBaseParams = record.data["responseBaseParams"];
    		        		var id = record.data["id"]+"_response";
    		            	return '<a onclick="queryInfo(\''+id+'\')" style="cursor:pointer;color:red">查看</a><span id="'+id+'" style="display:none"><xmp>'+responseBaseParams+'</xmp></span>';
    		            }
    		        }]
                }]
			}).show();
		}else{
			searchManagerTab.show();
		}
	}
    
    queryInfo = function (id){
    	var content=document.getElementById(id).innerHTML;
		var moreUserInfo = Ext.create('Ext.Window', {
	        title: '详细信息',
	        width: 1000,
	        height: 500,
	        x: 250,
	        y: 100,
	        autoScroll: true,
	        headerPosition: 'top',
	        modal: true,
	        html: content,
	        constrain: true,
	        region:"region"
	    });
		moreUserInfo.show();
	}
});


