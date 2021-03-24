package model.dao;

import java.util.List;

import java.util.ArrayList;
import java.sql.*;

import model.Member;
import model.Study;

public class StudyDAO {
	private JDBCUtil jdbcUtil = null;
	
	public StudyDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	//���͵� ���̺� ���ο� �� ���� !
	public int create(Study study) throws SQLException {
		String sql = "INSERT INTO STUDY VALUES (STUDYID_SEQ.nextval, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, 1)";
		Object[] param = new Object[] {study.getTitle(),
				study.getWriter(), study.getCategory(), 
				study.getLocation(), study.getAge(), study.getCompanyName(), study.getPeriod(), 
				study.getMaxHeadcount()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	public int createRegister(String memberID) throws SQLException {
		String sql = "INSERT INTO Study_wish VALUES (?, STUDYID_SEQ.currval)";
		Object[] param = new Object[] {memberID};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	//������ ���͵� ������ ����(�ο� ���� ����
	public int updateStudyHeadcount(Study study) throws SQLException {
		String sql = "UPDATE STUDY "
				+ "SET currHeadcount=? "
				+ "WHERE study_id=?";
		Object[] param = new Object[] {study.getCurrHeadcount(), study.getStudyID()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	//study_id�� �ش��ϴ� ���͵� ����
	public int deleteStudy(int studyID) throws SQLException {
		String sql = "DELETE FROM STUDY WHERE study_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {studyID});
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	//study_id에 해당하는 스터디 정보에 찾아 도메인 클래스에 데이터 저장하여 반환
	public Study getStudyByNo(int studyID) throws SQLException {
		String sql = "SELECT s.title, s.writer, s.reportingdate, s.category, s.location, s.age, s.companyname, s.period, s.maxheadcount, s.currheadcount, "
				+ "m.memberId, m.password, m.name, m.email, m.phone, m.age AS mAge "
				+ "FROM STUDY s JOIN STUDY_WISH sw ON s.study_id = sw.study_id "
				+ "JOIN MEMBER m ON sw.member_id = m.memberId "
				+ "AND s.study_id=?";
		System.out.println(sql);
		jdbcUtil.setSqlAndParameters(sql, new Object[] {studyID});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Study study = null;
			List<Member> memberList = null;
			while (rs.next()) {
				if (study == null) {
					study = new Study(
							studyID,
							rs.getString("title"), rs.getString("writer"),
							rs.getDate("reportingdate"), rs.getString("category"),
							rs.getString("location"), rs.getString("age"),
							rs.getString("companyname"), rs.getInt("period"),
							rs.getInt("maxheadcount"), rs.getInt("currheadcount"));
					memberList = new ArrayList<Member>();
				}
				
				System.out.println("StudyDAO: " + study);
				
				Member member = new Member(rs.getString("memberId"), rs.getString("password"),
						rs.getString("name"), rs.getString("email"), rs.getString("phone"),
						rs.getInt("mAge"));
				memberList.add(member);
			}
			if (study != null)
				study.setMemberList(memberList);
			return study;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//모든 스터디 리스트 반환
	public List<Study> getAllStudyList() throws SQLException {
		String sql = "SELECT study_id, title, writer, reportingdate, category, location, age, companyname, period, maxheadcount, currheadcount "
				+ "FROM STUDY";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Study> allStudyList = new ArrayList<Study>();
			while (rs.next()) {
				Study study = new Study(
						rs.getInt("study_id"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getDate("reportingdate"),
						rs.getString("category"),
						rs.getString("location"),
						rs.getString("age"),
						rs.getString("companyname"),
						rs.getInt("period"),
						rs.getInt("maxheadcount"),
						rs.getInt("currheadcount"));
				allStudyList.add(study);
			}
			return allStudyList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 전체 사용자 정보 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여 해당하는 사용자 정보만을 List에 저장하여 반환 */
	public List<Study> getAllStudyList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT study_id, title, writer, reportingdate, category, location, age, companyname, period, maxheadcount, currheadcount "
				+ "FROM STUDY";
		jdbcUtil.setSqlAndParameters(sql, null,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Study> allStudyList = new ArrayList<Study>();
			while (rs.next()) {
				Study study = new Study(
						rs.getInt("study_id"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getDate("reportingdate"),
						rs.getString("category"),
						rs.getString("location"),
						rs.getString("age"),
						rs.getString("companyname"),
						rs.getInt("period"),
						rs.getInt("maxheadcount"),
						rs.getInt("currheadcount"));
				allStudyList.add(study);
			}
			return allStudyList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//����� ���͵� ����Ʈ ��ȯ
	public List<Study> getStudyByCompany(String companyName) throws SQLException {
		String sql = "SELECT study_id, title, writer, reportingdate, category, location, age, companyname, period, maxheadcount, currheadcount "
				+ "FROM STUDY " + "WHERE companyname = ?";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] {companyName});
		System.out.println(companyName);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Study> studyListByCom = new ArrayList<Study>();
			while (rs.next()) {
				Study study = new Study(
						rs.getInt("study_id"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getDate("reportingdate"),
						rs.getString("category"),
						rs.getString("location"),
						rs.getString("age"),
						rs.getString("companyname"),
						rs.getInt("period"),
						rs.getInt("maxheadcount"),
						rs.getInt("currheadcount"));
				studyListByCom.add(study);
			}
			return studyListByCom;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//������ ���͵� ����Ʈ ��ȯ
	public List<Study> getStudyByCategory(String category) {
		String sql = "SELECT study_id, title, writer, reportingdate, category, location, age, companyname, period, maxheadcount, currheadcount "
				+ "FROM STUDY " + "WHERE category = ?";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] {category});
		System.out.println(category);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Study> studyListByCat = new ArrayList<Study>();
			while (rs.next()) {
				Study study = new Study(
						rs.getInt("study_id"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getDate("reportingdate"),
						rs.getString("category"),
						rs.getString("location"),
						rs.getString("age"),
						rs.getString("companyname"),
						rs.getInt("period"),
						rs.getInt("maxheadcount"),
						rs.getInt("currheadcount"));
				studyListByCat.add(study);
			}
			return studyListByCat;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//������ ���͵� ����Ʈ ��ȯ
	public List<Study> getStudyByLocation(String location) {
		String sql = "SELECT study_id, title, writer, reportingdate, category, location, age, companyname, period, maxheadcount, currheadcount "
				+ "FROM STUDY " + "WHERE location = ?";
		jdbcUtil.setSqlAndParameters(sql,  new Object[] {location});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Study> studyListByLoc = new ArrayList<Study>();
			while (rs.next()) {
				Study study = new Study(
						rs.getInt("study_id"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getDate("reportingdate"),
						rs.getString("category"),
						rs.getString("location"),
						rs.getString("age"),
						rs.getString("companyname"),
						rs.getInt("period"),
						rs.getInt("maxheadcount"),
						rs.getInt("currheadcount"));
				studyListByLoc.add(study);
			}
			return studyListByLoc;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public int register(String memberID, int studyID) throws SQLException {
		String sql = "INSERT INTO Study_wish VALUES (?, ?)";
		Object[] param = new Object[] {memberID, studyID};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	
	
	public int disband(int studyID) throws SQLException {
		String sql = "DELETE FROM STUDY_WISH WHERE study_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {studyID});
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
}
