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
      <form class="form-signin" role="form" action="login.do">
        <h2 class="form-signin">欢迎登录</h2>
        <input type="text" class="form-control" placeholder="请输入用户名" required>
        <input type="password" class="form-control" placeholder="请输入密码" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
      <%--<img src="../images/login_bg.jpg">--%>
      <img src="../../images/login_bg.jpg">
    </div><!-- /container -->
  </body>
</html>
