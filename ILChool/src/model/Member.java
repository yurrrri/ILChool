package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Member implements Serializable{

	private String memberId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private int age;
	

	public Member() { }		
	
	public Member(String memberId, String password, String name, String email, String phone, int age) {
	
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
	}


	public Member(String  memberId, String name) {
		this.memberId = memberId;
		this.name = name;
	}
	
	/*public void update(member updateMember) {
        this.password = updatemember.password;
        this.name = updatemember.name;
        this.email = updatemember.email;
        this.phone = updatemember.phone;
    }*/
	
	public String  getmemberId() {
		return memberId;
	}

	public void setmemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameMember(String memberId) {
        return this.memberId.equals(memberId);
    }
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", age=" + age + "]";
	}	




}

