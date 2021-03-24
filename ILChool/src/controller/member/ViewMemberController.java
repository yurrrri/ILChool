package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.MemberManager;
import model.service.MemberNotFoundException;
import model.Member;


public class ViewMemberController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/member/login/form";		// login form 요청으로 redirect
        }
    	
		MemberManager manager = MemberManager.getInstance();
		String memberId = request.getParameter("memberId");

    	Member member = null;
    	try {
			member = manager.findMember(memberId);	// 사용자 정보 검색
		} catch (MemberNotFoundException e) {				
	        return "redirect:/member/list";
		}	
		
    	request.setAttribute("member", member);		// 사용자 정보 저장				
		return "/member/view.jsp";				// 사용자 보기 화면으로 이동
    }
}
