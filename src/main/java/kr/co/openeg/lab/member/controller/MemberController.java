package kr.co.openeg.lab.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.openeg.lab.login.service.LoginService;
import kr.co.openeg.lab.member.model.MemberModel;
import kr.co.openeg.lab.member.model.MemberSecurity;
import kr.co.openeg.lab.member.service.MemberService;
import kr.co.openeg.lab.member.service.MemberValidatior;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/join")
	public String memberJoin(){
		return "/member/join";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("MemberModel") MemberModel memberModel, HttpServletRequest request, BindingResult result){
		ModelAndView mav = new ModelAndView();
		memberModel.setPinNo(service.makePin());
		new MemberValidatior().validate(memberModel, result);
		if(result.hasErrors()){
			mav.setViewName("/member/join");
			return mav;
		}
		
		int errCode=service.addMember(memberModel);
		service.addMemberSecurity(
				new MemberSecurity(memberModel.getUserId(),"","",""));
	
		switch(errCode) {
		case 1:
			mav.addObject("errCode", 1); // userId already exist 
			mav.setViewName("/member/join");
			break;
		case 2: 
			mav.addObject("errCode", 2); // failed to add new member
			mav.setViewName("/member/join");
			break;
		case 3:
			mav.addObject("errCode", 3); // success to add new member; move to login page
			break;
		}
		return mav;
	
	}
	
	@RequestMapping("/modify")	
	public ModelAndView memberModify(HttpSession session){
		ModelAndView mav=new ModelAndView();
		System.out.println("member_modify.do GET Request");
		String userId=(String)session.getAttribute("userId");
		System.out.println("session userid: "+userId);
		MemberModel m =service.findMember(userId);
		mav.addObject("member", m);
		mav.setViewName("member/member_modify");	
		return mav;
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)	
	public ModelAndView memberModifyProcess(
			@ModelAttribute("MemberModel") MemberModel memberModel, 
			 BindingResult result, HttpServletRequest request, HttpSession session){
		ModelAndView mav=new ModelAndView();
		// 서버 세션에 저장된 userId 와 DB에 저장할 데이터인 memberModel 객체의 userId 가 동일한지 체크 
		String userId=(String)session.getAttribute("userId");	// 회원 id
		if (userId != null && ! userId.equals(memberModel.getUserId())) {
			mav.addObject("errCode", 1);
			mav.addObject("member",memberModel);
			mav.setViewName("member/member_modify");
			return mav;
		}

		String oldpasswd=(String)request.getParameter("oldUserPw");	// 기존 패스워드
		String userPw = (String) request.getParameter("userPw");	// 새 패스워드
		
		// 회원정보 확인
		String findUserPw = loginService.checkUserId(userId).getUserPw();

		// 회원정보 일치하지 않을 때
		if (!oldpasswd.equals(findUserPw)) {
			mav.addObject("errCode", 1);
			mav.addObject("member",memberModel);
			mav.setViewName("member/member_modify");
			return mav;
		}
		
		// 기존 패스워드와 새 패스워드가 같을 때
		if (oldpasswd.equals(userPw)) {
			mav.addObject("errCode", 3);
			mav.setViewName("member/member_modify");
			return mav;
		}
		
		// 새 패스워드 설정
		memberModel.setUserPw(userPw);
		boolean is_Success = service.modifyMember(memberModel);
		
		if (is_Success) {
			mav.addObject("errCode", 4);
			mav.setViewName("member/member_modify");
		    return mav;	 
		} else {
			mav.addObject("errCode", 2);
			mav.setViewName("member/member_modify");
			return mav;
		}
	}
}
