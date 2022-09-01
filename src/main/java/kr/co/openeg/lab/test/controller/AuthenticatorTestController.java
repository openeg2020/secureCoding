package kr.co.openeg.lab.test.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticatorTestController {
	// 인증 
	@RequestMapping(value="/test/authenticator_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testAuthenticator(HttpServletRequest request, 
			HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException{
		StringBuffer buffer=new StringBuffer();
		buffer.append("인증테스트");
        return buffer.toString();
	}
}
