<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx}/static/css/layout/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${rs}/js/plugins/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		resize();
		$(window).resize(function(){
			resize();
		});

		//顶部导航
		$(".nav li a").click(function(){
			$(".nav li a.selected").removeClass("selected");
			$(this).addClass("selected");
		});
		$(".menuson li").click(function(){
			console.log($(".menuson li.active"));
			$(".menuson li.active").removeClass("active");
			$(this).addClass("active");
		})
		$(".title").click(function(){
			var $ul = $(this).next('ul');
			$("dd").find("ul").slideUp();
			if($ul.is(":visible")){
				$(this).next("ul").slideUp();
			}else{
				$(this).next("ul").slideDown();
			}
		});
		
		//默认进入菜单
		$(".nav li:first > a").trigger("click");
	})
	function changeList(menu){
		$(".leftmenu dd").hide();
		var $menu = $("[class^="+menu+"_]");
		$menu.show();
		$menu.first().find(".title").trigger("click");
	}
	
	function resize(){
		var headH = $("#top").height();
		var footH = $("#footer").height();
		var bodyH = $(window).height() - headH - footH;
		var bodyW = $(window).width() - $("#middle-left").width();
		$("#middle,#middle-left,#middle-right,#rightFrame").height(bodyH);
		$("#middle").width(bodyW);
	}
	
</script>
</head>
<body>
	<div id="top" style="background:url(${rs}/images/layout/topbg.gif) repeat-x;height: 88px;width: 100%">
		<div class="topleft">
			<a href="javascript:;"><img alt="系统首页" src="${rs}/images/layout/web-logo.png"></a>
		</div>
		<ul class="nav">
			<c:forEach var="res" items="${resList}">
				<c:if test="${res.level == 1}">
					<li>
						<a href="javascript:;" id="sys_${res.id}" onclick="changeList('sys_${res.id}')">
							<img alt="${res.resource_name}" src="${rs}/images/layout/${res.resource_icon}.png">
							<br>${res.resource_name}
						</a>
					</li>
				</c:if>
			</c:forEach>
		</ul>
		<div class="topright">
			<ul>
				<li><a href="javascript:;">首页</a>
				<li><a href="" target="rightFrame">信息维护</a>
				<li><a href="javascript:;" target="_blank" onclick="javascript:window.location='${path}/logout.do'">退出</a>
			</ul>
			<div class="user">
				<span>${user.userName}</span>
			</div>
		</div>
	</div>
	<div id="middle" style="width:100%;height:100%">
		<div id="middle-left" style="width: 187px;height: 100%;float: left;background:#f0f9fd;">
			<div class="lefttop"><span></span>菜单</div>
			<dl class="leftmenu">
				<c:forEach var="res" items="${resList}" varStatus="status">
				<c:if test="${res.level == 2}">
					<dd style="display:none;" class="sys_${res.parent_id}_${res.id}">
						<div class="title">
							<span></span><img alt="" src="${rs}/images/layout/leftico01.png">${res.resource_name}
						</div>
						<ul class="menuson" style="display:none">
								<c:forEach var="r" items="${resList}">
									<c:if test="${r.level == 3 && r.parent_id == res.id}">
										<li><cite></cite><a href="${ctx}/${r.resource_path}" target="rightFrame">${r.resource_name}</a><i></i></li>
									</c:if>
								</c:forEach>
							</ul>
					</dd>
				</c:if>
				</c:forEach>
			</dl>
		</div>
		<div id="middle-right" style="float:left">
			<iframe id="rightFrame" name="rightFrame" style="width:100%;border:0;" src="${path}/workIndex.do"></iframe>
		</div>
		<div id="clear" style="clear: both;"></div>
	</div>
	<div id="footer" style="background-color:#eee">
		<center>copyright@zsx-2017-4-19</center>
	</div>
</body>
</html>