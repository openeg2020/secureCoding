package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntOverflowTestController {
	// 정수 오버플로우 
	@RequestMapping(value="/test/int_overflow_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String testIntOverflow(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer buffer=new StringBuffer();
		String data=request.getParameter("data");
		System.out.println("수신데이터: "+data);
		int size=Integer.parseInt(data);
		String[] strings = new String[size+1];
        strings[0]="hello";
        buffer.append(strings[0]+"  int overflow test </br>");   
        buffer.append("입력데이터: " + data);
	    return buffer.toString();
	}
}
