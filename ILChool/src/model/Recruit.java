package model;

import java.io.Serializable;
import java.util.Date;

public class Recruit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String company_name;
	private String recruit_url;
	private String company_form;
	private String title;
	private String workingType;
	private Date regDate;
	private Date deadLine;
	
	public Recruit() {};
	
	public Recruit(String company_name, String recruit_url, String company_form, String title,
			String workingType, Date regDate, Date deadLine) {
		this.company_name = company_name;
		this.recruit_url = recruit_url;
		this.company_form = company_form;
		this.title = title;
		this.workingType = workingType;
		this.regDate = regDate;
		this.deadLine = deadLine;
	}
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getRecruit_url() {
		return recruit_url;
	}
	public void setRecruit_url(String recruit_url) {
		this.recruit_url = recruit_url;
	}
	public String getCompany_form() {
		return company_form;
	}
	public void setCompany_form(String company_form) {
		this.company_form = company_form;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWorkingType() {
		return workingType;
	}
	public void setWorkingType(String workingType) {
		this.workingType = workingType;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
}
