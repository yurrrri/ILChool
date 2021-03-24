package controller.recruit;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Recruit;
import model.service.RecruitManager;

public class ListRecruitController implements Controller {
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	RecruitManager recMan = RecruitManager.getInstance();
    	
		List<Recruit> recList = recMan.findRecruitList();
		
		// recrList�� request�� �����Ͽ� ä����� �˻� ȭ������ ����(forwarding)
		request.setAttribute("recList", recList);
		request.setAttribute("is_search_result", false);
		
		return "/recruit/searchRecruit.jsp";  
    }
}