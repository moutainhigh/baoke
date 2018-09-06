	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	commodityAdminTab = function(){
		var commodityAdminTab = Ext.getCmp("commodity_Admin_Tab");
		if(commodityAdminTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'commodity_Admin_Tab',
				height:'100%',
				width:'100%',
				title:'商城管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/commodity-admin" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});