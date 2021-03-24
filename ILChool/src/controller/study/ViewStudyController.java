package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Study;
import model.service.StudyManager;

public class ViewStudyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Study study = null;
		StudyManager manager = StudyManager.getInstance();
		int study_id = Integer.parseInt(request.getParameter("study_id"));
		System.out.println(study_id);
		study = manager.getStudyByNo(study_id);
		
		request.setAttribute("study", study);
		
		System.out.println("ViewStudyController: " + study);
		return "/study/view.jsp";
	}

}
