package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.openeg.lab.util.TestUtil;

@RestController
public class SqlTestController {

	// SQL 인젝션
	@PostMapping(value = "test/sql_test", produces = "application/text; charset=utf8")
	public String testSqlInjection(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		TestUtil util = new TestUtil();
		buffer.append("ID,PASSWD 조회 결과:   ");
		buffer.append(util.readDB(id, passwd));
		return buffer.toString();
	}

	// SQL 인젝션
	@PostMapping(value = "test/sql_test_b", produces = "application/text; charset=utf8")
	public String testSqlInjectionB(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		String id = request.getParameter("id2");
		TestUtil util = new TestUtil();
		buffer.append("MySQL 조회결과:    ");
		buffer.append(util.readDB2(id));
		return buffer.toString();
	}

	// SQL 인젝션
	/*
	 * @RequestMapping(value="/test/sql_test_b2", method = RequestMethod.POST,
	 * produces = "application/text; charset=utf8")
	 * 
	 * @ResponseBody public String testSqlInjectionB2(HttpServletRequest request)
	 * throws Exception{ StringBuffer buffer=new StringBuffer(); String
	 * id=request.getParameter("id"); TestUtil util=new TestUtil();
	 * buffer.append("MS SQL Server 조회결과:    " ); buffer.append(util.readDB3(id));
	 * return buffer.toString(); }
	 */
}
