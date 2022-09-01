package kr.co.openeg.lab.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.openeg.lab.login.model.LoginSessionModel;
import kr.co.openeg.lab.login.service.LoginService;
import kr.co.openeg.lab.login.service.LoginValidator;
import kr.co.openeg.lab.member.model.MemberModel;


@Controller
public class LoginController {
	 
	@Autowired
	private LoginService service;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {

		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView loginProc(@ModelAttribute("LoginModel") LoginSessionModel loginModel, 
			   BindingResult result, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		

		// form validation
		new LoginValidator().validate(loginModel, result);
		if(result.hasErrors()){
			mav.setViewName("index");
			return mav;
		}
		
		String userId = loginModel.getUserId();
		String userPw = loginModel.getUserPw();
		
		MemberModel loginCheckResult = service.checkUserId(loginModel);
		
		//check joined user
		if(loginCheckResult == null){
			mav.addObject("userId", userId);
			mav.addObject("errCode", 1);	// not exist userId in database
			mav.setViewName("index");
			return mav; 
		}else {
			session.setAttribute("userId", userId);
			session.setAttribute("userName", loginCheckResult.getUserName());
			mav.setViewName("redirect:/board/main");
			return mav;
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login";
	}
}
