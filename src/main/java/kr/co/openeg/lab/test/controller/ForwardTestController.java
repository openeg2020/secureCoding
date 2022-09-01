package kr.co.openeg.lab.test.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForwardTestController {
	// 오픈리다이렉트
	@RequestMapping(value="/test/forward_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testForwarding(HttpServletRequest request,HttpServletResponse response){
		String url=request.getParameter("data");
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			return "redirect 테스트 오류";
		}
		return null;
	}
	
}
