package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Member;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Member 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */

public class MemberDAO {

	private JDBCUtil jdbcUtil = null;

	private SqlSessionFactory sqlSessionFactory;
	
	public MemberDAO() {	
		jdbcUtil = new JDBCUtil();	//  JDBCUtil 객체 생성		
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	
			/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */

	public Member create(Member member) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(MemberMapper.class).insertMember(member);
			if (result > 0) {
				sqlSession.commit();
			} 
			return member;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 기존의 사용자 정보를 수정.
	 */

		public int update(Member member) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(MemberMapper.class).updateMember(member);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 사용자 ID에 해당하는 사용자를 삭제.
	 */
	public int remove(String memberId) {		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(MemberMapper.class).deleteMember(memberId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
	}


	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 member 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Member findMember(String memberId) throws SQLException {
        String sql = "SELECT password, name, email, phone, age "
        			+ "FROM Member "
        			+ "WHERE memberId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Member member = new Member(		// member 객체를 생성하여 학생 정보를 저장
					memberId,
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getInt("age"));					
					
				return member;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Member> findmemberList() throws SQLException {
        String sql = "SELECT memberId, name, email, phone, age " 
        		   + "FROM Member "
        		   + "ORDER BY memberId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Member> memberList = new ArrayList<Member>();	// member들의 리스트 생성
			while (rs.next()) {
				Member member = new Member(			// member 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("memberId"),
					null,
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getInt("age"));
				memberList.add(member);				// List에 member 객체 저장
			}		
			return memberList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	public List<Member> findmemberList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT memberId, name, email, phone, age " 
					+ "FROM Member "
					+ "ORDER BY memberId ";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<Member> memberList = new ArrayList<Member>();	// member들의 리스트 생성
				do {
					Member member = new Member(			// member 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("memberId"),
						null,
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getInt("age"));
					memberList.add(member);							// 리스트에 member 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return memberList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}


	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingMember(String memberId) throws SQLException {
		String sql = "SELECT count(*) FROM Member WHERE memberId=? ";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

}
	

