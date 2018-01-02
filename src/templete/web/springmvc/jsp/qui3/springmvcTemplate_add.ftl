<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="${p.basePackage}.query.${p.bizPackage}.${p.className}Query"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${"${"}pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${p.code_name}新增页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	
	<!--框架必需start-->
	<script type="text/javascript" src="${"${"}path}/libs/js/jquery.js"></script>
	<script type="text/javascript" src="${"${"}path}/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="${"${"}path}/libs/js/framework.js"></script>
	<link href="${"${"}path}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="${"${"}path}/" />
	<link rel="stylesheet" type="text/css" id="customSkin"/>
	<!-- 日期选择框start -->
	<script type="text/javascript" src="${"${"}path}/libs/js/form/datePicker/WdatePicker.js"></script>
	<!-- 日期选择框end -->
	<!-- 表单验证start -->
	<script src="${"${"}path}/libs/js/form/validationRule.js" type="text/javascript"></script>
	<script src="${"${"}path}/libs/js/form/validation.js" type="text/javascript"></script>
	<!-- 表单验证end -->

	<script type="text/javascript">
		function submitToAction(){
			var valid = ${"$"}("#positionForm").validationEngine({returnIsValid: true});
			if(valid){
				//top.Dialog.alert("验证通过，此处是提交表单的处理!");
				document.positionForm.submit();
			}else{
				return false;
			}
		}
	</script>
  </head>
<body>
<div id="scrollContent">
	<div class="box2" panelWidth="98%" panelTitle="${p.code_name}维护" showStatus="false" roller="true">
	<form action="${"${"}path}/${p.lowerName}Controller/add.do"  method="post" id="positionForm" name="positionForm" AUTOCOMPLETE="OFF">
	<table class="tableStyle" formMode="line">
		
		<#list p.gci_columns as u>
		<#if u.column_key=='PRI'><#else>
		<tr>
			<td><%=${p.className}Query.ALIAS_${u.javaColumnFileNameCode} %>：</td>
			<td>
			<input type="text" <#if u.dataType=="Date">class="${u.requireClass}" <#else> class="${u.requireClass}" style="width:256px"</#if>  name="${u.javaColumnFileNameCode}"  id="${u.javaColumnFileNameCode}_id"  value="" /><#if u.require=='true'><span class="star">*</span><#else></#if>
			</td>
		</tr>
		</#if>	
		</#list>
		<tr>
			<td colspan="2">
				<input type="button"  value="提 交" onclick="submitToAction();"/>
				<input type="reset" value="重 置"/>
				<input name="back" type="button"  value="返 回" onClick="javascript:history.back(-1);"/>
			</td>
		</tr>
	</table>
	</form>
	</div>
</div>
<div class="clear"></div><div class="box1" panelWidth="100%" panelHeight="100"></div>
</body>
</html>