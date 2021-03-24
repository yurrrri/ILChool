package controller.main;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Recruit_wish;
import model.TO_DO_LIST;
import model.service.RecruitManager;
import model.service.TodoManager;

public class MainController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {   
    	
    	HttpSession session = request.getSession();
        String memberid = MemberSessionUtils.getLoginmemberId(session);
    	/* 1. 채용 위시 목록 받아오는 부분 */
    	RecruitManager recMan = RecruitManager.getInstance();
    	List<Recruit_wish> rec_wishList = recMan.recruit_wishList(memberid);
    	request.setAttribute("recruit_wish_list", rec_wishList);
    	    	
    	/* 2. 투두 받아오는 부분 */
        
        TodoManager manager = TodoManager.getInstance();
		List<TO_DO_LIST> todolist = manager.getTodoList(memberid);
		
        request.setAttribute("todolist", todolist);
        
        System.out.println(memberid + ", " + todolist.isEmpty());
    	
    	
    	/* 3. session(memberid) 설정 부분 */
        request.setAttribute("memberId", memberid);
        
        return "/main/MainUI.jsp";
    	
    }
}