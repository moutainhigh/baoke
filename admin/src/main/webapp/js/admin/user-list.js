	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	userListTab = function(){
		var userListTab = Ext.getCmp("user_List_Tab");
		if(userListTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'user_List_Tab',
				height:'100%',
				width:'100%',
				title:'用户管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/user-list" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});