<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="/static/plugins/zTree/css/zTreeStyle/zTreeStyle.css">

<div class="content-header">
    <h2 class="content-title"><spring:message code="system.role.mgn"/></h2>
	<span class="layui-breadcrumb">
	  <a href="#!home"><spring:message code="home.page"/></a>
	  <a><cite><spring:message code="system.role.mgn"/></cite></a>
	</span>
</div>
<div>
    <div class="layui-form toolbar">
        <spring:message code="search"/>:
        <select id="searchKey">
            <option value="">-<spring:message code="please.select"/>-</option>
            <option value="roleName"><spring:message code="system.role.name"/></option>
        </select>&emsp;
        <input id="searchValue" class="layui-input search-input" type="text"/>&emsp;
        <button id="searchBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe615;</i><spring:message
                code="search"/></button>&emsp;
        <button id="addBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe654;</i><spring:message code="add"/>
        </button>
    </div>
    <!-- 数据表格 -->
    <table class="layui-table" id="table" lay-filter="table"></table>
</div>

<!-- 表单弹窗 -->
<script type="text/html" id="addModel">
    <form id="editForm" class="layui-form model-form" action="">
        <input name="roleId" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="system.role.name"/></label>
            <div class="layui-input-block">
                <input name="roleName" placeholder="" type="text" class="layui-input" maxlength="100"
                       lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><spring:message code="remark"/></label>
            <div class="layui-input-block">
                <textarea name="comments" placeholder="" class="layui-textarea" maxlength="200"></textarea>
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
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit"><spring:message code="edit"/></a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><spring:message code="delete"/></a>
    <a class="layui-btn layui-btn-xs" lay-event="detail"><spring:message code="system.permission.mgn"/></a>
</script>
<!-- 表格状态列 -->
<script type="text/html" id="statusTpl">
    <input type="checkbox" value="{{d.roleId}}" lay-filter="statusCB" lay-skin="switch"
           lay-text="<spring:message code='on'/>|<spring:message code='off'/>" {{ d.status== 1?'checked' : '' }}>
</script>

<script type="text/javascript" src="/static/js/system/role.js"></script>
<script type="text/javascript" src="/static/plugins/zTree/js/jquery.ztree.core-3.5.min.js" />
<script type="text/javascript" src="/static/plugins/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script>
    var i18n = new Object();
    i18n['roleName'] = '<spring:message code="system.role.name"/>';
    i18n['remark'] = '<spring:message code="remark"/>';
    i18n['status'] = '<spring:message code="status"/>';
    i18n['createTime'] = '<spring:message code="create.time"/>';
    i18n['opt'] = '<spring:message code="opt"/>';
    i18n['add'] = '<spring:message code="add"/>';
    i18n['edit'] = '<spring:message code="edit"/>';

</script>
