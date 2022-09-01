package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.DBinit;

@Controller
public class TestController {

	@RequestMapping("/test/test")
	public String test() {
		return "test/test";
	}
	@RequestMapping("/test/domxss")
	public String domxsstest() {
		return "test/domxss";
	}

	// DB초기화
	@RequestMapping(value = "/test/init_db", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String initDB(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		StringBuffer buffer = new StringBuffer();
		if (! "admin".equals(session.getAttribute("userId"))) {
			throw new Exception();
		}
		DBinit util = new DBinit();
		// util.initMSSQLDB();
		util.initMySQLDB();
		buffer.append("DB 초기화 완료");
		request.getSession().invalidate();
		response.sendRedirect("/login");
		return buffer.toString();
	}

}
