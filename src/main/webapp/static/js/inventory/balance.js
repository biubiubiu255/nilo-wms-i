
$(function () {

    refreshPermission();
    refreshI18n();
    //渲染表格
    layui.table.render({
        elem: '#table',
        url: '/servlet/inventory/balance',
        where: {
            token: getToken()
        },
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'store_id', sort: true, title: getI18nAttr('basic_supplier')},
            {field: 'sku', sort: true, title: getI18nAttr('basic_sku')},
            {field: 'storage', sort: true, title: getI18nAttr('inventory_storage')},
            {field: 'cache_storage', sort: true,  title: getI18nAttr('inventory_cache_storage')},
            {field: 'lock_storage', sort: true, title: getI18nAttr('inventory_lock_storage')},
            {field: 'safe_storage', sort: true, title: getI18nAttr('inventory_safe_storage')},
            {align: 'center', toolbar: '#barTpl', minWidth: 180, title: getI18nAttr('opt')}
        ]],
        done: function(res, curr, count){
            refreshI18n();
        }
    });


    //表单提交事件
    layui.form.on('submit(btnSubmit)', function (data) {
        data.field.token = getToken();
        layer.load(2);
        $.post("/servlet/inventory/balance", data.field, function (data) {
            layer.closeAll('loading');
            if ("succ" == data.status) {
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
        }
        if (layEvent === 'sync') { //同步
            syncInventory(data);
        }
    });

    function syncInventory(obj) {
        layer.confirm("Confirm Sync", function (index) {
            layer.close(index);
            layer.load(2);
            $.ajax({
                url: "/servlet/inventory/balance/sync/" + obj.sku + "?token=" + getToken(),
                type: "POST",
                dataType: "JSON",
                success: function (data) {
                    layer.closeAll('loading');
                    if ("succ" == data.status) {
                        layer.msg("SUCCESS", {icon: 1});
                    } else {
                        layer.msg(data.error, {icon: 2});
                    }
                }
            });
        });
    }


    //搜索按钮点击事件
    $("#searchBtn").click(function () {
        doSearch(table);
    });

    //搜索按钮点击事件
    $("#syncAllBtn").click(function () {
        syncAll();
    });
});

//显示表单弹窗
function showEditModel(data) {
    layer.open({
        type: 1,
        title: getI18nAttr('edit'),
        area: '450px',
        offset: '120px',
        content: $("#addModel").html(),
        success: function(layero, index){
            refreshI18n(layero);
        }
    });

    $("#editForm")[0].reset();
    if (data != null) {
        $("#editForm input[name=store_id]").val(data.store_id);
        $("#editForm input[name=sku]").val(data.sku);
        $("#editForm input[name=storage]").val(data.storage);
        $("#editForm input[name=cache_storage]").val(data.cache_storage);
        $("#editForm input[name=lock_storage]").val(data.lock_storage);
        $("#editForm input[name=safe_storage]").val(data.safe_storage);
    }
    $("#btnCancel").click(function () {
        layer.closeAll('page');
    });
}

function doSearch(table) {
    var key = $("#searchKey").val();
    var value = $("#searchValue").val();
    if (value == '') {
        key = '';
    }
    layui.table.reload('table', {where: {searchKey: key, searchValue: value}});
}

function syncAll() {
    layer.confirm("Confirm Sync", function (index) {
        layer.close(index);
        layer.load(2);
        $.ajax({
            url: "/servlet/inventory/balance/sync/all?token=" + getToken(),
            type: "POST",
            dataType: "JSON",
            success: function (data) {
                layer.closeAll('loading');
                if ("succ" == data.status) {
                    layer.msg("SUCCESS", {icon: 1});
                } else {
                    layer.msg(data.error, {icon: 2});
                }
            }
        });
    });
}
