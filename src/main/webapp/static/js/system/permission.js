$(function () {
    //渲染表格
    layui.table.render({
        elem: '#table',
        url: '/servlet/permission',
        where: {
            token: getToken()
        },
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'permissionId', sort: true, title: 'ID'},
            {field: 'parentName', sort: true, title: '父级'},
            {field: 'desc', sort: true, title: '名称'},
            {field: 'value', sort: true, title: 'URL'},
            {field: 'typeDesc', sort: true, title: '类型'},
            {
                field: 'createdTime', sort: true, templet: function (d) {
                return layui.util.toDateString(d.createdTime);
            }, title: '创建时间'
            },
            {field: 'status', sort: true, templet: '#statusTpl', width: 80, title: '状态'},
            {align: 'center', toolbar: '#barTpl', minWidth: 110, title: '操作'}
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
        layer.load(1);
        $.post("/servlet/permission", data.field, function (data) {
            layer.closeAll('loading');
            if (data.status == "succ") {
                layer.msg("SUCCESS", {icon: 1});
                layer.closeAll('page');
                layui.table.reload('table', {});
                parents1 = null;
                parents2 = null;
            } else {
                layer.msg(data.msg, {icon: 2});
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
            doDelete(obj);
        }
    });

    //监听状态开关操作
    layui.form.on('switch(statusCB)', function (obj) {
        updateStatus(obj);
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function () {
        doSearch(table);
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
    var selectItem = "";
    var type = 0;
    if (data != null) {
        $("#editForm input[name=permissionId]").val(data.permissionId);
        $("#editForm input[name=desc_e]").val(data.desc_e);
        $("#editForm input[name=desc_c]").val(data.desc_c);
        $("#editForm input[name=permissionName]").val(data.desc);
        $("#editForm input[name=value]").val(data.value);
        $("#editForm input[name=orderNumber]").val(data.orderNumber);
        $("#editForm").attr("method", "PUT");
        selectItem = data.parentId;
        type = data.type;
        $("select[name='type']").val(type);
        layui.form.render('select');
    }
    $("#btnCancel").click(function () {
        layer.closeAll('page');
    });

    getParents(selectItem, type);

    layui.form.on('select(permissionType)', function (data) {
        getParents(selectItem, data.value);
    });
}

//获取所有父级菜单
var parents1 = null;
var parents2 = null;
function getParents(selectItem, type) {
    var parents;
    if (type == 0) {
        parents = new Array();
    }
    if (type == 1) {
        parents = parents1;
    }
    if (type == 2) {
        parents = parents2;
    }
    if (parents != null) {
        $("#parent-select").empty();
        $("#parent-select").prepend("<option value=''>" + i18n['pls_select'] + "</option>");
        for (var i = 0; i < parents.length; i++) {
            $("#parent-select").append("<option value='" + parents[i].permissionId + "'>" + parents[i].desc + "</option>");
        }
        $("#parent-select").val(selectItem);
        layui.form.render('select');
    } else {
        $.get("/servlet/permission/parent/" + type, {
            token: getToken()
        }, function (data) {
            if (type == 1) {
                parents1 = data.data;
            } else if (type == 2) {
                parents2 = data.data;
            }
            getParents(selectItem, type);
        }, "JSON");
    }
}

//删除
function doDelete(obj) {
    layer.confirm(i18n['confirm_delete'], function (index) {
        layer.close(index);
        layer.load(1);
        $.ajax({
            url: "/servlet/permission/" + obj.data.permissionId + "?token=" + getToken(),
            type: "DELETE",
            dataType: "JSON",
            success: function (data) {
                layer.closeAll('loading');
                if (data.status = 'succ') {
                    layer.msg("SUCCESS", {icon: 1});
                    obj.del();
                    parents1 = null;
                    parents2 = null;
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            }
        });
    });
}

//更改状态
function updateStatus(obj) {
    layer.load(1);
    var newStatus = obj.elem.checked ? 0 : 1;
    $.post("/servlet/permission/status", {
        permissionId: obj.elem.value,
        status: newStatus,
        _method: "PUT",
        token: getToken()
    }, function (data) {
        layer.closeAll('loading');
        if (data.status = 'succ') {
            layui.table.reload('table', {});
        } else {
            layer.msg(data.msg, {icon: 2});
            layui.table.reload('table', {});
        }
    });
}

//搜索
function doSearch(table) {
    var key = $("#searchKey").val();
    var value = $("#searchValue").val();
    if (value == '') {
        key = '';
    }
    layui.table.reload('table', {where: {searchKey: key, searchValue: value}});
}