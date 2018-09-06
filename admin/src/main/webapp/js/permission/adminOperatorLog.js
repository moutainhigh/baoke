	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var adminOperatorLogManagerTab;

Ext.onReady(function() {
	
	adminOperatorLogManagerTab = function(){
		var adminOperatorLogTab = Ext.getCmp("admin_operator_log_manager_tab");
		if(adminOperatorLogTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'admin_operator_log_manager_tab',
				height:'100%',
				width:'100%',
				title:'后台用户操作记录',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/permission/adminOperatorLog" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			adminOperatorLogTab.show();
		}
	}
	
});