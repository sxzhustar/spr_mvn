<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/global.jsp" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<link href="${rs}/css/layout/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	.form-control{width:300px;}
</style>
<script type="text/javascript" src="${rs}/js/plugins/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${rs}/js/layout/logincloud.js"></script>
</head>
<body onkeydown="toLogin(event)">
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="logintop">
		<div>欢迎登陆后台本系统</div>
		<ul>
			<li><a href="javascript:;">回首页</a>
			<li><a href="javascript:;">帮助</a>
			<li><a href="javascript:;">关于</a>
		</ul>
	</div>
	<div class="loginbody">
		<div class="systemlogo"></div>
		<form id="loginForm" action="${ctx}/login.do" method="post" class="loginForm">
			<div class="loginbox">
				<ul>
					<li id="warnInfo" style="display:none">
						<div id="msgInfo" class="alert alert-danger">
							<label id="msgShow">${warn}</label>
						</div>
					</li>
					<li><!-- 用户名 -->
						<div class="input-group">
							<span class="input-group-addon">
								<i class="graphic graphic-user"></i>
								<input type="text" id="username" name="username" class="form-control" placeholder="请输入登录名">
							</span>
						</div>
					</li>
					<li><!-- 密码 -->
						<div class="input-group">
							<span class="input-group-addon">
								<i class="graphic graphic-lock"></i>
								<input type="text" id="password" name="password" class="form-control" placeholder="请输入密码">
							</span>
						</div>
					</li>
					<li><!-- 验证码 -->
						<div>
							<div class="col-8"><input id="passKey" name="passKey" class="form-control col-8" placeholder="请输入验证码" autocomplete="off"></div>
							<div class="col-5"><img id="passkey_img" alt="验证码" src="<%=request.getContextPath()%>/loadPasskey.do"></div>
						</div>
					</li>
					<li><!-- 记住我 -->
						<div class="col-3"><input id="btnLogin" type="button" class="btn btn-primary" value="登陆"></div>
						<div class="col-9"><input name="rememberMe" value="1" type="checkbox" checked="checked">记住密码&nbsp;&nbsp;<a href="#">忘记密码？</a></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
	<div class="loginbottom">
		copyright@zsx：bluedon 2017-4-20
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var msg = $.trim($("#msgShow").text());
		msg && $("#warnInfo").show();
		
		$("#btnLogin").click(login);
	});
	//登陆
	function login(){
		var user = $("#username").val();
		var psd = $("#password").val();
		var pkey = $("#passKey").val();
		var str = !user?"用户名不能为空":!psd?"密码不能为空":!pkey?"验证码不能为空":"";
		if(!str){
			$("#loginForm").submit();
		}else{
			$("#msgShow").text(str);
			$("#warnInfo").show();
		}
	}
	
	//刷新验证码
	$("#passkey_img").click(function(){
		$("#passkey_img").attr("src","${ctx}/loadPasskey.do?timestamp="+new Date().getTime());
	});
	
	//回车登陆
	function toLogin(e){
		if(e.keyCode == 13){
			login();
		}
	}
</script>
</html>