package com.ericsson.school.weblayer.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "student", eager = true)
public class StudentBean {

	@ManagedProperty(value = "#{institute}")
	private InstituteBean institute;

	private String name;

	private String neptun;

	public String getName() {
		return this.name;
	}

	public String getNeptun() {
		return this.neptun;
	}

	public InstituteBean getInstitute() {
		return this.institute;
	}

	public void setInstitute(InstituteBean institute) {
		this.institute = institute;
	}

	public String getMessage() {
		return this.institute.getName();
	}

}
