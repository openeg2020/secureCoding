package kr.co.openeg.lab.test.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XssTestController {

	// Reflective XSS 테스트
	@RequestMapping(value = "/test/xss_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testXss(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		String data = request.getParameter("data");
		buffer.append(data);
		return buffer.toString();
	}

	// Stored XSS 테스트
	@RequestMapping(value = "/test/xss_test_b", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testXssB(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("1", "<script>alert('xss');</script>");
		map.put("2", "&lt;script&gt;alert('xss');&lt;/script&gt;");
		StringBuffer buffer = new StringBuffer();
		String data = request.getParameter("data");
		if ("1".equals(data)) {
			buffer.append(map.get("1"));
		} else if ("2".equals(data)) {
			buffer.append(map.get("2"));
		} else {
			buffer.append("잘못된 요청입니다.");
		}
		return buffer.toString();
	}

	// DOM XSS 테스트
	@RequestMapping(value = "/test/xss_test_c")
	public void testXssC(HttpServletRequest request, HttpServletResponse response) {
		String data = request.getParameter("data");
		//data=data.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		try {
			response.sendRedirect("/test/domxss?message=" + data);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}
	}
}
