package model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Member;
import model.dao.MemberDAO;

// an example business class
public class MemberAnalysis {
	private MemberDAO dao;
	
	public MemberAnalysis() {}
	
	public MemberAnalysis(MemberDAO dao) {
		super();
		this.dao = dao;
	}

	// an example business method
	public List<Member> recommendFriends(String memberId) throws Exception {
		Member thisMember = dao.findMember(memberId);
		if (thisMember == null) {
			throw new Exception("invalid member ID!");
		}
		int m1 = memberId.indexOf('@');
		if (m1 == -1) return null;
		String mserver1 = thisMember.getEmail().substring(m1);
		
		List<Member> friends = new ArrayList<Member>();
		
		List<Member> memberList = dao.findmemberList(1, 10000);
		Iterator<Member> memberIter = memberList.iterator();		
		while (memberIter.hasNext()) {
			Member member = (Member)memberIter.next();
			
			if (member.getmemberId().equals(memberId)) continue;
			int m2 = member.getEmail().indexOf('@');
			if (m2 == -1) continue;
			String mserver2 = member.getEmail().substring(m2);

			if (mserver1.equals(mserver2)) 
				friends.add(member);		
		}
		return friends;
	}

}
