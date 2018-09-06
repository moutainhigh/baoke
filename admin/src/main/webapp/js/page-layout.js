Ext.require(['*']);

Ext.onReady(function() {
	
	var meun = new Ext.data.TreeStore({
		proxy: {  
            type: 'ajax',  
            url: 'loadMenu'  
        },
        root: {
            text: 'root',
            id: 'menu-root',
            expanded: true
        }
    });
	
    var viewport = Ext.create('Ext.Viewport', {
        layout: {
            type: 'border'
        },
        defaults: {
            split: true
        },
        items: [{
        	region: 'west',
            collapsible: true,
            title: '请选择操作',
            split: true,
            width: '15%',
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            defaultType: 'treepanel',
            defaults: {
                rootVisible: false,
                flex: 1
            },
            items: [{
                store: meun,
                viewConfig: {
                    plugins: {
                       ptype: 'treeviewdragdrop',
                       enableDrag: true,
                       enableDrop: false
                    }
                },
                listeners : {
                	itemclick : function(current, record, item, index, e, eOpts){
                		if(record.isLeaf())
                			eval(record.internalId+"()");
                	}
                }
            }]
        },{
            region: 'center',
            id : 'body_panel',
            minHeight: 80,
            xtype:'tabpanel'
        }]
    });
});
