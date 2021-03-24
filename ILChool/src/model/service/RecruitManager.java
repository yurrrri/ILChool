package model.service;

import java.util.List;

import model.Recruit;
import model.Recruit_wish;
import model.dao.RecruitDAO;

public class RecruitManager {
	private static RecruitManager recruitMan = new RecruitManager();
	private RecruitDAO recDAO;

	private RecruitManager() {
		try {
			recDAO = new RecruitDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static RecruitManager getInstance() {
		return recruitMan;
	}
	
	public List<Recruit> findRecruitList() {
		return recDAO.findRecruitListFromWorknet();
	}
	
	public Recruit findRecruit(String title) {
		return recDAO.findRecruit(title);
	}
	
	public int addRecruitWish(String member_id, Recruit rec) {
		return recDAO.addRecruitWish(member_id, rec);
	}
	
	public List<Recruit_wish> recruit_wishList(String memberId){
		return recDAO.recruit_wishList(memberId);
	}
}
