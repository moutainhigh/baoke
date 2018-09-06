	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	videoAuditTab = function(){
		var videoAuditTab = Ext.getCmp("video_Audit_Tab");
		if(videoAuditTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'video_Audit_Tab',
				height:'100%',
				width:'100%',
				title:'视频商品审核',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/video-audit" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});