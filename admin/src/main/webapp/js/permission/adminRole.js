	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var userRoleManagerTab;

Ext.onReady(function() {
	
	adminRoleManagerTab = function(){
		var adminRoleTab = Ext.getCmp("admin_role_manager_tab");
		if(adminRoleTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'admin_role_manager_tab',
				height:'100%',
				width:'100%',
				title:'后台角色管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/permission/adminRole" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			adminRoleTab.show();
		}
	}
	
});