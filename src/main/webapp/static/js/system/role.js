$(function () {
    //渲染表格
    layui.table.render({
        elem: '#table',
        url: '/servlet/role',
        where: {
            token: getToken()
        },
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'roleName', sort: true, title: i18n['roleName']},
            {field: 'comments', sort: true, title: i18n['remark']},
            {
                field: 'createdTime', sort: true, templet: function (d) {
                return layui.util.toDateString(d.createdTime*1000);
            }, title: i18n['createTime']
            },
            {field: 'status', sort: true, templet: '#statusTpl', width: 80, title: i18n['status']},
            {align: 'center', toolbar: '#barTpl', minWidth: 180, title: i18n['opt']}
        ]]
    });

    //添加按钮点击事件
    $("#addBtn").click(function () {
        showEditModel(null);
    });

    //表单提交事件
    layui.form.on('submit(btnSubmit)', function (data) {
        data.field.token = getToken();
        data.field._method = $("#editForm").attr("method");
        layer.load(2);
        $.post("/servlet/role", data.field, function (data) {
            layer.closeAll('loading');
            if (data.status == "succ") {
                layer.msg("SUCCESS", {icon: 1});
                layer.closeAll('page');
                layui.table.reload('table', {});
            } else {
                layer.msg(data.error, {icon: 2});
            }
        }, "JSON");
        return false;
    });

    //工具条点击事件
    layui.table.on('tool(table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') { //修改
            showEditModel(data);
        } else if (layEvent === 'del') { //删除
            doDelete(data.roleId);
        } else if (layEvent == 'detail') { //权限树
            showPermDialog(data.roleId);
        }
    });

    //监听状态开关操作
    layui.form.on('switch(statusCB)', function (obj) {
        updateStatus(obj);
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function () {
        doSearch();
    });
});

//显示表单弹窗
function showEditModel(data) {
    layer.open({
        type: 1,
        title: data == null ? i18n['add'] : i18n['edit'],
        area: '450px',
        offset: '120px',
        content: $("#addModel").html()
    });
    $("#editForm")[0].reset();
    $("#editForm").attr("method", "POST");
    if (data != null) {
        $("#editForm input[name=roleId]").val(data.roleId);
        $("#editForm input[name=roleName]").val(data.roleName);
        $("#editForm textarea[name=comments]").val(data.comments);
        $("#editForm").attr("method", "PUT");
    }
    $("#btnCancel").click(function () {
        layer.closeAll('page');
    });
}

//删除
function doDelete(roleId) {

    layer.confirm(i18n['confirm_delete'], function (index) {
        layer.close(index);
        var load = layer.load(2);
        $.ajax({
            url: "/servlet/role/" + roleId + "?token=" + getToken(),
            type: "DELETE",
            dataType: "JSON",
            success: function (data) {
                layer.close(load);
                if (data.status == 'succ') {
                    layui.table.reload('table', {});
                } else {
                    layer.msg(data.error, {icon: 2});
                    layui.table.reload('table', {});
                }
            }
        });
    });
}

//更改状态
function updateStatus(obj) {
    var load = layer.load(2);
    var newStatus = obj.elem.checked ? 1 : 0;
    $.post("/servlet/role/status", {
        roleId: obj.elem.value,
        status: newStatus,
        _method: "PUT",
        token: getToken()
    }, function (data) {
        layer.close(load);
        console.log(data);
        if (data.status == 'succ') {
            layui.table.reload('table', {});
        } else {
            layer.msg(data.error, {icon: 2});
            layui.table.reload('table', {});
        }
    }, "json");
}

//搜索
function doSearch() {
    var key = $("#searchKey").val();
    var value = $("#searchValue").val();
    if (value == '') {
        key = '';
    }
    layui.table.reload('table', {where: {searchKey: key, searchValue: value}});
}

//管理权限
function showPermDialog(roleId) {

    layer.open({
        type: 1,
        title: i18n['permission'],
        area: ['550px', '700px'],
        content: $("#permissionTree").html()
    });

    var load = layer.load(2);
    var setting = {
        check: {enable: true},
        data: {
            simpleData: {enable: true}
        }
    };
    $.get("/servlet/role/tree/" + roleId, {
        token: getToken()
    }, function (data) {
        layer.close(load);
        $.fn.zTree.init($("#treeAuth"), setting, data.zTree);
    }, "json");

    $("#btnPerCancel").click(function () {
        layer.closeAll('page');
    });

    //表单提交事件
    layui.form.on('submit(btnPerSubmit)', function (data) {
        saveRolePerm(roleId);
        return false;
    });
}

//保存权限
function saveRolePerm(roleId) {
    var load = layer.load(2);
    var treeObj = $.fn.zTree.getZTreeObj("treeAuth");
    var nodes = treeObj.getCheckedNodes(true);
    var ids = new Array();
    for (var i = 0; i < nodes.length; i++) {
        ids[i] = nodes[i].id;
    }
    $.post("/servlet/role/tree", {
        roleId: roleId,
        permIds: JSON.stringify(ids),
        token: getToken(),
        _method: "PUT"
    }, function (data) {
        layer.close(load);
        if (data.status = 'succ') {
            layer.msg("SUCCESS", {icon: 1}, function () {
                layer.closeAll('page');
            });
        } else {
            layer.msg(data.error, {icon: 2});
        }
    }, "json");
}