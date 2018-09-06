	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	bannerListTab = function(){
		var bannerListTab = Ext.getCmp("banner_List_Tab");
		if(bannerListTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'banner_List_Tab',
				height:'100%',
				width:'100%',
				title:'页面管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/banner-list" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});