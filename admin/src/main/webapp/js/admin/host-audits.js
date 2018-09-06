	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	hostAuditsTab = function(){
		var hostAuditsTab = Ext.getCmp("host_Audits_Tab");
		if(hostAuditsTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'host_Audits_Tab',
				height:'100%',
				width:'100%',
				title:'主播审核',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/host-audits" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});