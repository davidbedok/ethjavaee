package com.ericsson.school.weblayer.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "institute", eager = true)
public class InstituteBean {

	private String name = "OE";

	public String getName() {
		return this.name;
	}

}
