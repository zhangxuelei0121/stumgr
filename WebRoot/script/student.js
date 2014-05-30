Ext.onReady(function(){

	/**
	 * 前台页面表格部分显示学生信息列表
	 */
	
	var sexRenderer = function(value){
		if(value == 1){
			return '<span style="color:blue;font-weight:bold;">男</span>';
		}else if(value == 2){
			return '<span style="color:red;font-weight:bold;">女</span>';
		}
	};
	
	Ext.define('StudentRecord',{
		extend : 'Ext.data.Model',
		 fields: [
	        {name: 'id',  type: 'int'},
	        {name: 'code',   type: 'string'},
	        {name: 'name', type: 'string'},
	        {name: 'sex', type: 'int'},
	        {name: 'birthday', type: 'string'},
	        {name: 'origin', type: 'string'},
	        {name: 'dept', type: 'string'}
    	]
	});
	
	var store = Ext.create('Ext.data.Store', {
	     pageSize : 15,
	     proxy: {
	         type: 'ajax',
	         //url: 'jsp/list.jsp',
	         url: 'student?method=list',
	         reader: {
	             type: 'json',
	             totalProperty : 'totalCount',
	             root: 'result',
	             idProperty : 'id'
	         }
	     },
	     model: StudentRecord,
	     remoteSort: true
 	});
 	store.load();
 	
 	var columns = [
 		{headers : '学号', dataIndex : 'code'},
 		{headers : '姓名', dataIndex : 'name'},
 		{headers : '性别', dataIndex : 'sex', renderer:sexRenderer},
 		{headers : '出生日期', dataIndex : 'birthday',type:'date',dateFormat:'Y-m-d'},
 		{headers : '籍贯', dataIndex : 'origin'},
 		{headers : '所属系', dataIndex : 'dept'}
 	];
 	
 	var grid = Ext.create('Ext.grid.Panel',{
		 title : '学生信息列表',
		 region : 'center',
		 loadMask : true,
		 store : store,
		 columns : columns,
		 forceFit : true,
		 bbar : new Ext.PagingToolbar({
		 	pageSize : 15,
		 	store : store,
		 	displayInfo : true
		 })
 	});
 	
 	
 	/**
 	 * 学生信息的表单
 	 */
 	var form = Ext.create('Ext.form.Panel',{
 		title : '编辑学生信息',
 		region : 'east',
 		frame : true,
 		width : 300,
 		autoHeight : true,
 		labelAlign : 'right',
 		labelWidth : 60,
 		defaultType : 'textfield',
 		defaults : {
 			width : 200,
 			allowBlank : false
 		},
 		items : [{
 			xtype : 'hidden',
 			name : 'id'
 		},{
 			fieldLabel : '学号',
 			name : 'code'
 		},{
 			fieldLabel : '姓名',
 			name : 'name'
 		},{
 			fieldLabel: '性别',
 			name : 'sex',
            xtype: 'combo',
            store: new Ext.data.SimpleStore({
                fields: [{name:'value',type:'int'},'text'],
                data: [['1','男'],['2','女']]
            }),
            emptyText: '请选择',
            queryMode: 'local',
            triggerAction: 'all',
            valueField: 'value',
            displayField: 'text',
            editable: false
 		},{
 			fieldLabel : '出生日期',
 			emptyText : '请选择',
 			name : 'birthday',
 			xtype: 'datefield',
 			format : 'Y-m-d'
 		},{
 			fieldLabel : '籍贯',
 			name : 'origin'
 		},{
 			fieldLabel : '所属系',
 			name : 'dept'
 		}],
 		buttons : [{
 			id: 'buttonSave',
 			text : '添加',
 			handler : function(){
 				if(!form.getForm().isValid()){
 					return;
 				}
 				if(form.getForm().findField('id').getValue() == ''){
 					//添加
 					form.getForm().submit({
 						url : 'student?method=add',
 						success : function(f,action){
 							if(action.result.success){
 								Ext.Msg.alert('消息',action.result.msg,function(){
 									grid.getStore().reload();
 									form.getForm().reset();
 									Ext.getCmp('buttonSave').setText('修改');
 								});
 							}
 						},
 						failure : function(){
 							Ext.Msg.alert('错误','添加失败');
 						}
 					});
 				} else{
 					//修改
 					 	form.getForm().submit({
 						url : 'student?method=update',
 						success : function(f,action){
 							if(action.result.success){
 								Ext.Msg.alert('消息',action.result.msg,function(){
 									grid.getStore().reload();
 									form.getForm().reset();
 									Ext.getCmp('buttonSave').setText('修改');
 								});
 							}
 						},
 						failure : function(){
 							Ext.Msg.alert('错误','修改失败');
 						}
 					});
 				}
 			}
 		},{
 			text : '清空',
 			handler : function(){
 				form.getForm().reset();
 				Ext.getCmp('buttonSave').setText('添加');
 			}
 		},{
 			text : '删除',
 			handler : function(){
 				var id = form.getForm().findField('id').getValue();
 				if(id == ''){
 					Ext.Msg.alert('提示','请选择需要删除的信息');
 				}else{
 					Ext.Ajax.request({
 						url : 'student?method=delete',
 						success : function(response){
 							var json = Ext.decode(response.responseText);
 							if(json.success){
 								Ext.Msg.alert('消息',json.msg,function(){
 									grid.getStore().reload();
 									form.getForm().reset();
 									//form.buttons[0].setText('添加');
 									Ext.getCmp('buttonSave').setText('添加');
 								});
 							}
 						},
 						failure : function(){
 							Ext.Msg.alert('错误','删除失败');
 						},
 						params : "id=" + id
 					});
 				}
 			}
 		}]
 	});
 	
 	grid.addListener('itemclick',function(view, record){
 		form.getForm().loadRecord(record);
 		Ext.getCmp('buttonSave').setText('修改');
 	});
	
	var viewport = Ext.create('Ext.container.Viewport',{
		layout : 'border',
		items : [{
			region : 'north',
			contentEl : 'head'
		},grid,form,{
			region : 'south',
			contentEl : 'foot'
		}]
	});
});