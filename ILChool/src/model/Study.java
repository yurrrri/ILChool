package model;

import java.util.Date;
import java.util.List;

public class Study {
	private int studyID;
	private String title;		//제목
	private String writer;		//작성자: fk member(member_id)
	private Date reportingDate;	//작성일
	private String category;	//카테고리
	private String location;	//지역
	private String age;			//연령대
	private String companyName;	//기업이름
	private int period;			//기간
	private int maxHeadcount;	//최대 인원
	

	private int currHeadcount;	//현재 인원
	private List<Member> memberList;	//스터디에 속한 멤버리스트
	
	public Study() { }
	
	public Study(int studyID, String title, String writer, String category, String location,
			String age, String companyName, int period, int maxHeadcount, int currHeadcount) {
		this.studyID = studyID;
		this.title = title;
		this.writer = writer;
		this.category = category;
		this.location = location;
		this.age = age;
		this.companyName = companyName;
		this.period = period;
		this.maxHeadcount = maxHeadcount;
		this.currHeadcount = currHeadcount;
	}

	public Study(int studyID, String title, String writer, Date reportingDate, String category, String location,
			String age, String companyName, int period, int maxHeadcount, int currHeadcount) {
		super();
		this.studyID = studyID;
		this.title = title;
		this.writer = writer;
		this.reportingDate = reportingDate;
		this.category = category;
		this.location = location;
		this.age = age;
		this.companyName = companyName;
		this.period = period;
		this.maxHeadcount = maxHeadcount;
		this.currHeadcount = currHeadcount;
	}

	public int getStudyID() {
		return studyID;
	}


	public void setStudyID(int studyID) {
		this.studyID = studyID;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public Date getReportingDate() {
		return reportingDate;
	}


	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public int getPeriod() {
		return period;
	}


	public void setPeriod(int period) {
		this.period = period;
	}


	public int getMaxHeadcount() {
		return maxHeadcount;
	}


	public void setMaxHeadcount(int maxHeadcount) {
		this.maxHeadcount = maxHeadcount;
	}


	public int getCurrHeadcount() {
		return currHeadcount;
	}


	public void setCurrHeadcount(int currHeadcount) {
		this.currHeadcount = currHeadcount;
	}
	
	public List<Member> getMemberList() {
		return memberList;
	}


	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
	
	
	@Override
	public String toString() {
		return "Study [studyID=" + studyID + ", title=" + title + ", writer=" + writer + ", reportingDate="
				+ reportingDate + ", category=" + category + ", location=" + location + ", age=" + age
				+ ", companyName=" + companyName + ", period=" + period + ", maxHeadcount=" + maxHeadcount
				+ ", currHeadcount=" + currHeadcount + ", memberList=" + memberList + "]";
	}
	
}