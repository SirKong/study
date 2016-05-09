package com.ccnu.test.validation.model;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class SimpleEntity {

	@NotBlank(message = "名字不能为空或者空串")
	@Length(min = 2, max = 10, message = "名字必须由2~10个字组成")
	private String name;

	@Past(message = "时间不能晚于当前时间")
	private Date date;

	@Email(message = "邮箱格式不正确")
	private String email;

	@Pattern(regexp = "/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}$/", message = "password ")
	private String password;

	@AssertTrue(message = "字段必须为真")
	private boolean valid;

	private String idno;

	private String idtype;

	public SimpleEntity(String name, Date date, String email, String password, boolean valid, String idno, String idtype) {
		super();
		this.name = name;
		this.date = date;
		this.email = email;
		this.password = password;
		this.valid = valid;
		this.idno = idno;
		this.idtype = idtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	@Override
	public String toString() {
		return "SimpleEntity [name=" + name + ", date=" + date + ", email=" + email + ", password=" + password
				+ ", valid=" + valid + ", idno=" + idno + ", idtype=" + idtype + "]";
	}

}