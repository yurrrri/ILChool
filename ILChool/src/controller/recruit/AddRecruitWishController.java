package controller.recruit;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import javax.servlet.http.HttpSession;
import controller.member.MemberSessionUtils;
import model.Recruit;
import model.service.RecruitManager;

public class AddRecruitWishController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String member_id = MemberSessionUtils.getLoginmemberId(session);
    	SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
    	
    	Recruit rec = new Recruit(
        		request.getParameter("company_name"),
    			request.getParameter("recruit_url"),
    			request.getParameter("company_form"),
    			request.getParameter("title"),
    			request.getParameter("workingType"),
    			df.parse(request.getParameter("regDate")),
    			df.parse(request.getParameter("deadLine"))
    			);
    	
		try {
			RecruitManager recMan = RecruitManager.getInstance();
			recMan.addRecruitWish(member_id, rec);
			
	        return "/recruit/list";	// ���� �� Ŀ�´�Ƽ ����Ʈ ȭ������ redirect
	        
		} catch (Exception e) {		// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("com", rec);
			return "/recruit/view";
		}
    }
}
