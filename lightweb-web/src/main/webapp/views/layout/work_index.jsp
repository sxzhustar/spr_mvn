<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>默认显示页</title>

</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="static/images/layout/sun.png" alt="天气" /></span>
    <b>${user.userName }，欢迎使用后台管理系统</b>
<%--     <a href="${path }/personalCenterController/editById.do">帐号设置</a> --%>
    <a href="javascript:;">帐号设置</a>
    </div>

    </div>
    
    

</body></html>