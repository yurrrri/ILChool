package controller.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Company;
import model.service.CompanyManager;

public class ViewCompanyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		CompanyManager comMan = CompanyManager.getInstance();
		String comName = request.getParameter("comName");
		
		Company com = comMan.findCompany(comName);
				
		request.setAttribute("com", com);	// 회사 정보 저장				
		return "/company/view.jsp";			// 회사 정보 보기 화면으로 이동
    }
}