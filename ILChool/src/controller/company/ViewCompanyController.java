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
				
		request.setAttribute("com", com);	// ȸ�� ���� ����				
		return "/company/view.jsp";			// ȸ�� ���� ���� ȭ������ �̵�
    }
}