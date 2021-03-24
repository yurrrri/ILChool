package controller.recruit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Recruit;
import model.service.RecruitManager;

public class ViewRecruitController implements Controller {
    
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	    	
		RecruitManager rcMan = RecruitManager.getInstance();
		String title = request.getParameter("title");
		Recruit recruit = rcMan.findRecruit(title);
		
		request.setAttribute("recruit", recruit);	// ä�� ���� ����				
		return "/recruit/view.jsp";			// ä�� ���� ���� ȭ������ �̵�
    }
}