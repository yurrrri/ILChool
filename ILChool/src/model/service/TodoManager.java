package model.service;

import java.sql.SQLException;
import java.util.List;

import model.TO_DO_LIST;
import model.dao.TodoDAO;

public class TodoManager {
	private static TodoManager tdMan = new TodoManager();
	private TodoDAO todoDAO;
	
	private TodoManager() {
		try {
			todoDAO = new TodoDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static TodoManager getInstance() {
		return tdMan;
	}
	
	public int create(TO_DO_LIST todo)throws SQLException {
		return todoDAO.create(todo);
	}
	
	public int update(TO_DO_LIST todo)throws SQLException {
		return todoDAO.updateTO_DO_LIST(todo);
	}
	
	public int remove(int to_do_id)throws SQLException {
		return todoDAO.deleteTO_DO_LIST(to_do_id);
	}
	
	public List<TO_DO_LIST> getTodoList(String memberid) throws SQLException {
		return todoDAO.getTodoList(memberid);
	}
}
