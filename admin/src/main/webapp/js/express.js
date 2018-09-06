	Ext.require([
        'Ext.form.*',
	    'Ext.tip.QuickTipManager'
	]);

var expressManagerTab;

Ext.onReady(function() {
	
	expressManagerTab = function(){
		var orderTab = Ext.getCmp("express_manager_tab");
		if(orderTab==null){
			Ext.getCmp("body_panel").add({
				xtype:'panel',
				id:'express_manager_tab',
				height:'100%',
				width:'100%',
				title:'订单包裹管理',
				layout:'fit',
		        closable: true,
	            buttonAlign:'center',
	            fitToFrame: true, 
                items: [{
                	html: '<iframe id="frame1" src="index?path=/order/userTradeExpress" frameborder="0" width="100%" height="100%"></iframe>' 
                }]
			}).show();
		}else{
			orderTab.show();
		}
	}
	
});