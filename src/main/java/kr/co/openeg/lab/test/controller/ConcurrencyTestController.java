package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.TestUtil;

@Controller
public class ConcurrencyTestController {

	static int count;

	// TOCTOU
	@RequestMapping(value = "/test/concurrency_test", produces = "application/text; charset=utf8")
	@ResponseBody
	public String testConcurrency(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("실행결과1: " + count + "<br/>");
		count = count + TestUtil.getInt(request.getParameter("data"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			buffer.append("처리 불가");
		}
		buffer.append("실행결과2: " + count);

		return buffer.toString();
	}

}
