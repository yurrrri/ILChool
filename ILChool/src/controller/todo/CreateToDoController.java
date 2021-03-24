package controller.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.TO_DO_LIST;
import model.service.TodoManager;

public class CreateToDoController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateToDoController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = (request.getSession());
		String memberid = MemberSessionUtils.getLoginmemberId(session);
		TO_DO_LIST todo = new TO_DO_LIST(memberid, request.getParameter("TO_DO"), 0);
		
		try {
			TodoManager manager = TodoManager.getInstance();
			manager.create(todo);
			
			
			log.debug("Create TODO: {}", todo);
			return "redirect:/todo/list";
			
		}catch (Exception e) {
			request.setAttribute("Failed", true);
			request.setAttribute("exception", e);
			return "/todo/list.jsp";
		}
	}
}
