	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	videoListTab = function(){
		var videoListTab = Ext.getCmp("video_List_Tab");
		if(videoListTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'video_List_Tab',
				height:'100%',
				width:'100%',
				title:'视频管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/video-list" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});