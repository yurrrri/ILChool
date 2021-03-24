package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import model.Study;
import controller.Controller;
import model.service.StudyManager;

public class ListStudyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StudyManager manager = StudyManager.getInstance();
		List<Study> studyList = null;
				
		String option = request.getParameter("option");
		String search = request.getParameter("search");
		
		if (option == null || search == null || option.equals("") || search.equals("")) {
			studyList = manager.getStudyList();
		} else {
			if (option.equals("company"))
				studyList = manager.getStudyByCompany(search);
			else if (option.equals("category"))
				studyList = manager.getStudyByCategory(search);
			else if (option.equals("location"))
				studyList = manager.getStudyByLocation(search);
		}
		
		
		//studyList 객체를 request에 저장하여 커뮤니티 리스트 화면으로 이동(forwarding)
		request.setAttribute("studyList", studyList);
		return "/study/studyList.jsp";
	}

}
