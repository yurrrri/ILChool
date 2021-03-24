package model;

import java.util.List;

public class TO_DO_LIST {
	private String memberid;
	private String TO_DO;
	private int TO_DO_ID;
	private List<Member> memberList;
	
	public TO_DO_LIST() {}
	
	public TO_DO_LIST(String TO_DO) {
		super();
		this.TO_DO = TO_DO;
	}
	public TO_DO_LIST(String TO_DO, int TO_DO_ID) {
		super();
		this.TO_DO = TO_DO;
		this.TO_DO_ID = TO_DO_ID;
	}
	public TO_DO_LIST(String memberid, String TO_DO, int TO_DO_ID) {
		super();
		this.memberid = memberid;
		this.TO_DO = TO_DO;
		this.TO_DO_ID = TO_DO_ID;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getTO_DO() {
		return TO_DO;
	}
	public void setTO_DO(String tO_DO) {
		TO_DO = tO_DO;
	}
	public int getTO_DO_ID() {
		return TO_DO_ID;
	}
	public void setTO_DO_ID(int tO_DO_ID) {
		TO_DO_ID = tO_DO_ID;
	}
	public List<Member> getMemberList() {
		return memberList;
	}


	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
	
}
