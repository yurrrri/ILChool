package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.member.MemberSessionUtils;
import model.TO_DO_LIST;

public class TodoDAO {
		   private JDBCUtil jdbcUtil = null;
		   
		   public TodoDAO() {         
		      jdbcUtil = new JDBCUtil();   //  JDBCUtil 媛앹껜 �깮�꽦
		   }
		   public int create(TO_DO_LIST todo) throws SQLException {
			      String sql = "INSERT INTO TO_DO_LIST VALUES (?, ?, TO_DO_ID_SEQ.nextval) ";      
			      Object[] param = new Object[] {todo.getMemberid(), todo.getTO_DO() };            
			      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			      try {            
			         int result = jdbcUtil.executeUpdate();   // insert 臾� �떎�뻾
			         return result;
			      } catch (Exception ex) {
			         jdbcUtil.rollback();
			         ex.printStackTrace();
			      } finally {      
			         jdbcUtil.commit();
			         jdbcUtil.close();   // resource 諛섑솚
			      }
			      return 0;         
		   }
		   
		   public int updateTO_DO_LIST(TO_DO_LIST todo) throws SQLException {
			      String sql = "UPDATE TO_DO_LIST "
			               + "SET TO_DO "
			               + "WHERE TO_DO_ID=?";
			      Object[] param = new Object[] {todo.getTO_DO_ID()};            
			      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil   update      키           
			         
			      try {            
			         int result = jdbcUtil.executeUpdate();   // // update 臾� �떎�뻾
			         return result;
			      } catch (Exception ex) {
			         jdbcUtil.rollback();
			         ex.printStackTrace();
			      }
			      finally {
			         jdbcUtil.commit();
			         jdbcUtil.close();   // resource 諛섑솚
			      }      
			      return 0;
			   }
		public int deleteTO_DO_LIST(int to_do_id) throws SQLException{
			String sql = "DELETE FROM TO_DO_LIST WHERE TO_DO_ID=?";
			Object[] param = new Object[] {to_do_id};
			jdbcUtil.setSqlAndParameters(sql, param);
			
			try {
				int result = jdbcUtil.executeUpdate();
				return result;
			} catch(Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
			return 0;
		}
	
		public List<TO_DO_LIST> getTodoList(String memberid) throws SQLException {
			String sql = "SELECT TO_DO FROM TO_DO_LIST WHERE MEMBERID = ?";
		
			Object[] param = new Object[] {memberid};
			jdbcUtil.setSqlAndParameters(sql, param);
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<TO_DO_LIST> alltodoList = new ArrayList<TO_DO_LIST>();
				while(rs.next()) {
					TO_DO_LIST todo = new TO_DO_LIST(rs.getString("TO_DO"));
					alltodoList.add(todo);
				}
				return alltodoList;
			}catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				jdbcUtil.close();
			}
			return null;
		}

	}