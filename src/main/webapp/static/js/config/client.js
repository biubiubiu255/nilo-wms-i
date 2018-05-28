
$(function () {

    refreshPermission();
    refreshI18n();
    //渲染表格
    layui.table.render({
        elem: '#table',
        url: '/servlet/client',
        where: {
            token: getToken()
        },
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'clientCode', sort: true, title: getI18nAttr('config_client_code')},
            {field: 'clientName', sort: true, title: getI18nAttr('config_client_name')},
            {field: 'clientKey', sort: true, title: getI18nAttr('config_client_key')},
            {field: 'wmsKey', sort: true, title: getI18nAttr('config_client_wms_key')},
            {field: 'warehouseCode', sort: true, title: getI18nAttr('config_client_warehouse_code')},
            {field: 'customerCode', sort: true, title: getI18nAttr('config_client_customer_code')},
            {
                field: 'createdTime', sort: true, templet: function (d) {
                return layui.util.toDateString(d.createdTime * 1000);
            }, title: getI18nAttr('create_time')
            },
            {align: 'center', toolbar: '#barTpl', minWidth: 180, title: getI18nAttr('opt')}
        ]]
    });



    //添加按钮点击事件
    $("#addBtn").click(function () {
        showEditModel(null);
    });

    //表单提交事件
    layui.form.on('submit(btnSubmit)', function (data) {
        data.field.token = getToken();
        layer.load(2);
        $.post("/servlet/client", data.field, function (data) {
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
        title: data == null ? getI18nAttr('add') : getI18nAttr('edit'),
        area: '450px',
        offset: '120px',
        content: $("#addModel").html(),
        success: function(layero, index){
            refreshI18n(layero);
        }
    });
    $("#editForm")[0].reset();
    if (data != null) {
        $("#editForm input[name=clientCode]").val(data.clientCode);
        $("#editForm input[name=clientName]").val(data.clientName);
        $("#editForm input[name=wmsKey]").val(data.wmsKey);
        $("#editForm input[name=clientKey]").val(data.clientKey);
        $("#editForm input[name=customerCode]").val(data.customerCode);
        $("#editForm input[name=warehouseCode]").val(data.warehouseCode);
        $("#editForm input[name=clientKey]").val(data.clientKey);
        $("#editForm input[name=_method]").val("PUT");
        $("#editForm input[name=clientCode]").attr("disabled", true);
    }
    $("#btnCancel").click(function () {
        layer.closeAll('page');
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
