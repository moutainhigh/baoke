	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var testTab;

Ext.onReady(function() {
	
	popularGoodTab = function(){
		var popularGoodTab = Ext.getCmp("popular_Good_Tab");
		if(popularGoodTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'popular_Good_Tab',
				height:'100%',
				width:'100%',
				title:'人气热门商品',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/admin/popular-good" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			indexInfoTab.show();
		}
	}
	
});