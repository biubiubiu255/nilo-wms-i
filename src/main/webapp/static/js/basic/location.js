
$(function () {

    refreshPermission();
    refreshI18n();
    //渲染表格
    layui.table.render({
        elem: '#table',
        url: '/servlet/location',
        where: {
            token: getToken()
        },
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'locationID', width: 125, sort: true, title: getI18nAttr('basic_location_id')},
            {field: 'warehouseID', width: 190, sort: true, title: getI18nAttr('basic_of_warehouse')},
            {field: 'putawayZone', sort: true, title: getI18nAttr('putaway_zone')},
            {field: 'pickZone', sort: true, title: getI18nAttr('pick_zone')},
            {field: 'putawaySequence', sort: true, title: getI18nAttr('putaway_sequence')},
            {field: 'pickSequence', sort: true, title: getI18nAttr('pick_sequence')},
            {field: 'areMixGoods', sort: true, templet: '#radio-areMixGoods', width: 125, title: getI18nAttr('are_mix_goods')},
            {field: 'areMixLot', sort: true, templet: '#radio-areMixLot', width: 125, title: getI18nAttr('are_mix_lot')},
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
        data.field._method = $("#editForm").attr("method");
        layer.load(2);
        console.log(data.field);
        //return false;
        $.post("/servlet/location", data.field, function (data) {
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
    layui.form.on('switch', function (obj) {
        updateStatus(obj);
    });

    //搜索按钮点击事件
    $("#searchBtn").click(function () {
        doSearch(table);
    });
});

//显示Add、Edit Page
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
    $("#editForm").attr("method", "POST");
    var selectItem = "";
    if (data != null) {
        $("#editForm input[name=id]").val(data.id);
        $("#editForm input[name=locationID]").val(data.locationID);
        $("#editForm input[name=pickSequence]").val(data.pickSequence);
        $("#editForm input[name=putawaySequence]").val(data.putawaySequence);
        $("#editForm").attr("method", "PUT");
        if (data.areMixGoods) {
            $("input[name='areMixGoods'][value='1']").attr("checked",true);
        } else {
            $("input[name='areMixGoods'][value='0']").attr("checked",true);
        }
        if (data.areMixLot) {
            $("input[name='areMixLot'][value='1']").attr("checked",true);
        } else {
            $("input[name='areMixLot'][value='0']").attr("checked",true);
        }
        layui.form.render('radio');
    }

    $("#btnCancel").click(function () {
        layer.closeAll('page');
    });

    drawSelectZone(data.putawayZone, data.pickZone);
}

//获取库位区域下拉框
var zone = null;
function drawSelectZone(putawayZoneItem, pickZoneItem) {
    if (zone != null) {
        $("#select-pick-zone").empty();
        $("#select-putaway-zone").empty();
        $("#select-pick-zone").prepend("<option value=''>" + getI18nAttr('pls_select') + "</option>");
        $("#select-putaway-zone").prepend("<option value=''>" + getI18nAttr('pls_select') + "</option>");
        for (var i = 0; i < zone.length; i++) {
            $("#select-pick-zone").append("<option value='" + zone[i].code + "'>" + zone[i].desc + "</option>");
            $("#select-putaway-zone").append("<option value='" + zone[i].code + "'>" + zone[i].desc + "</option>");
        }
        $("#select-pick-zone").val(pickZoneItem);
        $("#select-putaway-zone").val(putawayZoneItem);
        layui.form.render('select');
    } else {
        var load = layer.load(2);
        $.get("/servlet/dictionary", {
            token: getToken(), type: 'location_zone'
        }, function (data) {
            zone = data.response;
            drawSelectZone(putawayZoneItem, pickZoneItem);
            layer.close(load);
        }, "JSON");
    }
}

//删除按钮
function doDelete(obj) {
    layer.confirm(getI18nAttr('confirm_delete'), function (index) {
        layer.close(index);
        layer.load(2);
        $.ajax({
            url: "/servlet/location/" + obj.data.id + "?token=" + getToken(),
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

//更改状态单选框
function updateStatus(obj) {
    var load = layer.load(2);
    var status = obj.elem.checked;
    var areMixGoodsStatus,areMixLotStatus;
    obj.elem.attributes['lay-filter'].value=='areMixGoods' ?  areMixGoodsStatus=status : areMixLotStatus=status;
    //console.log(obj);
    updateField = obj.elem.attributes['lay-filter'].value;
    $.post("/servlet/location", {
        id: obj.value,
        _method: "PUT",
        areMixGoods: areMixGoodsStatus,
        areMixLot: areMixLotStatus,
        token: getToken()
    }, function (data) {
        console.log(data);
        layer.close(load);
        if (data.status == 'succ') {
            layui.table.reload('table', {});
        } else {
            layer.msg(data.error, {icon: 2, time: 2000}, function () {
                layui.table.reload('table', {});
            });
        }
    }, "json");
}

//搜索
function doSearch(table) {
    var key = $("#searchKey").val();
    var value = $("#searchValue").val();
    if (value == '') {
        key = '';
    }
    layui.table.reload('table', {where: {locationID: value}});
}

//重置密码
function doReSet(userId) {
    layer.confirm(getI18nAttr('confirm_reset_pwd'), function (index) {
        layer.close(index);
        layer.load(2);
        $.post("/servlet/user/psw/" + userId, {
            token: getToken(),
            _method: "PUT"
        }, function (data) {
            layer.closeAll('loading');
            if (data.status == 'succ') {
                layer.msg("SUCCESS", {icon: 1});
            } else {
                layer.msg(data.error, {icon: 2});
            }
        }, "JSON");
    });
}