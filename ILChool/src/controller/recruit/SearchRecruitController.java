package controller.recruit;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Recruit;
import model.service.RecruitManager;

public class SearchRecruitController implements Controller {
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	RecruitManager recMan = RecruitManager.getInstance();
    	String company_name = request.getParameter("company_name");
    	
    	List<Recruit> finded_recList = new ArrayList<Recruit>();
    	
		List<Recruit> recList = recMan.findRecruitList();
		for (int i=0; i<recList.size(); i++) {
			if (recList.get(i).getCompany_name().contains(company_name))
				finded_recList.add(recList.get(i));
		}
		
		// recrList�� request�� �����Ͽ� ä����� �˻� ȭ������ ����(forwarding)
		request.setAttribute("finded_recList", finded_recList);
		request.setAttribute("is_search_result", true);
		
		return "/recruit/searchRecruit.jsp";  
    }
}
