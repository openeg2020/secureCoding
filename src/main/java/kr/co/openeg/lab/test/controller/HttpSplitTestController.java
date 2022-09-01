package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HttpSplitTestController {
	// HTTP 응답분할 
	@RequestMapping(value="/test/http_split_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testHttpSplit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		StringBuffer buffer=new StringBuffer();
		String data=request.getParameter("data");
		System.out.println("수신된 데이터: "+data);
		response.setHeader("openeg", data);
//		Cookie cookie=new Cookie("data",data);
//		response.addCookie(cookie);
		buffer.append("HTTP 응답 분할 테스트: Cookie 값 확인");
	    return buffer.toString();
	}
}
