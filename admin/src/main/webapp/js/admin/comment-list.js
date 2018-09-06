	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	commentListTab = function(){
		var commentListTab = Ext.getCmp("comment_List_Tab");
		if(commentListTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'comment_List_Tab',
				height:'100%',
				width:'100%',
				title:'评论管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/comment-list" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});