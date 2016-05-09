<%--
  Created by IntelliJ IDEA.
  User: gongyb08837
  Date: 2016/1/24
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>Welcome to APIManager</title>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <!-- 可选的Bootstrap主题文件（一般不用引入） -->
  <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

  <style type="text/css">
    .form-signin{
      max-width: 330px;
      padding:15px;
      margin: 0 auto;
    }
    input{
      margin-bottom: 3px;
    }
  </style>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!--左侧导航start-->
    <div id="navbar" class="col-md-3" style="position:relative;background:#f5f5f5;padding:10px;height:100%;border-right:#ddd 1px solid;overflow-y:auto;">
      <div style="height:50px;font-size:30px;line-height:50px;">
        <a class="home" style="color:#000000;text-shadow:1px 0px 1px #666;text-decoration: none" href="#">
          <span class="glyphicon glyphicon-random" aria-hidden="true" style="width:40px;"></span>&nbsp;
          <span style="position: relative;top:-3px;">API Manager </span>
          <span style="font-size:12px;position:relative;top:-13px;">&nbsp;V1.0</span>
        </a>
      </div>
      <%--<%@ include  file="menu.jsp"%>--%>
    </div>
    <!--左侧导航end-->
    <div id="mainwindow" class="col-md-9"   style="height:100%;background:white;margin:0px;overflow-y:auto;padding:0px;">
    <!--顶部导航start-->
    <div class="textshadow" style="font-size:16px;widht:100%;height:60px;line-height:60px;padding:0 16px 0 16px;;border-bottom:#ddd 1px solid">
      <span> <a class="home" href="#">Home</a></span>
			        <span id="topbutton" style="float:right">
			           <a href="#">修改密码</a>&nbsp;&nbsp;<a href="#">退出&nbsp;&nbsp;<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a>
			           <%--<a href="?act=login">登录&nbsp;&nbsp;<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></a>--%>
			        </span>
    </div>
    <!--顶部导航end-->
    <!--主窗口start-->
    <div style="padding:16px;">
    </div>
    <!--主窗口end-->
  </div>
  </div>
</div><!-- /container -->
</body>
</html>



