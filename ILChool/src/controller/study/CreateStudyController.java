package controller.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Study;
import model.service.StudyManager;
import controller.member.MemberSessionUtils;

import java.util.Date;

public class CreateStudyController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateStudyController.class);
	Date date = new Date();
	
	/* writer 가져오는 방법 구현할 것 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		System.out.println(session.isNew());
		
		String writer = MemberSessionUtils.getLoginmemberId(session);
		
		Study study = new Study(0, request.getParameter("title"), writer,
				request.getParameter("category"), request.getParameter("location"),
				request.getParameter("age"), request.getParameter("companyName"),
				Integer.parseInt(request.getParameter("period")), Integer.parseInt(request.getParameter("maxHeadcount")), 1);

		try {
			StudyManager manager = StudyManager.getInstance();
			manager.create(study);
			manager.createRegister(writer);
		
			log.debug("Create Study : {}", study);
			return "redirect:/study/list";
			
		} catch (Exception e) {
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("study", study);
			return "/study/studyCreation.jsp";
		}
		
		
	}
}
