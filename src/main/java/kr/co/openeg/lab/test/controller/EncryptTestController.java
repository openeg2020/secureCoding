package kr.co.openeg.lab.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.openeg.lab.util.TestUtil;

@Controller
public class EncryptTestController {
	
	// 암호화 테스트 
		@RequestMapping(value="/test/encrypt_test", method = RequestMethod.POST,produces = "application/text; charset=utf8")
		@ResponseBody
		public String testEncryption(HttpServletRequest request){
			StringBuffer buffer=new StringBuffer();
			String data=request.getParameter("data");
			String type=request.getParameter("type");
			switch ( TestUtil.getInt(type)  ) {
			case 0:
				buffer.append("SHA-256 테스트: "); break;
			case 1:
				buffer.append("AES 암호화 테스트: ");break;		
			case 2:
				buffer.append("AES 복호화 테스트: "); break;
			case 3:
				buffer.append("RSA 암호화 테스트: "); break;
			case 4:
				buffer.append("RSA 복호화 테스트: "); break;
		    default:
		    	buffer.append("잘못된 요청입니다: ");
			}
			
	        return buffer.toString();
			
		}

	

}
