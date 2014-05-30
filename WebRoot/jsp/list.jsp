<%@ page contentType="application/json; charset=UTF-8" import="com.student.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	int start = 0;
	try{
		start = Integer.parseInt(request.getParameter("start"));
	}catch(Exception e){
		e.printStackTrace();
	}
	int limit = 15;
	try{
		limit = Integer.parseInt(request.getParameter("limit"));
	}catch(Exception e){
		e.printStackTrace();
	}
	String sort = request.getParameter("sort");
	String dir = request.getParameter("dir");

	StudentDao studentDao = StudentDao.getInstance();
	Page pager = studentDao.pagedQuery(start, limit, sort, dir);
	out.print(pager.toString());
%>