package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.TestUtil;

@Controller
public class LoopTestController {
	
	// 무한반복제어 
		@RequestMapping(value="/test/loop_test")
		@ResponseBody
		public String testLoopExit(HttpServletRequest request){
			    StringBuffer buffer=new StringBuffer();			
			    int number = TestUtil.getInt(request.getParameter("data"));
	            int result=TestUtil.factorial(number);
			    buffer.append(number+"! = "+result);
	            return buffer.toString();	
		}
	

	

}
