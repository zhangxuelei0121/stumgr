package com.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Student student = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		String method = request.getParameter("method");
		if("list".equals(method)){
			this.list(request, response);
		}else if("add".equals(method) || "update".equals(method)){
			String id = request.getParameter("id");
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String origin = request.getParameter("origin");
			String dept = request.getParameter("dept");
			
			student = new Student();
			student.setCode(code);
			student.setName(name);
			student.setSex(Integer.parseInt(sex));
			student.setBirthday(birthday);
			student.setOrigin(origin);
			student.setDept(dept);
			
			if("add".equals(method)){
				this.add(request, response);
			}else if("update".equals(method)){
				student.setId(Integer.parseInt(id));
				this.update(request, response);
			}
			response.getWriter().write("{success:true,msg:'保存成功'}");
		}else if("delete".equals(method)){
			this.delete(request, response);
		}
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		response.getWriter().write(pager.toString());
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDao studentDao = StudentDao.getInstance();
		studentDao.add(student);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDao studentDao = StudentDao.getInstance();
		studentDao.update(student);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		StudentDao studentDao = StudentDao.getInstance();
		studentDao.delete(Integer.parseInt(id));
		response.getWriter().write("{success:true,msg:'删除成功'}");
	}
	
}
