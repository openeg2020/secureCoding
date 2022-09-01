package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.member.model.MemberModel;
import kr.co.openeg.lab.member.model.MemberSecurity;
import kr.co.openeg.lab.member.service.MemberService;

@Controller
public class CsrfTestController {

	@Autowired
	private MemberService service;
	
	// CSRF 테스트 
		@RequestMapping(value="/test/csrf_test",produces = "application/text; charset=utf8")
		@ResponseBody
		public String testCSRF(HttpServletRequest request) {
			StringBuffer buffer=new StringBuffer();
			String id=request.getParameter("id");
		    MemberSecurity m=service.findMemberSecurity(id);
			buffer.append("<p>ID: "+m.getUserId()+"</p>");
			buffer.append("<p>Comment: "+m.getComment()+"</p>");
	        return buffer.toString();
		}
		
		
		// CSRF 테스트 
		@RequestMapping(value="/test/csrf_test", method = RequestMethod.POST,produces = "application/text; charset=utf8")
		@ResponseBody
		public String testCSRF2(HttpServletRequest request) {
			StringBuffer buffer=new StringBuffer();
			String id=request.getParameter("id");
			String comment=request.getParameter("comment");
			int result=service.updateMemberSecurity(new MemberSecurity(id,"","",comment));
			buffer.append(result+"건이 처리됨");
	        return buffer.toString();
		}
	

	

}
