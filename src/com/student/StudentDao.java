package com.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy
 * 数据库操作类
 */
public class StudentDao {

	private static StudentDao studentDao = new StudentDao();

	public StudentDao() {
	}
	
	public static StudentDao getInstance(){
		return studentDao;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 * @return page
	 */
	public Page pagedQuery(int start,int limit,String sort,String dir) {
		StringBuilder sql = new StringBuilder("select * from student");
		if(sort != null && !sort.equals("") && dir != null && !dir.equals("")){
			sql.append(" order by " + sort + " " + dir);
		}
		sql.append(" limit " + start + " , " + limit);
		List<Student> result = null;
		try{
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			result = new ArrayList<Student>();
			while(rs.next()){
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setCode(rs.getString("code"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getString("birthday"));
				student.setOrigin(rs.getString("origin"));
				student.setDept(rs.getString("dept"));
				result.add(student);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
		//查询学生信息的数量
		int totalCount = 0;
		try{
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from student");
			rs = pstmt.executeQuery();
			if(rs.next()){
				totalCount = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbUtil.close(rs, pstmt, conn);
		}
		
		Page page = new Page(totalCount,result);
		return page;
		
	}
	
	/**
	 * 删除学生信息
	 * @param id
	 * @return affectRows
	 */
	public int delete(int id){
		String sql = "delete from student where id = ?";
		conn = DbUtil.getConnection();
		int affectRows = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			affectRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtil.close(rs, pstmt, conn);
		}
		return affectRows;
	}
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public int update(Student student){
		String sql = "update student set code=?,name=?,sex=?,birthday=?,origin=?,dept=? where id = ?";
		conn = DbUtil.getConnection();
		int affectRows = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getCode());
			pstmt.setString(2, student.getName());
			pstmt.setInt(3, student.getSex());
			pstmt.setString(4, student.getBirthday());
			pstmt.setString(5, student.getOrigin());
			pstmt.setString(6, student.getDept());
			pstmt.setInt(7, student.getId());
			affectRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtil.close(rs, pstmt, conn);
		}
		return affectRows;
	}
	
	/**
	 * 删除学生信息
	 * @param student
	 * @return
	 */
	public int add(Student student){
		String sql = "insert into student(code,name,sex,birthday,origin,dept) values (?,?,?,?,?,?)";
		conn = DbUtil.getConnection();
		int affectRows = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getCode());
			pstmt.setString(2, student.getName());
			pstmt.setInt(3, student.getSex());
			pstmt.setString(4, student.getBirthday());
			pstmt.setString(5, student.getOrigin());
			pstmt.setString(6, student.getDept());
			affectRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtil.close(rs, pstmt, conn);
		}
		return affectRows;
	}

	
}
