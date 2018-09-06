	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	indexInfoTab = function(){
		var indexInfoTab = Ext.getCmp("index_Info_Tab");
		if(indexInfoTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'index_Info_Tab',
				height:'100%',
				width:'100%',
				title:'首页',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/index-info" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});