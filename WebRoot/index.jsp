<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css">
	<script type="text/javascript" src="extjs/ext-all-dev.js"></script>
	<script type="text/javascript" src="extjs/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="script/student.js"></script>
  </head>
  
  <body>
    <div id="main">
         <div id="head" style="font-weight:bold;font-size:200%;text-align: center;">学生信息管理</div>
         <div id="foot" style="text-align:right;"> - &copy; 2014 道明寺 - </div>
    </div>
  </body>
</html>
