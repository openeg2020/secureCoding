package kr.co.openeg.lab.test.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.TestUtil;

@Controller
public class XpathTestController {
	
	
	// XPath 인젝션
	@RequestMapping(value="/test/xpath_test", method = RequestMethod.POST,produces = "application/text; charset=utf8")
	@ResponseBody
	public String testXpathInjection(HttpServletRequest request){
		StringBuffer buffer=new StringBuffer();
		String name=request.getParameter("name");
		TestUtil util=new TestUtil();
	    buffer.append("실행결과: <br/>"+util.readXML(name));
        return buffer.toString();
		
	}

	

}
