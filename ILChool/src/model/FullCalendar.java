package model;

import java.util.Date;

public class FullCalendar {
	private String company_name;
	private Date endDate;
	private String memberid;
	
	
	
	public FullCalendar(String company_name, Date endDate) {
		super();
		this.company_name = company_name;
		this.endDate = endDate;
	}
	public FullCalendar(String company_name, Date endDate, String memberid) {
		super();
		this.company_name = company_name;
		this.endDate = endDate;
		this.memberid = memberid;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	
	
}
