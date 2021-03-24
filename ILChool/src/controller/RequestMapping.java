package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.company.*;
import controller.main.MainController;
import controller.member.DeleteMemberController;
import controller.member.ListMemberController;
import controller.member.LoginController;
import controller.member.LogoutController;
import controller.member.RegisterMemberController;
import controller.member.UpdateMemberController;
import controller.member.UpdateMemberController2;
import controller.member.ViewMemberController;
import controller.member.ViewMemberController2;
import controller.recruit.AddRecruitWishController;
import controller.recruit.ListRecruitController;
import controller.recruit.SearchRecruitController;
import controller.recruit.ViewRecruitController;
import controller.study.CreateStudyController;
import controller.study.DeleteStudyController;
import controller.study.ListStudyController;
import controller.study.RegisterStudyController;
import controller.study.ViewStudyController;
import controller.todo.CreateToDoController;
import controller.todo.ListToDoController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 占쏙옙 占쏙옙청 uri占쏙옙 占쏙옙占쏙옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 HashMap 占쏙옙占쏙옙
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
        mappings.put("/", new ForwardController("index.jsp"));
        
//       占쏙옙占쏙옙占� 占쏙옙占쏙옙 (占쏙옙占쏙옙) controller
        mappings.put("/member/login/form", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/login", new LoginController());
        mappings.put("/member/logout", new LogoutController());
        mappings.put("/member/list", new ListMemberController());
        mappings.put("/member/view", new ViewMemberController());
        mappings.put("/member/view2", new ViewMemberController2());
        mappings.put("/member/register/form", new ForwardController("/member/registerForm.jsp"));
        mappings.put("/member/register", new RegisterMemberController());  //회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쌨되는곤옙
   // 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙청占쏙옙 占쏙옙占쏙옙 占쏙옙청 처占쏙옙 占쏙옙占쏙옙
        mappings.put("/member/update/form", new UpdateMemberController());
        mappings.put("/member/update2/form", new UpdateMemberController2());
        mappings.put("/member/update", new UpdateMemberController());
        mappings.put("/member/update2", new UpdateMemberController2());
 
        mappings.put("/member/delete", new DeleteMemberController());
    	
//        회占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙) controller
        mappings.put("/company/view", new ViewCompanyController());
    	
//       recruit controller
        mappings.put("/recruit/list", new ListRecruitController());
    	mappings.put("/recruit/view", new ViewRecruitController());
        mappings.put("/recruit/addwish", new AddRecruitWishController());     
        mappings.put("/recruit/search", new SearchRecruitController());
        
//      study controller
		mappings.put("/study/list", new ListStudyController());
		mappings.put("/study/create/form", new ForwardController("/study/studyCreation.jsp"));
		mappings.put("/study/create", new CreateStudyController());
		mappings.put("/study/view", new ViewStudyController());
		mappings.put("/study/delete", new DeleteStudyController());
		mappings.put("/study/register", new RegisterStudyController());
		
//		main
		mappings.put("/main", new MainController());
		
//		todo
        mappings.put("/todo/list", new ListToDoController());
        mappings.put("/todo/create", new CreateToDoController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 占쌍억옙占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 찾占쏙옙 占쏙옙환
        return mappings.get(uri);
    }
}