package model.service;

import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import model.Member;
import model.dao.MemberDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * memberDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class MemberManager {
private static final Logger log = LoggerFactory.getLogger(MemberManager.class);
	private static MemberManager memberMan = new MemberManager();
	private MemberDAO memberDAO;
	private MemberAnalysis memberAanlysis;

	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
			memberAanlysis = new MemberAnalysis(memberDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MemberManager getInstance() {
		return memberMan;
	}
	
	public Member create(Member member) throws SQLException, ExistingMemberException {
		if (memberDAO.existingMember(member.getmemberId()) == true) {
			throw new ExistingMemberException(member.getmemberId() + "는 존재하는 아이디입니다.");
		}
		return memberDAO.create(member);
	}
	
	public int update(Member member) throws SQLException, MemberNotFoundException {
		
		return memberDAO.update(member);
	}	
	
	public int remove(String memberId) throws SQLException, MemberNotFoundException {

		return memberDAO.remove(memberId);
	}




	public Member findMember(String memberId)
		throws SQLException, MemberNotFoundException {
		Member member = memberDAO.findMember(memberId);
		
		if (member == null) {
			throw new MemberNotFoundException(memberId + "는 존재하지 않는 아이디입니다.");
		}		
		return member;
	}

	public List<Member> findmemberList() throws SQLException {
			return memberDAO.findmemberList();
	}
	
	public List<Member> findmemberList(int currentPage, int countPerPage)
		throws SQLException {
		return memberDAO.findmemberList(currentPage, countPerPage);
	}

	public boolean login(String memberId, String password)
		throws SQLException, MemberNotFoundException, PasswordMismatchException {
		
		Member member = findMember(memberId);
		
		if (!member.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public List<Member> makeFriends(String memberId) throws Exception {
		return memberAanlysis.recommendFriends(memberId);
	}
	

	public MemberDAO getMemberDAO() {
		return this.memberDAO;
	}
}
