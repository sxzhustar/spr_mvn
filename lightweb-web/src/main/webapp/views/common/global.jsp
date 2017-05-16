<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	String basePath = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort() + request.getContextPath();
	request.setAttribute("path", basePath);
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="rs" value="${ctx}/static"></c:set>

<link href="${rs}/css/layout/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${rs}/js/plugins/jquery-2.2.3.min.js"></script>
<script src="${rs}/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script> 
<script src="${rs}/js/plugins/artDialog/plugins/iframeTools.js"></script> 
<script type="text/javascript" src="${rs}/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="${rs}/js/utils/artDialogExt.js"></script>
<script type="text/javascript" src="${rs}/js/utils/tableCommon.js"></script>
<script type="text/javascript">
$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};
</script>