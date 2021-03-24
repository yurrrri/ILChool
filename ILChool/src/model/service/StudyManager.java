package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Study;
import model.dao.StudyDAO;

public class StudyManager {
	private static StudyManager studyMan = new StudyManager();
	private StudyDAO studyDAO;
	
	private StudyManager() {
		try {
			studyDAO = new StudyDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static StudyManager getInstance() {
		return studyMan;
	}
	
	public int create(Study study) throws SQLException {
		return studyDAO.create(study);
	}
	
	public int updateHeadcount(Study study) throws SQLException {
		return studyDAO.updateStudyHeadcount(study);
	}
	
	public int remove(int study_id) throws SQLException {
		return studyDAO.deleteStudy(study_id);
	}
	
	public Study getStudyByNo(int study_id) throws SQLException {
		return studyDAO.getStudyByNo(study_id);
	}

	public List<Study> getStudyList() throws SQLException {
		return studyDAO.getAllStudyList();
	
	}
	
	public List<Study> getStudyByCompany(String companyname) throws SQLException {
		return studyDAO.getStudyByCompany(companyname);
	}
	
	public List<Study> getStudyByCategory(String category) throws SQLException {
		return studyDAO.getStudyByCategory(category);
	}
	
	public List<Study> getStudyByLocation(String location) throws SQLException {
		return studyDAO.getStudyByLocation(location);
	}
	
	public int createRegister(String memberID) throws SQLException {
		return studyDAO.createRegister(memberID);
	}
	
	public int register(String memberID, int studyID) throws SQLException {
		return studyDAO.register(memberID, studyID);
	}
	
	public int disband(int studyID) throws SQLException {
		return studyDAO.disband(studyID);
	}
	 /* 기업,종류,지역별 스터디 리스트 반환 메소드 접근 방법 구현할 것 */

}
