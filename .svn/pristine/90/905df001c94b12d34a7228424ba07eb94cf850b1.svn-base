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
    <title>${p.code_name}查看页面</title>
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
	<!--框架必需end-->
	<script type="text/javascript" src="${"${"}path}/js/Validator.js"></script>
	<!--验证框架-->
	<script type="text/javascript">
		function submitToAction(){
			var isNull= Validator.Validate(document.positionForm,2);   
			if(isNull){
				document.positionForm.submit();
			}else{
				return false;
			}
		}
	</script>
  </head>
  
  <body>
<div class="position">
	<div class="center">
	<div class="left">
	<div class="right">
		<span>当前位置：${p.code_name} >> 查看</span>
	</div>	
	</div>	
	</div>
</div>	
<div id="scrollContent">
	<div class="box2" panelWidth="98%" panelTitle="${p.code_name}查看" showStatus="false" roller="true">
	<form action=""  method="post" id="positionForm" name="positionForm">
	<table class="tableStyle" formMode="true">
		
		<#list p.gci_columns as u>
		<#if u.clumn_name=='id'><#else>
		<tr>
			<td><%=${p.className}Query.ALIAS_${u.clumn_name} %>：</td>
			<td>
			<#if u.dataType=="Date"><fmt:formatDate value="${"${"}vo.${u.clumn_name}}" type="date"/><#else>${"${"}vo.${u.clumn_name}}</#if>
			</td>
		</tr>
		</#if>	
		</#list>
		<tr>
			<td colspan="2">
				<input name="back" type="button"  value="返 回" onClick="javascript:history.back(-1);"/>
			</td>
		</tr>
	</table>
	</form>
	</div>
</div>
</body>
</html>