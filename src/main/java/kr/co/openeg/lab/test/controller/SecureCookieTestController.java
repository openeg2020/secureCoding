package kr.co.openeg.lab.test.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecureCookieTestController {
	// Secure Cookie
		@RequestMapping(value="/test/secure_cookie_test", method = RequestMethod.POST, produces = "application/text; charset=utf8")
		@ResponseBody
		public String testSecureCookie(HttpServletRequest request, HttpServletResponse response) {
			StringBuffer buffer=new StringBuffer();
			String data=request.getParameter("data");
			String type=request.getParameter("type");
			switch( Integer.parseInt(type)) {
			case 0:
				Cookie c=new Cookie("openeg",data);
				// 쿠키에 보안 설정 CODE 삽입
				c.setMaxAge(60*60*24*365);            // 쿠키 유지 기간 - 1년
				c.setPath("/"); 
				response.addCookie(c);
				buffer.append("openeg="+data +"  쿠키값 추가");
				break;
			case 1:
				Cookie[] cookies = request.getCookies();
				buffer.append("수신된 쿠키<br/>");
				for (int i = 0; i < cookies.length; i++) {
				    buffer.append(cookies[i].getName() +"=");
				    buffer.append(cookies[i].getValue() + "<br/>");
				}
				break;
			default:
				buffer.append("요청오류");
				break;
			}

	        return buffer.toString();		
		}
}
