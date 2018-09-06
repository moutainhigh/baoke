	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	informationListTab = function(){
		var informationListTab = Ext.getCmp("information_List_Tab");
		if(informationListTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'information_List_Tab',
				height:'100%',
				width:'100%',
				title:'信息管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/information-list" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});