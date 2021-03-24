package model.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import model.Company;

public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;

	public CompanyDAO() {
		jdbcUtil = new JDBCUtil();
	};
	
	public void updateCompany() {
		CompanyXmlParser parser = new CompanyXmlParser();
		List<Company> comList = null;
		try {
			comList = parser.companyListFromWorknet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO Company VALUES (companyid_seq.nextval, ?, ?, ?, ?)"
				+ " WHERE NOT EXISTS "
				+ "(SELECT name FROM Company WHERE name=?)";
		for (int i=0; i<comList.size(); i++) {
			Company c = comList.get(i);
			Object[] param = new Object[] {c.getName(), c.getForm(),
				c.getSummary(), c.getUrl(), c.getName()};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
							
			String key[] = {"company_id"};	// PK �÷��� �̸�     
			try {    
				jdbcUtil.executeUpdate(key);  // insert �� ����
			   	ResultSet rs = jdbcUtil.getGeneratedKeys();
			   	if(rs.next()) {
			   		int generatedKey = rs.getInt(1);   // ������ PK ��
			   		c.setCompany_id(generatedKey); 	// id�ʵ忡 ����  
			   	}
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource ��ȯ
			}
		}
	}
	
	public Company findCompany(String comName) {
		CompanyXmlParser parser = new CompanyXmlParser();
		List<Company> comList = parser.findCompany(comName);
				
		for (int i=0; i<comList.size(); i++) {
			if (comList.get(i).getName().equals(comName))
				return comList.get(i);
		}
		return null;
	}
}