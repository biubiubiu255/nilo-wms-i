<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="content-header">
	<h2 class="content-title"><spring:message code="system.role.mgn"/></h2>
	<span class="layui-breadcrumb">
	  <a href="#!home"><spring:message code="home.page"/></a>
	  <a><cite><spring:message code="system.role.mgn"/></cite></a>
	</span>
</div>
<div>
	<div class="layui-form toolbar">
		搜索：
		<select id="searchKey">
			<option value="">-<spring:message code="please.select"/>-</option>
			<option value="role_id">ID</option>
			<option value="role_name"><spring:message code="system.role.name"/></option>
			<option value="comments"><spring:message code="remark"/></option>
		</select>&emsp;
		<input id="searchValue" class="layui-input search-input" type="text" placeholder="<spring:message code="input.search.content"/>" />&emsp;
		<button id="searchBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe615;</i><spring:message code="search"/></button>&emsp;
		<button id="addBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe654;</i><spring:message code="add"/></button>
	</div>
	<!-- 数据表格 -->
	<table class="layui-table" id="table" lay-filter="table"></table>
</div>

<!-- 表单弹窗 -->
<script type="text/html" id="addModel">
	<form id="editForm" class="layui-form model-form" action="">
		<input name="roleId" type="hidden" />
		<div class="layui-form-item">
			<label class="layui-form-label"><spring:message code="system.role.name"/></label>
 			<div class="layui-input-block">
				<input name="roleName" placeholder="请输入角色名" type="text" class="layui-input" maxlength="100" lay-verify="required" required />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"><spring:message code="remark"/></label>
 			<div class="layui-input-block">
				<textarea name="comments" placeholder="请输入内容" class="layui-textarea" maxlength="200"></textarea>
			</div>
		</div>
		<div class="layui-form-item model-form-footer">
			<button class="layui-btn layui-btn-primary" type="button" id="btnCancel"><spring:message code="cancel"/></button>
			<button class="layui-btn" lay-filter="btnSubmit" lay-submit><spring:message code="ok"/></button>
		</div>
	</form>
</script>
<!-- 表格操作列 -->
<script type="text/html" id="barTpl">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit"><spring:message code="edit"/></a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><spring:message code="delete"/></a>
	<a class="layui-btn layui-btn-xs" lay-event="detail">管理权限</a>
</script>
<!-- 表格状态列 -->
<script type="text/html" id="statusTpl">
	<input type="checkbox" value="{{d.roleId}}" lay-filter="statusCB" lay-skin="switch" lay-text="正常|停用" {{ d.isDelete == 0 ? 'checked' : '' }}>
</script>
<link rel="stylesheet" href="/assets/plugins/zTree/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="/assets/plugins/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="/assets/js/system/role.js"></script>
