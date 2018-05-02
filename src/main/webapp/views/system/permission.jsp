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
			<option value="user_id">ID</option>
			<option value="user_account"><spring:message code="system.user.username"/></option>
			<option value="user_nickname"><spring:message code="system.user.nickname"/></option>
			<option value="mobile_phone"><spring:message code="system.user.phone"/></option>
		</select>&emsp;
		<input id="searchValue" class="layui-input search-input" type="text" placeholder="<spring:message code="input.search.content"/>" />&emsp;
		<button id="searchBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe615;</i><spring:message code="search"/></button>&emsp;
		<button id="addBtn" class="layui-btn search-btn"><i class="layui-icon">&#xe654;</i><spring:message code="add"/></button></button>
	</div>
	
	<table class="layui-table" id="table" lay-filter="table"></table>
</div>

<!-- 表单弹窗 -->
<script type="text/html" id="addModel">
	<form id="editForm" class="layui-form model-form" action="">
		<input name="userId" type="hidden" />
		<div class="layui-form-item">
			<label class="layui-form-label"><spring:message code="system.user.username"/></label>
 			<div class="layui-input-block">
				<input name="userAccount" placeholder="<spring:message code="please.input"/> <spring:message code="system.user.username"/>" type="text" class="layui-input" maxlength="20" lay-verify="required" required />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"><spring:message code="system.user.nickname"/></label>
 			<div class="layui-input-block">
				<input name="userNickname" placeholder="<spring:message code="please.input"/> <spring:message code="system.user.nickname"/>" type="text" class="layui-input" maxlength="20" lay-verify="required" required />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"><spring:message code="system.user.phone"/></label>
 			<div class="layui-input-block">
				<input name="mobilePhone" placeholder="<spring:message code="please.input"/> <spring:message code="system.user.phone"/>" type="text" class="layui-input" lay-verify="required|phone" required />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"><spring:message code="sex"/></label>
 			<div class="layui-input-block">
				<input type="radio" name="sex" id="sexMan" value="1" title='<spring:message code="male"/>' checked />
				<input type="radio" name="sex" id="sexWoman" value="2" title='<spring:message code="female"/>' />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色</label>
 			<div class="layui-input-block">
				<select id="role-select" name="roleId" lay-verify="required">
				</select>   
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
  <a class="layui-btn layui-btn-xs" lay-event="reset">重置密码</a>
</script>
<!-- 表格状态列 -->
<script type="text/html" id="statusTpl">
	<input type="checkbox" name="sex" lay-filter="statusCB" value="{{d.userId}}" lay-skin="switch" lay-text="正常|锁定" {{ d.userStatus == 0 ? 'checked' : '' }}>
</script>
<!-- 角色select模板 -->
<script id="rolesSelect" type="text/html">
<option value="">-<spring:message code="please.select"/>-</option>
{{#  layui.each(d, function(index, item){ }}
<option value="{{ item.roleId }}">{{ item.roleName }}</option>
{{#  }); }}
</script>
<script type="text/javascript" src="/static/js/system/user.js"></script>
<script>

	var i18n=new Object();
	i18n['username'] = '<spring:message code="system.user.username"/>';
	i18n['nickname'] = '<spring:message code="system.user.nickname"/>';
	i18n['phone'] = '<spring:message code="system.user.phone"/>';
	i18n['sex'] = '<spring:message code="sex"/>';
	i18n['roleName'] = '<spring:message code="system.role.name"/>';
	i18n['createTime'] = '<spring:message code="create.time"/>';
	i18n['status'] = '<spring:message code="status"/>';
	i18n['opt'] = '<spring:message code="opt"/>';

</script>