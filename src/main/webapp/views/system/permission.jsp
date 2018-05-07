<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="p" uri="/permission.tag" %>

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
            <option value="permissionId"><spring:message code="system.permission.id"/></option>
            <option value="desc"><spring:message code="system.permission.name"/></option>
        </select>&emsp;
        <input id="searchValue" class="layui-input search-input" type="text"
               placeholder="<spring:message code="input.search.content"/>"/>&emsp;
        <p:hasPermission name="10031">

            <button id="searchBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe615;</i><spring:message
                    code="search"/></button>
            &emsp;
        </p:hasPermission>
        <p:hasPermission name="10032">
            <button id="addBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe654;</i><spring:message
                    code="add"/>
            </button>
        </p:hasPermission>
    </div>

    <table class="layui-table" id="table" lay-filter="table"></table>
</div>

<!-- 表单弹窗 -->
<script type="text/html" id="addModel">
    <form id="editForm" class="layui-form model-form" action="">
        <input name="permissionId" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="type"/></label>
            <div class="layui-input-block">
                <select lay-filter="permissionType" name="type">
                    <option value="">-<spring:message code="please.select"/>-</option>
                    <option value="0"><spring:message code="navigation"/></option>
                    <option value="1"><spring:message code="menu"/></option>
                    <option value="2"><spring:message code="button"/></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="parent"/></label>
            <div class="layui-input-block">
                <select id="parent-select" name="parentId" lay-verify="required">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="system.permission.name_c"/></label>
            <div class="layui-input-block">
                <input name="desc_c" type="text" class="layui-input" maxlength="20"
                       lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="system.permission.name_e"/></label>
            <div class="layui-input-block">
                <input name="desc_e" type="text" class="layui-input" maxlength="20"
                       lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="system.permission.id"/></label>
            <div class="layui-input-block">
                <input name="permissionId" type="text" class="layui-input" maxlength="20"
                       lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">URL</label>
            <div class="layui-input-block">
                <input name="value" type="text" class="layui-input" maxlength="20"
                />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="system.permission.order"/></label>
            <div class="layui-input-block">
                <input name="orderNumber" type="number" class="layui-input" lay-verify="number"
                       required/>
            </div>
        </div>

        <div class="layui-form-item model-form-footer">
            <button class="layui-btn layui-btn-primary" type="button" id="btnCancel"><spring:message
                    code="cancel"/></button>
            <button class="layui-btn" lay-filter="btnSubmit" lay-submit><spring:message code="ok"/></button>
        </div>
    </form>
</script>
<!-- 表格操作列 -->
<script type="text/html" id="barTpl">
    <p:hasPermission name="10033">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit"><spring:message code="edit"/></a>
    </p:hasPermission>
    <p:hasPermission name="10034">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><spring:message code="delete"/></a>
    </p:hasPermission>
</script>
<!-- 表格状态列 -->
<script type="text/html" id="statusTpl">
    <input type="checkbox" value="{{d.permissionId}}" lay-filter="statusCB" lay-skin="switch"
           lay-text="<spring:message code='on'/>|<spring:message code='off'/>" {{ d.status==
           1?'checked' : '' }}></script>
<script type="text/javascript" src="/static/js/system/permission.js"></script>
<script>

    var i18n = new Object();
    i18n['createTime'] = '<spring:message code="create.time"/>';
    i18n['status'] = '<spring:message code="status"/>';
    i18n['opt'] = '<spring:message code="opt"/>';
    i18n['pls_select'] = '<spring:message code="please.select"/>';
    i18n['confirm_delete'] = '<spring:message code="confirm.delete"/>';
    i18n['add'] = '<spring:message code="add"/>';
    i18n['edit'] = '<spring:message code="edit"/>';
    i18n['parent'] = '<spring:message code="parent"/>';
    i18n['permission_id'] = '<spring:message code="system.permission.id"/>';
    i18n['permission_name'] = '<spring:message code="system.permission.name"/>';
    i18n['type'] = '<spring:message code="type"/>';

</script>