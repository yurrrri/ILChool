package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Company;
import model.dao.CompanyDAO;
import model.dao.RecruitDAO;

public class CompanyManager {
	private static CompanyManager comMan = new CompanyManager();
	private CompanyDAO comDAO;

	private CompanyManager() {
		try {
			comDAO = new CompanyDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CompanyManager getInstance() {
		return comMan;
	}
	
//	회사 리스트 업데이트
	public void updateCompanyList() {
		comDAO.updateCompany();
	}
	
	public Company findCompany(String comName) {
		return comDAO.findCompany(comName);
	}
}
