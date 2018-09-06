	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var adminRoleAuthorityManagerTab;

Ext.onReady(function() {
	
	adminRoleAuthorityManagerTab = function(){
		var view = Ext.getCmp("admin_role_authority_manager_tab");
		if(view==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'admin_role_authority_manager_tab',
				height:'100%',
				width:'100%',
				title:'角色权限表',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/permission/adminRoleAuthority" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			view.show();
		}
	}
	
});