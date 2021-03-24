package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.MemberManager;

public class LoginController implements Controller {
private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String memberId = request.getParameter("memberId");
      String password = request.getParameter("password");
      
      try {
         // 紐⑤뜽�뿉 濡쒓렇�씤 泥섎━瑜� �쐞�엫
         MemberManager manager = MemberManager.getInstance();
         manager.login(memberId, password);
         
         HttpSession session = request.getSession();
            session.setAttribute(MemberSessionUtils.Member_SESSION_KEY, memberId);
            
         if (MemberSessionUtils.isLoginmember("admin", session))
            return "redirect:/member/list";   //由ъ뒪�듃 �솕硫댁쑝濡쒓�寃� 愿�由ъ옄 濡쒓렇�씤�씠硫�.
         else
            return "/main"; //�씪諛섏궗�슜�옄�씪寃쎌슦 �쉶�썝媛��엯�뤌�쑝濡�
             
      } catch (Exception e) {
         /* memberNotFoundException�씠�굹 PasswordMismatchException 諛쒖깮 �떆
          * �떎�떆 login form�쓣 �궗�슜�옄�뿉寃� �쟾�넚�븯怨� �삤瑜� 硫붿꽭吏��룄 異쒕젰
          
          */
            request.setAttribute("loginFailed", true);
         request.setAttribute("exception", e);
            return "/member/loginForm.jsp";         
      }   
    }
}