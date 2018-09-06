	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	sensitiveListTab = function(){
		var sensitiveListTab = Ext.getCmp("sensitive_List_Tab");
		if(sensitiveListTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'sensitive_List_Tab',
				height:'100%',
				width:'100%',
				title:'敏感词管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/sensitive-list" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});