package model.dao;


import model.Member;

public interface MemberMapper {

	public int insertMember(Member memberId);   
 
	public int updateMember(Member memberId);
	

	public int deleteMember(String memberId);


}
