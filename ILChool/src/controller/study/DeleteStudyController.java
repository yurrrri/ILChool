package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.StudyManager;

public class DeleteStudyController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteStudyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int studyId = Integer.parseInt(request.getParameter("study_id"));
		
		StudyManager manager = StudyManager.getInstance();
		manager.disband(studyId);
		manager.remove(studyId);
		
		return "redirect:/study/list";
	}

}
