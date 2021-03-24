package model.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Recruit;
import model.Recruit_wish;

/**
 * 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쌜억옙占쏙옙 占쏙옙占쏙옙占싹댐옙 DAO 클占쏙옙占쏙옙
 * Recruit 占쏙옙占싱븝옙占쏙옙 커占승댐옙티 占쏙옙占쏙옙占쏙옙 占쌩곤옙, 占쏙옙占쏙옙, 占쏙옙占쏙옙, 占싯삼옙 占쏙옙占쏙옙 
 */
public class RecruitDAO {
	private JDBCUtil jdbcUtil = null;

	public RecruitDAO() {
		jdbcUtil = new JDBCUtil();	// JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	};

	public List<Recruit> findRecruitListFromWorknet() {
		RecruitXmlParser parser = new RecruitXmlParser();
		List<Recruit> recrList = null;	// Community占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
		try {
			recrList = parser.recruitListFromWorknet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recrList;
	}
	
	public Recruit findRecruit(String title) {
		RecruitXmlParser parser = new RecruitXmlParser();
		List<Recruit> recrList = null;	// Community占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
		Recruit recruit = null;
		
		try {
			recrList = parser.recruitListFromWorknet();
			for (int i=0; i<recrList.size(); i++) {
				if (recrList.get(i).getTitle().equals(title)) {
					recruit = recrList.get(i);
					return recruit;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return recruit;
	}
	
//	채용을 준비하려는 회사를 recruitWish에 추가
	public int addRecruitWish(String member_id, Recruit rec) {
		java.sql.Date regDate = new java.sql.Date(rec.getRegDate().getTime());
		java.sql.Date deadline = new java.sql.Date(rec.getDeadLine().getTime());
		
		String sql = "INSERT INTO Recruit_wish VALUES (recruit_wish_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {rec.getCompany_name(), rec.getRecruit_url(),
				rec.getCompany_form(), rec.getTitle(), rec.getWorkingType(),
				regDate, deadline, member_id};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
		
		try {    
			int result = jdbcUtil.executeUpdate();
			return result; // insert �� ����
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	public List<Recruit> findRecruitByCompanyName(String comName){
		RecruitXmlParser parser = new RecruitXmlParser();
		List<Recruit> recrList = null;	// Community占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
		try {
			recrList = parser.recruitListFromWorknet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recrList;
	}
	
	public List<Recruit_wish> recruit_wishList(String memberId){
        String sql = "SELECT * from recruit_wish where MEMBERID = ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil�� query���� �Ű� ���� ����
		Recruit rec = null;
		
		try {
			
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Recruit_wish> rec_wishList = new ArrayList<Recruit_wish>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				rec = new Recruit(		
						rs.getString("company_name"),
						rs.getString("RECRUIT_URL"),
						rs.getString("company_form"),
						rs.getString("title"),
						rs.getString("workingtype"),
						rs.getDate("regdate"),
						rs.getDate("deadline")
				);
					
				Recruit_wish rec_wish = new Recruit_wish(
						rs.getInt("recruit_wish_id"),
						rs.getString("memberid"),
						rec);
				rec_wishList.add(rec_wish);
			}		
			return rec_wishList;
		
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}