<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="content-header">
    <h2 class="content-title"><spring:message code="system.permission.mgn"/></h2>
	<span class="layui-breadcrumb">
	  <a href="#!home"><spring:message code="home.page"/></a>
	  <a><cite><spring:message code="system.permission.mgn"/></cite></a>
	</span>
</div>

<div>
    <div class="layui-form toolbar">
        <spring:message code="search"/>：
        <select id="searchKey">
            <option value="">-<spring:message code="please.select"/>-</option>
            <option value="desc"><spring:message code="system.permission.name"/></option>
        </select>&emsp;
        <input id="searchValue" class="layui-input search-input" type="text"
               placeholder="<spring:message code="input.search.content"/>"/>&emsp;
        <button id="searchBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe615;</i><spring:message
                code="search"/></button>&emsp;
        <button id="addBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe654;</i><spring:message code="add"/>
        </button>
        </button>
    </div>

    <table class="layui-table" id="table" lay-filter="table"></table>
</div>

<!-- 表单弹窗 -->
<script type="text/html" id="addModel">
    <form id="editForm" class="layui-form model-form" action="">
        <input name="permissionId" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label">父级</label>
            <div class="layui-input-block">
                <select id="parent-select" name="parentId" lay-verify="required">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input name="permissionName" placeholder="请输入名称" type="text" class="layui-input" maxlength="20"
                       lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限值</label>
            <div class="layui-input-block">
                <input name="permissionValue" placeholder="请输入权限值" type="text" class="layui-input" maxlength="20"
                       lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序号</label>
            <div class="layui-input-block">
                <input name="orderNumber" placeholder="请输入排序号" type="number" class="layui-input" lay-verify="number"
                       required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">类型</label>
            <div class="layui-input-block">
                <input type="radio" name="permissionType" id="type0" lay-filter="permissionType" value="0" title="菜单"
                       checked/>
                <input type="radio" name="permissionType" id="type1" lay-filter="permissionType" value="1" title="按钮"/>
            </div>
        </div>
        <div class="layui-form-item model-form-footer">
            <button class="layui-btn layui-btn-primary" type="button" id="btnCancel">取消</button>
            <button class="layui-btn" lay-filter="btnSubmit" lay-submit>保存</button>
        </div>
    </form>
</script>
<!-- 表格操作列 -->
<script type="text/html" id="barTpl">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<!-- 表格状态列 -->
<script type="text/html" id="statusTpl">
    <input type="checkbox" value="{{d.permissionId}}" lay-filter="statusCB" lay-skin="switch"
           lay-text="<spring:message code='on'/>|<spring:message code='off'/>" {{ d.status==
           1?'checked' : '' }}></script>
<script type="text/javascript" src="/static/js/system/permission.js"></script>
<script>

    var i18n = new Object();
    i18n['username'] = '<spring:message code="system.user.username"/>';
    i18n['nickname'] = '<spring:message code="system.user.nickname"/>';
    i18n['phone'] = '<spring:message code="system.user.phone"/>';
    i18n['sex'] = '<spring:message code="sex"/>';
    i18n['roleName'] = '<spring:message code="system.role.name"/>';
    i18n['createTime'] = '<spring:message code="create.time"/>';
    i18n['status'] = '<spring:message code="status"/>';
    i18n['opt'] = '<spring:message code="opt"/>';

</script>