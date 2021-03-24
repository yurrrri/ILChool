package model;

public class Company {
	private int company_id;
	private String url;
	private String name;
	private String form;
	private String summary;
	
	public Company() {}

	public Company(int company_id, String url, String name, String form, String summary) {
		this.company_id = company_id;
		this.url = url;
		this.name = name;
		this.form = form;
		this.summary = summary;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
