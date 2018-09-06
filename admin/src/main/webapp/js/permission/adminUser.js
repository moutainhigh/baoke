	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var adminUserManagerTab;

Ext.onReady(function() {
	
	adminUserManagerTab = function(){
		var adminUserTab = Ext.getCmp("admin_user_manager_tab");
		if(adminUserTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'admin_user_manager_tab',
				height:'100%',
				width:'100%',
				title:'后台用户管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/permission/adminUser" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			adminUserTab.show();
		}
	}
	
});