package model;

public class Recruit_wish {
	private int recruit_wish_id;
	private String member_id;
	private Recruit recruit;
	
	public Recruit_wish() {};
	
	public Recruit_wish(int recruit_wish_id, String member_id, Recruit recruit) {
		super();
		this.recruit_wish_id = recruit_wish_id;
		this.member_id = member_id;
		this.recruit = recruit;
	}

	public int getRecruit_wish_id() {
		return recruit_wish_id;
	}

	public void setRecruit_wish_id(int recruit_wish_id) {
		this.recruit_wish_id = recruit_wish_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Recruit getRecruit() {
		return recruit;
	}

	public void setRecruit(Recruit recruit) {
		this.recruit = recruit;
	}	
}
