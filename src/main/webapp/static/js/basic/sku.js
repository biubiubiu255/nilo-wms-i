$(function () {

    refreshPermission();
    refreshI18n();
    //渲染表格
    layui.table.render({
        elem: '#table',
        url: '/servlet/sku',
        where: {
            token: getToken()
        },
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'customerId', sort: true, title: getI18nAttr('basic_customer')},
            {field: 'sku', sort: true, title: getI18nAttr('basic_sku')},
            {field: 'desc_e', sort: true, title: getI18nAttr('basic_sku_desc_e')},
            {field: 'price', sort: true, title: getI18nAttr('price')},
            {field: 'weight', sort: true, title: getI18nAttr('weight')},
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
        $.post("/servlet/sku", data.field, function (data) {
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
        } else if (layEvent === 'del') { //删除
            doDelete(obj);
        } else if (layEvent === 'reset') { //重置密码
            doReSet(obj.data.userId);
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

//显示修改、ADD弹窗
function showEditModel(data) {
    layer.open({
        type: 1,
        title: data == null ? getI18nAttr('add') : getI18nAttr('edit'),
        area: ['1200px', '650px'],
        offset: ['100px', '250px'],
        content: $("#addModel").html(),
        success: function (layero, index) {
            refreshI18n(layero);
            init_upload();
        }
    });

    $("#editForm")[0].reset();
    if (data != null) {
        $("#editForm select[name='customerId']").val(data.customerId);
        $("#editForm input[name='sku']").val(data.sku);
        $("#editForm input[name='desc_c']").val(data.desc_c);
        $("#editForm input[name='desc_e']").val(data.desc_e);
        $("#editForm select[name='type']").val(data.type);
        $("#editForm input[name='_method']").val('PUT');

        $("#editForm input[name='price']").val(data.price);
        $("#editForm input[name='weight']").val(data.weight);
        $("#editForm input[name='length']").val(data.length);
        $("#editForm input[name='width']").val(data.width);
        $("#editForm input[name='height']").val(data.height);

        $("#editForm select[name='battery']").val(data.battery);
        if (data.image) {
            $("#sku_image_show").attr("src", data.image);
        }
        layui.form.render('select');
    }
    $("#btnCancel").click(function () {
        layer.closeAll('page');
    });
}

function init_upload() {
    layui.use('upload', function () {
        var upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#sku_upload_image'
            , url: '/sku/upload/'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#sku_image_show').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
            }
        });
    });
}

//删除
function doDelete(obj) {
    layer.confirm(getI18nAttr('confirm_delete'), function (index) {
        layer.close(index);
        layer.load(2);
        $.ajax({
            url: "/servlet/sku/" + obj.data.sku + "?token=" + getToken(),
            type: "DELETE",
            dataType: "JSON",
            success: function (data) {
                layer.closeAll('loading');
                if ("succ" == data.status) {
                    layer.msg("SUCCESS", {icon: 1});
                    obj.del();
                } else {
                    layer.msg(data.error, {icon: 2});
                }
            }
        });
    });
}

//搜索
function doSearch(table) {
    var value = $("#searchValue").val();
    layui.table.reload('table', {where: {name: value}});
}
