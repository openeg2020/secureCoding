package kr.co.openeg.lab.test.controller;

import java.io.FileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.TestUtil;

@Controller
public class NullTestController {
	
	// 널포인트 역참조
		@RequestMapping(value="/test/null_test",produces = "application/text; charset=utf8")
		@ResponseBody
		public String testNull(HttpServletRequest request, HttpSession session){
			    StringBuffer buffer=new StringBuffer();			
		        String data=request.getParameter("data");
		        String userid=request.getParameter("userid");
		        try {
			        FileReader fr=new FileReader("C:/SecureCoding2021/set_env.txt");	
			        if( userid.equals("admin")) {
			        	switch(TestUtil.getInt(data)){
			        	case 0: buffer.append("백업 작업을 수행합니다."); break;
			        	case 1: buffer.append("복구 작업을 수행합니다."); break;
			        	case 2: buffer.append("실행 작업을 수행합니다."); break;
			        	default: buffer.append("선택된 작업이 없습니다.");
			        	}
			        } else {
			        	buffer.append("작업권한이 없습니다.");
			        }
		        }catch(Exception e) {
		        	e.printStackTrace();
		        	buffer.append("파일 초기화 작업을 수행합니다."); 
		        }
			 
	        return buffer.toString();	
		}
		

	

}
