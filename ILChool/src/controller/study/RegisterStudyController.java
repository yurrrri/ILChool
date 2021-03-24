package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Study;
import model.service.StudyManager;

public class RegisterStudyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StudyManager manager = StudyManager.getInstance();
		HttpSession session = request.getSession();
		
		int studyID = Integer.parseInt(request.getParameter("study_id"));
		String memberID = MemberSessionUtils.getLoginmemberId(session);
		
		manager.register(memberID, studyID);
		
		Study study = manager.getStudyByNo(studyID);
		study.setCurrHeadcount(study.getCurrHeadcount() + 1);
		manager.updateHeadcount(study);
		request.setAttribute("study", study);
		
		return "/study/view.jsp";
	}

}
