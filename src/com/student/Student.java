package com.student;

/**
 * @author Andy
 * 学生JavaBean
 */
public class Student {

	private int id;
	private String code;
	private String name;
	private int sex;
	private String birthday;
	private String origin;
	private String dept;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public String toString() {
        return "{id:" + id +
            ",code:'" + code +
            "',name:'" + name +
            "',sex:" + sex +
            ",birthday:'" + birthday +
            "',origin:'" + origin +
            "',dept:'" + dept +
            "'}";
    }
	
}
