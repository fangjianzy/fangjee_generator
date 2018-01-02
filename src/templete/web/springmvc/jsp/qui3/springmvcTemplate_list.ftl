<%@ page import="com.fangjian.framework.common.PageInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
 <%
 	String path = request.getContextPath();
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 	//分页需要
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageinfo");
	String url = "/${p.lowerName}Controller/list.do";
	String linkPath = url+ "?action=List";
%>
<%@ page import="${p.basePackage}.query.${p.bizPackage}.${p.className}Query"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${p.code_name}列表页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--框架必需start-->
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" />
	<link rel="stylesheet" type="text/css" id="customSkin"/>
	<!--框架必需end-->
	<!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
	<!-- 日期选择框end -->
	
	<!--树组件与弹窗组件（这里引入只为“非iframe弹窗（复杂内容）”示例需要） -->
	<script type="text/javascript" src="<%=path%>/libs/js/tree/ztree/ztree.js"></script>
	<link href="<%=path%>/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path%>/libs/js/popup/drag.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/popup/dialog.js"></script>
	<!--树组件与弹窗组件（这里引入只为“非iframe弹窗（复杂内容）”示例需要） -->
	
	
	
	<script type="text/javascript">
	
	$(function(){
		top.Dialog.close()
	})
	
	function initComplete(){
		var layout=$("#layout1").layout({leftWidth: 200,rightWidth:150}); 
		//设计右边默认关闭
		//layout.setRightCollapse(true);
	}
	function customHeightSet(contentHeight){
		$(".layout_content").height(contentHeight-30)
	}
	
	//获取选择的值
	function getRadioRow(){
		var msg="";
		msg=$("#radioTable").find("input[type=radio]").filter("[checked]").val();
		if(msg=="" | msg == null){
			//top.Dialog.alert(msg);
			msg=0;
		}else{
			
		}
		
		return msg;
	}
	//新增
	function add(){
		location="<%=path%>/${p.lowerName}Controller/toAdd.do";
	}
	//删除
	function del(){
		var id = getRadioRow();
		if(id==0){
			top.Dialog.alert("请先选择要删除的数据!");
		}else{
			top.Dialog.confirm("您确定要删除这条信息",
					function(){
						location="<%=path%>/${p.lowerName}Controller/deleteById.do?${p.pk_name}="+id;
					},
					function(){
						//top.Dialog.alert("点击了取消")
					});
			
		}
	}
	//更新
	function edit(){
		var id = getRadioRow();
		if(id==0){
			top.Dialog.alert("请先选择要更新的数据!");
		}else{
			location="<%=path%>/${p.lowerName}Controller/editById.do?${p.pk_name}="+id;
		}
	}
	//查看
	function view(){
	    var id = getRadioRow();
		if(id==0){
			top.Dialog.alert("你没有选择数据!");
		}else{
			location="<%=path%>/${p.lowerName}Controller/findById.do?${p.pk_name}="+id;
		}
	}
	function query()
	{
	    ${"$"}("#form1").action="<%=path%>/${p.lowerName}Controller/list.do";
		${"$"}("#form1").submit();
	}
	</script>	
  </head>
  
  <body>
  	<!-- form 标签是必须存在，否则分页出问题 -->
    <form id="form1" name="form1" method="post" action="" AUTOCOMPLETE="OFF">
	<div class="box2" panelTitle="查询" id="searchPanel" statusText="显示" startState="close" afterStatusText="隐藏">
		<table>
			<tr>
		<#list p.gci_columns as u>
			
		<#if (u.column_key=='PRI')> 
	
		<#else> 
			<#if (u.dataType=="Date")>
				<td><%=${p.className}Query.ALIAS_${u.javaColumnFileNameCode} %></td>
				<td><input type="text" name="${u.javaColumnFileNameCode}" value="" class="date[custom[date]]"/></td>
			<#else>
	    	<td><%=${p.className}Query.ALIAS_${u.javaColumnFileNameCode} %></td>
			<td><input type="text" name="${u.javaColumnFileNameCode}" value=""/></td>
	    	</#if>
		</#if> 
			
		</#list>
				<td><button onclick="query();"><span class="icon_find">查询</span></button></td>
			</tr>
		</table>
	</div>
	<div class="float_left padding5" style="margin-top:1px">
			<button type="button" onclick="add()"><span class="icon_save">新增</span></button>
			<button type="button" onclick="edit()"><span class="icon_edit">编辑</span></button>
			<button type="button" onclick="del()"><span class="icon_delete">删除</span></button>
			<button type="button" onclick="view()"><span class="icon_find">查看</span></button>
			<!--
			<button type="button" onclick="chexk()"><span class="icon_ok">提交</span></button>
			-->
	</div>
	<div class="clear"></div>
	<div id="scrollContent"   style="overflow-x:hidden;">
		<!-- 内容分页区     开始 -->
		
		<table class="tableStyle" mode="list" useRadio="true" id="radioTable" >
			<tr>
			    <#list p.gci_columns as u>
			    	<th width="10%">
			    		<%=${p.className}Query.ALIAS_${u.javaColumnFileNameCode} %>
			    	</th>
				</#list>
			</tr>
			<c:forEach items="${"$"}{list}" var="vo">
			<tr>
			 <#list p.gci_columns as u>
			 	<#if (u.column_key=='PRI')> 
					<td><input type="radio" name="${u.javaColumnFileNameCode}" value="${"${"}vo.${u.javaColumnFileNameCode}}"/></td>
				<#else> 
					<#if (u.dataType=="Date")>
					<td><fmt:formatDate value="${"${"}vo.${u.javaColumnFileNameCode}}" type="both"/></td>
					<#else>
			    	<td>${"${"}vo.${u.javaColumnFileNameCode}}</td>
			    	</#if>
				</#if> 
			</#list>
			</tr>
			</c:forEach>
		</table>
		<!-- 内容分页区     结束 -->
	</div>


	<div style="height:40px;">
		 <!----------start:上下翻页   固定的分页方法，一般情况不需要修改，除非进行一些特定的参数传递  by fangjian------------------>
	      <table width='100%'  border='0' cellpadding='1' cellspacing='1' bgcolor='' align="center" style="margin-top:1px">
			<tr>
			<td height="25" colspan="10" align="center">
					<!--分页显示模块-->
					<pg:pager items="<%=pageInfo.getTotalCount()%>"
						index="center"
						maxPageItems="<%= pageInfo.getPageSize() %>"
						maxIndexPages="10" isOffset="<%= true %>" url="<%=url%>"
						export="offset,currentPageNumber=pageNumber"
						scope="request">
						<pg:param name="maxPageItems" />
						<pg:index>
							<jsp:include page="/common/pager.jsp" flush="true">
								<jsp:param name="postURL" value="<%=linkPath%>" />
							</jsp:include>
						</pg:index>
					</pg:pager>
	
					<%
						if (pageInfo.getTotalCount() <= pageInfo.getPageSize()) {
					%>
					<jsp:include page="/common/single_pager.jsp"
						flush="true">
						<jsp:param name="postURL" value="<%=linkPath%>" />
					</jsp:include>
					<%
						}
					%>
				</td>
			</tr>
			</table>
			<!----------end:上下翻页------------------>
	</div>
	<div class="clear"></div>
	<div style="height: 30px"></div>
</form>
</body>
</html>
