	$(function() {
	//渲染表格
	layui.table.render({
		elem : '#table',
		url : '/servlet/role',
 		where: {
	  		token : getToken()
		},
		page: true,
		cols: [[
			{type:'numbers'},
			{field:'roleName', sort: true, title: i18n['roleName']},
			{field:'comments', sort: true, title: i18n['remark']},
			{field:'createTime', sort: true, templet:function(d){ return layui.util.toDateString(d.createTime); }, title: i18n['createTime']},
			{field:'isDelete', sort: true, templet: '#statusTpl',width: 80, title: i18n['status']},
			{align:'center', toolbar: '#barTpl', minWidth: 180, title: i18n['opt']}
    	]]
	});

	//添加按钮点击事件
	$("#addBtn").click(function(){
		showEditModel(null);
	});
	
	//表单提交事件
	layui.form.on('submit(btnSubmit)', function(data){
		data.field.token = getToken();
		data.field._method = $("#editForm").attr("method");
		layer.load(2);
		$.post("/servlet/role", data.field, function(data){
			layer.closeAll('loading');
			if(data.code==200){
				layer.msg(data.msg,{icon: 1});
				layer.closeAll('page');
				layui.table.reload('table', {});
			}else{
				layer.msg(data.msg,{icon: 2});
			}
		}, "JSON");
		return false;
	});
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'edit'){ //修改
			showEditModel(data);
		} else if(layEvent === 'del'){ //删除
			doDelete(obj);
		} else if(layEvent == 'detail'){
			showPermDialog(data.roleId);
		}
	});
	
	//监听状态开关操作
	layui.form.on('switch(statusCB)', function(obj){
		updateStatus(obj);
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch();
	});
});

//显示表单弹窗
function showEditModel(data){
	layer.open({
		type: 1,
		title: data==null?"添加角色":"修改角色",
		area: '450px',
		offset: '120px',
		content: $("#addModel").html()
	});
	$("#editForm")[0].reset();
	$("#editForm").attr("method","POST");
	if(data!=null){
		$("#editForm input[name=roleId]").val(data.roleId);
		$("#editForm input[name=roleName]").val(data.roleName);
		$("#editForm textarea[name=comments]").val(data.comments);
		$("#editForm").attr("method","PUT");
	}
	$("#btnCancel").click(function(){
		layer.closeAll('page');
	});
}

//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		layer.close(index);
		layer.load(2);
		$.ajax({
			url: "/servlet/role/"+obj.data.roleId+"?token="+getToken(),
			type: "DELETE", 
			dataType: "JSON", 
			success: function(data){
				layer.close(load);
				if(data.status=='succ'){
					layui.table.reload('table', {});
				}else{
					layer.msg(data.error,{icon: 2});
					layui.table.reload('table', {});
				}
			}
		});
	});
}

//更改状态
function updateStatus(obj){
	var load = layer.load(2);
	var newStatus = obj.elem.checked?1:0;
	$.post("/servlet/role/status", {
		roleId: obj.elem.value,
		status: newStatus,
		_method: "PUT",
		token: getToken()
	}, function(data){
		layer.close(load);
		console.log(data);
		if(data.status=='succ'){
			layui.table.reload('table', {});
		}else{
			layer.msg(data.error,{icon: 2});
			layui.table.reload('table', {});
		}
	}, "json");
}

//搜索
function doSearch(){
	var key = $("#searchKey").val();
	var value = $("#searchValue").val();
	if (value=='') {
		key = '';
	}
	layui.table.reload('table', {where: {searchKey: key,searchValue: value}});
}

//管理权限
function showPermDialog(roleId){
	layer.open({
		type: 1,
		title: '管理权限',
		area: '450px',
		offset: '120px',
		content: '<ul id="treeAuth" class="ztree" style="padding: 25px 80px;"></ul>',
		btn: ['保存','关闭'],
		btnAlign: 'c', 
		yes: function(index){
			saveRolePerm(roleId,index);
		}
	});
	layer.load(2);
	var setting = {
		check: {enable: true},
	    data: {
	    	simpleData:{enable: true}
	    }
	};
	$.get("api/permission/tree/"+roleId,{
		token: getToken()
	},function(data) {
    	$.fn.zTree.init($("#treeAuth"), setting, data);  
		layer.closeAll('loading');
	},"json");
}

//保存权限
function saveRolePerm(roleId,index){
	layer.load(2);
	var treeObj = $.fn.zTree.getZTreeObj("treeAuth");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = new Array();
	for(var i=0;i<nodes.length;i++){
		ids[i] = nodes[i].id;
	}
	$.post("api/permission/tree",{
		roleId: roleId,
		permIds: JSON.stringify(ids),
		token: getToken(),
		_method: "PUT"
	},function(data){
		layer.closeAll('loading');
		if(200==data.code){
			layer.msg(data.msg, {icon: 1});
			layer.close(index);
		}else{
			layer.msg(data.msg, {icon: 2});
		}
	},"json");
}