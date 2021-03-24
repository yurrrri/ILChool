package controller.todo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import model.TO_DO_LIST;
import controller.Controller;
import controller.member.MemberSessionUtils;
import model.service.TodoManager;

public class ListToDoController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
        String memberid = MemberSessionUtils.getLoginmemberId(session);
//        TO_DO_LIST todo = new TO_DO_LIST(request.getParameter("TO_DO"), Integer.parseInt(request.getParameter("TO_DO_ID")));
//		TodoManager manager = TodoManager.getInstance();
//		List<TO_DO_LIST> todolist = manager.getTodoList(memberid);
//		
        TodoManager manager = TodoManager.getInstance();
		List<TO_DO_LIST> todolist = manager.getTodoList(memberid);
		
        request.setAttribute("todolist", todolist);
		return "/todo/list.jsp";
	}
}
